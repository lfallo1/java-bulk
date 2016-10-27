package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.servicesimpl.TimeTrackingServiceImpl;


public class ClockOutTest {
    
    private TimeTrackingServiceImpl target;
    
    private UserTimeDao userTimeDao;
    
    @Before
    public void setUp(){
        target = new TimeTrackingServiceImpl();
        userTimeDao = new UserTimeDaoStub();
        target.setUserTimeDao(userTimeDao);
    }
    
    @Test
    public void testClockout(){
        User user = new User();
        target.clockOut(user);
        assertNotNull(userTimeDao.getUserTimeByStillOpen(user).getClockOut());
    }
    
}
