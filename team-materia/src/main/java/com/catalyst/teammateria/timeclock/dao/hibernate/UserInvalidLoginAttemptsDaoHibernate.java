package com.catalyst.teammateria.timeclock.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;
import com.catalyst.teammateria.timeclock.dao.UserInvalidLoginAttemptsDao;

/**
 * The Hibernate specific implementation of UserInvalidLoginAttemptsDao
 * 
 * @author dGrimes
 */
@Component
public class UserInvalidLoginAttemptsDaoHibernate implements
        UserInvalidLoginAttemptsDao {

    private EntityManager em;

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
    public UserInvalidLoginAttempts getUserInvalidLoginAttemptsByUser(User user) {
        return em
                .createQuery(
                        "SELECT o FROM UserInvalidLoginAttempts o WHERE o.userId =:userId",
                        UserInvalidLoginAttempts.class)
                .setParameter("userId", user.getUserId()).getSingleResult();
    }

    @Override
    @Transactional
    public void addUserInvalidLoginAttempts(UserInvalidLoginAttempts obj) {
        this.em.persist(obj);
    }

    @Override
    @Transactional
    public void updateUserInvalidLoginAttempts(UserInvalidLoginAttempts obj) {
        this.em.merge(obj);
    }

    @Override
    @Transactional
    public void deleteUserInvalidLoginAttempts(UserInvalidLoginAttempts obj) {
        this.em.remove(this.em.contains(obj) ? obj : this.em.merge(obj));
    }
}
