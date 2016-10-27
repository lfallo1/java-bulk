package com.catalyst.teammateria.timeclock.dao.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.hibernate.UserTimeDaoHibernate;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

@RunWith(MockitoJUnitRunner.class)
public class UserTimeDaoTest {

	@InjectMocks
	UserTimeDaoHibernate target;
	@Mock
	EntityManager em;
	@Mock
	UserTime userTime = new UserTime();
	@Mock
	TypedQuery<UserTime> typedQueryUserTime;
	@Mock
	TypedQuery<LocalDate> typedQueryLocalDate;
	@Mock
	TypedQuery<Long> typedQueryLong;
	@Mock
	TypedQuery<UserTimeMillis> userTotalHoursQuery;
	@Mock
	TypedQuery<UserTimeMillis> userDailyHoursQuery;
	@Mock
	TypedQuery<User> typedQueryUser;
	@Mock
	Query query;
	@Mock
	List<UserTime> listUserTime;
	@Mock
	List<UserTimeMillis> userTotalHoursList;
	User user = new User();

	@Test
	public void setEmTest() {
		target.setEm(em);
	}

	@Test
	public void addUserTimeTest() {
		target.addUserTime(userTime);
		// Verify that userTime was persisted once
		verify(em, Mockito.times(1)).persist(userTime);
	}

	@Test
	public void updateUserTimeTest() {
		target.updateUserTime(userTime);
		// Verify that userTime was merged once
		verify(em, Mockito.times(1)).merge(userTime);
	}

	@Test
	public void getUserTimeByStillOpenTest() {
		when(em.createQuery(anyString(), eq(UserTime.class))).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.setParameter("user", user)).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.getSingleResult()).thenReturn(userTime);
		UserTime testUserTime = target.getUserTimeByStillOpen(user);
		assertEquals(testUserTime, userTime);
	}

	@Test
	public void getUserTimeObjectsBetweenDatesTest() {
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		when(em.createQuery(anyString(), eq(UserTime.class))).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.setParameter("startDate", startDate))
				.thenReturn(typedQueryUserTime);
		when(typedQueryUserTime.setParameter("endDate", endDate)).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.setParameter("user", user)).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.getResultList()).thenReturn(listUserTime);
		List<UserTime> testUserTime = target.getUserTimeObjectsBetweenDates(
				startDate, endDate, user);
		assertEquals(testUserTime, listUserTime);
	}

	@Test
	public void getUserTimeObjectsBetweenDatesForTimeManagementTest() {
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		when(em.createQuery(anyString(), eq(UserTime.class))).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.setParameter("startDate", startDate))
				.thenReturn(typedQueryUserTime);
		when(typedQueryUserTime.setParameter("endDate", endDate)).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.setParameter("user", user)).thenReturn(
				typedQueryUserTime);
		when(typedQueryUserTime.getResultList()).thenReturn(listUserTime);
		List<UserTime> expected = target
				.getUserTimeObjectsBetweenDatesForTimeManagement(startDate,
						endDate, user);
		assertEquals(expected, listUserTime);
	}

	@Test
	public void getOldestRecordTest() {
		LocalDate localDate = new LocalDate();
		when(em.createQuery(anyString(), eq(LocalDate.class))).thenReturn(
				typedQueryLocalDate);
		when(typedQueryLocalDate.setParameter("user", user)).thenReturn(
				typedQueryLocalDate);
		when(typedQueryLocalDate.getSingleResult()).thenReturn(localDate);
		LocalDate testLocalDate = target.getOldestRecord(user);
		assertEquals(testLocalDate, localDate);
	}

	@Test
	public void checkOpenTicketStatusTest() {
		// Return an open ticket, assert true
		Long expectedValue = 1l;
		when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
				typedQueryLong);
		when(typedQueryLong.setParameter("user", user)).thenReturn(
				typedQueryLong);
		when(typedQueryLong.getSingleResult()).thenReturn(expectedValue);
		assertTrue(target.checkOpenTicketStatus(user));
		// Return a closed ticket, assert false
		expectedValue = 0l;
		when(typedQueryLong.getSingleResult()).thenReturn(expectedValue);
		assertFalse(target.checkOpenTicketStatus(user));
	}

	@Test
	public void getAllUsersClockedInMoreThanEightHoursTest() {
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		when(em.createQuery(anyString(), eq(UserTimeMillis.class))).thenReturn(
				userTotalHoursQuery);
		when(
				userTotalHoursQuery.setParameter(anyString(),
						any(LocalDate.class))).thenReturn(userTotalHoursQuery);
		when(userTotalHoursQuery.getResultList())
				.thenReturn(userTotalHoursList);
		target.getAllUsersClockedInMoreThanEightHours(startDate, endDate);
		verify(userTotalHoursQuery, times(1)).getResultList();
	}

	@Test
	public void getAllByWeekTest() {
		LocalDate startWeek = new LocalDate();
		LocalDate endWeek = new LocalDate();
		when(em.createQuery(anyString(), eq(UserTimeMillis.class))).thenReturn(
				userTotalHoursQuery);
		when(
				userTotalHoursQuery.setParameter(anyString(),
						any(LocalDate.class))).thenReturn(userTotalHoursQuery);
		when(userTotalHoursQuery.getResultList())
				.thenReturn(userTotalHoursList);
		target.getAllByWeek(startWeek, endWeek);
		verify(userTotalHoursQuery, times(1)).getResultList();
	}

	@Test
	public void getAllUsersTotalHoursWorkedTest() {
		// Initialize input for target
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		User expectedUser = new User();
		// Return the mocked userhoursquery when requesting usertotalhours
		when(em.createQuery(anyString(), eq(UserTimeMillis.class))).thenReturn(
				userTotalHoursQuery);
		when(
				userTotalHoursQuery.setParameter(anyString(),
						any(LocalDate.class))).thenReturn(userTotalHoursQuery);
		when(userTotalHoursQuery.getResultList())
				.thenReturn(userTotalHoursList);
		// Return a list, and assert it is equal to the list the query returned
		List<UserTimeMillis> list = target.getWeeklyTotalHours(startDate,
				endDate, expectedUser);
		assertEquals(list, userTotalHoursList);
		// Repeat the test with a null user, verify a user parameter is not set
		// in the query
		expectedUser = null;
		target.getWeeklyTotalHours(startDate, endDate, expectedUser);
		verify(userTotalHoursQuery, never()).setParameter(anyString(),
				eq(User.class));
	}
	
	@Test
	public void getAllUsersTotalHoursWorkedByDayTest() {
		// Initialize input for target
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();
		User expectedUser = new User();
		// Return the mocked userhoursquery when requesting usertotalhours
		when(em.createQuery(anyString(), eq(UserTimeMillis.class))).thenReturn(
				userDailyHoursQuery);
		when(
				userDailyHoursQuery.setParameter(anyString(),
						any(LocalDate.class))).thenReturn(userDailyHoursQuery);
		when(userDailyHoursQuery.getResultList())
				.thenReturn(userTotalHoursList);
		// Return a list, and assert it is equal to the list the query returned
		List<UserTimeMillis> list = target.getDailyTotalHours(startDate,
				endDate, expectedUser);
		assertEquals(list, userTotalHoursList);
		// Repeat the test with a null user, verify a user parameter is not set
		// in the query
		expectedUser = null;
		target.getDailyTotalHours(startDate, endDate, expectedUser);
		verify(userTotalHoursQuery, never()).setParameter(anyString(),
				eq(User.class));
	}	

	@Test
	public void getNewestRecordTest() {
		User inputUser = new User();
		LocalDate expectedDate = new LocalDate();
		when(em.createQuery(anyString(), eq(LocalDate.class))).thenReturn(
				typedQueryLocalDate);
		when(typedQueryLocalDate.setParameter(anyString(), any(User.class)))
				.thenReturn(typedQueryLocalDate);
		when(typedQueryLocalDate.getSingleResult()).thenReturn(expectedDate);
		LocalDate returnedDate = target.getNewestRecord(inputUser);
		assertEquals(expectedDate, returnedDate);
	}

	@Test
	public void getDaysWorkedTest() {
		when(em.createQuery(anyString(), eq(LocalDate.class))).thenReturn(
				typedQueryLocalDate);
		when(typedQueryLocalDate.setParameter("userId", 1)).thenReturn(
				typedQueryLocalDate);
		List<LocalDate> expectedList = new ArrayList<LocalDate>();
		when(typedQueryLocalDate.getResultList()).thenReturn(expectedList);
		assertEquals(expectedList, target.getDaysWorked(1));
	}

	@Test
	public void getActiveWeeksTest() {
		when(em.createQuery(anyString(), eq(LocalDate.class))).thenReturn(
				typedQueryLocalDate);
		List<LocalDate> expectedList = new ArrayList<LocalDate>();
		when(typedQueryLocalDate.getResultList()).thenReturn(expectedList);
		assertEquals(expectedList, target.getActiveWeeks());
	}

	@Test
	public void getDistinctListWeeks() {
		when(em.createQuery(anyString(), eq(LocalDate.class))).thenReturn(
				typedQueryLocalDate);
		List<LocalDate> expectedList = new ArrayList<LocalDate>();
		when(typedQueryLocalDate.getResultList()).thenReturn(expectedList);
		assertEquals(expectedList, target.getDistinctListWeeks());
	}

	@Test
	public void getDistinctUserListTest() {
		when(em.createQuery(anyString(), eq(User.class))).thenReturn(
				typedQueryUser);
		List<User> expectedList = new ArrayList<User>();
		when(typedQueryUser.getResultList()).thenReturn(expectedList);
		assertEquals(expectedList, target.getDistinctUserList());
	}
}
