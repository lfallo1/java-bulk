package com.catalyst.cycle.jdbc_demo.service.impl;

import java.util.List;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.cycle.jdbc_demo.dao.UserDao;
import com.catalyst.cycle.jdbc_demo.model.User;
import com.catalyst.cycle.jdbc_demo.service.UserService;
import com.catalyst.cycle.jdbc_demo.utils.CyclicStateException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidIdentifierException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidInputException;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public void addUser(User user) throws InvalidInputException {
        //Check the user object for errors.
        List<String> errorCodes =user.checkErrors();
        if(errorCodes.size() > 0){
            //Custom exception that stores a list of error codes
            //No code after this line will be reachable.
            throw new InvalidInputException(errorCodes);
        }
        //No errors, so add the user
         this.userDao.addUser(user);
       

    }

    @Override
    @Transactional
    public void editUser(User user) throws CyclicStateException {
        this.userDao.updateUser(user); 
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        List<User> userList = this.userDao.getAll();
        for(User user : userList){
            this.userDao.removeUser(user);
            
        }
        
    }

    @Override
    public User getUserByID(Integer userID) throws InvalidIdentifierException {
       return this.userDao.getUserById(userID);
      
    }

    @Override
    public List<User> allUsers() {
        List<User> userList = this.userDao.getAll();
        return userList;
    }
    

    @Override
    public List<User> staff(Integer managerID) {
       return this.userDao.getStaffOfUser(managerID);
        
    }

}
