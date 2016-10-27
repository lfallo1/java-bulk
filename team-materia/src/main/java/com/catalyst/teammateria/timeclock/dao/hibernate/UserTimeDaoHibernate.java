package com.catalyst.teammateria.timeclock.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

/**
 * Implementation of UserTimeDao. Responsible for performing database operations
 * with UserTime objects.
 * 
 * @author aDietrich
 */
@Component
public class UserTimeDaoHibernate implements UserTimeDao {

    private static final String ACTIVE_WEEKS_QRY = "SELECT MIN(u.clockDate) FROM UserTime u WHERE u.clockOut != null GROUP BY Year(u.clockDate), Week(u.clockDate) ORDER BY Year(u.clockDate) desc, Week(u.clockDate) desc";

    private static final String WEEK_TOTAL_HRS_WORKED_QRY = "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis(u, SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null GROUP BY u.user, week(u.clockDate) ORDER BY u.user.username";

    private static final String WEEK_TOTAL_HRS_WORKED_QRY_USER_FILTER = "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis(u, SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null AND u.user =:user GROUP BY u.user, week(u.clockDate) ORDER BY u.user.username";

    private static final String OVERTIME_QRY = "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis(u, SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null GROUP BY u.user, u.clockDate HAVING SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000)) > 28800000 ORDER BY u.clockDate desc";
    
    private static final String DAILY_TOTAL_HRS_WORKED_QRY = "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis(u,SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null GROUP BY u.user, u.clockDate HAVING SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000)) > 0 ORDER BY u.user.username, u.clockDate";
    
    private static final String DAILY_TOTAL_HRS_WORKED_QRY_USER_FILTER = "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis(u,SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null AND u.user =:user GROUP BY u.user, u.clockDate HAVING SUM((hour(u.clockOut)*3600000 + minute(u.clockOut)*60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn)*60000 + second(u.clockIn)*1000)) > 0 ORDER BY u.user.username, u.clockDate";

    private static final String START_DATE = "startDate";

    private static final String END_DATE = "endDate";

    private EntityManager em;

    private static final String USER_PARAM_NAME = "user";

    /**
     * Setter for hibernate's required entity manager
     * 
     * @param em
     */
    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addUserTime(UserTime userTime) {
        this.em.persist(userTime);
    }

    @Override
    @Transactional
    public void updateUserTime(UserTime userTime) {
        this.em.merge(userTime);
    }

    @Override
    public boolean checkOpenTicketStatus(User user) {
        if (em.createQuery(
                "SELECT COUNT(o) FROM UserTime o WHERE o.user = :user AND o.clockOut = null",
                Long.class).setParameter(USER_PARAM_NAME, user)
                .getSingleResult() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public UserTime getUserTimeByStillOpen(User user) {
        return em
                .createQuery(
                        "SELECT o FROM UserTime o WHERE o.user = :user AND o.clockOut = null",
                        UserTime.class).setParameter(USER_PARAM_NAME, user)
                .getSingleResult();
    }

    @Override
    public List<UserTime> getUserTimeObjectsBetweenDates(LocalDate startDate,
            LocalDate endDate, User user) {
        return em
                .createQuery(
                        "SELECT o FROM UserTime o WHERE o.clockDate >=:startDate AND o.clockDate <=:endDate AND o.user = :user AND o.clockOut != null",
                        UserTime.class).setParameter(START_DATE, startDate)
                .setParameter(END_DATE, endDate)
                .setParameter(USER_PARAM_NAME, user).getResultList();
    }

    @Override
    public List<UserTime> getUserTimeObjectsBetweenDatesForTimeManagement(
            LocalDate startDate, LocalDate endDate, User user) {
        return em
                .createQuery(
                        "SELECT o FROM UserTime o WHERE o.clockDate >=:startDate AND o.clockDate <:endDate AND o.user = :user",
                        UserTime.class).setParameter(START_DATE, startDate)
                .setParameter(END_DATE, endDate)
                .setParameter(USER_PARAM_NAME, user).getResultList();
    }

    @Override
    public LocalDate getOldestRecord(User user) {
        return em
                .createQuery(
                        "SELECT MIN(o.clockDate) FROM UserTime o WHERE o.user = :user",
                        LocalDate.class).setParameter(USER_PARAM_NAME, user)
                .getSingleResult();
    }

    @Override
    public LocalDate getNewestRecord(User user) {
        return em
                .createQuery(
                        "SELECT MAX(u.clockDate) FROM UserTime u WHERE u.user =:user and u.clockOut != null",
                        LocalDate.class).setParameter(USER_PARAM_NAME, user)
                .getSingleResult();
    }

    @Override
    public List<LocalDate> getDistinctListWeeks() {
        return em
                .createQuery(
                        "select MIN(clockDate) from UserTime u group by Week(u.clockDate), Year(u.clockDate) order by Week(u.clockDate), Year(u.clockDate)",
                        LocalDate.class).getResultList();
    }

    @Override
    public List<User> getDistinctUserList() {
        return em
                .createQuery(
                        "select distinct u.user FROM UserTime u where u.clockOut != null",
                        User.class).getResultList();
    }

    @Override
    public List<UserTimeMillis> getAllUsersClockedInMoreThanEightHours(
            LocalDate startDate, LocalDate endDate) {
        return em.createQuery(OVERTIME_QRY, UserTimeMillis.class)
                .setParameter(START_DATE, startDate)
                .setParameter(END_DATE, endDate).getResultList();
    }

    @Override
    public List<UserTimeMillis> getAllByWeek(LocalDate startWeek,
            LocalDate endWeek) {
        return em
                .createQuery(
                        "SELECT new com.catalyst.teammateria.timeclock.formbeans.UserTotalHours (u, SUM((hour(u.clockOut)*3600000 + minute(u.clockOut) * 60000 + second(u.clockOut)*1000) - (hour(u.clockIn)*3600000 + minute(u.clockIn) * 60000 + second(u.clockIn)*1000))) FROM UserTime u WHERE u.clockDate >=:startDate AND u.clockDate <=:endDate AND u.clockOut != null GROUP BY u.user",
                        UserTimeMillis.class)
                .setParameter(START_DATE, startWeek)
                .setParameter(END_DATE, endWeek).getResultList();
    }

    @Override
    public List<UserTimeMillis> getWeeklyTotalHours(LocalDate startDate,
            LocalDate endDate, User user) {
        if (user == null) {
            return em
                    .createQuery(WEEK_TOTAL_HRS_WORKED_QRY,
                            UserTimeMillis.class)
                    .setParameter(START_DATE, startDate)
                    .setParameter(END_DATE, endDate).getResultList();
        } else {
            return em
                    .createQuery(WEEK_TOTAL_HRS_WORKED_QRY_USER_FILTER,
                            UserTimeMillis.class)
                    .setParameter(START_DATE, startDate)
                    .setParameter(END_DATE, endDate)
                    .setParameter(USER_PARAM_NAME, user).getResultList();
        }
    }
    
    @Override
    public List<UserTimeMillis> getDailyTotalHours(LocalDate startDate,
            LocalDate endDate, User user) {
        if (user == null) {
            return em
                    .createQuery(DAILY_TOTAL_HRS_WORKED_QRY,
                            UserTimeMillis.class)
                    .setParameter(START_DATE, startDate)
                    .setParameter(END_DATE, endDate).getResultList();
        } else {
            return em
                    .createQuery(DAILY_TOTAL_HRS_WORKED_QRY_USER_FILTER,
                            UserTimeMillis.class)
                    .setParameter(START_DATE, startDate)
                    .setParameter(END_DATE, endDate)
                    .setParameter(USER_PARAM_NAME, user).getResultList();
        }
    }    

    @Override
    public List<LocalDate> getDaysWorked(int userId) {
        return em
                .createQuery(
                        "SELECT  o.clockDate FROM UserTime o WHERE o.user.userId = :userId GROUP BY week(o.clockDate),year(o.clockDate)",
                        LocalDate.class).setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<LocalDate> getActiveWeeks() {
        return em.createQuery(ACTIVE_WEEKS_QRY, LocalDate.class)
                .getResultList();
    }
}
