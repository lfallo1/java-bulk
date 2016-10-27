package com.catalyst.teammateria.injuryreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.service.UserAuthService;

/**
 * Implementation of the User Authorization Service
 * 
 * @author dGrimes
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean userHasId(String loggedInUsername, Integer id) {
        return userDao.getUserByUsername(loggedInUsername).getEmployee()
                .getEmployeeId() == id;
    }
}
