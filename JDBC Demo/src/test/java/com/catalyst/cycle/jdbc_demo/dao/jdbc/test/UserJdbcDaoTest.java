package com.catalyst.cycle.jdbc_demo.dao.jdbc.test;

import org.junit.Before; 
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.springframework.jdbc.core.JdbcTemplate;

import com.catalyst.cycle.jdbc_demo.dao.jdbc.UserJdbcDao;
import com.catalyst.cycle.jdbc_demo.model.User;

 
public class UserJdbcDaoTest {

    
    private UserJdbcDao target = new UserJdbcDao();
    
    private JdbcTemplate mockJdbcTemplate;
    
    @Before
    public void setUp(){
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockJdbcTemplate);
    }
    
    @Test
    public void addUserTest(){
        String userFirstname= "testFirstname";
        String userLastname="testLastname";
        Integer managerID = 5;
        
        User user = new User();
        user.setFirstName(userFirstname);
        user.setLastName(userLastname);
        User manager= new User();
        
        manager.setUserID(managerID);
        user.setManager(manager);
        target.addUser(user);
        //Verify the data that went in.
        verify(mockJdbcTemplate).update(anyString(), eq(userFirstname), eq(userLastname), eq(managerID));
            
    }
    
    
    @Test
    public void addUserNoManagerTest(){
        String userFirstname= "testFirstname";
        String userLastname="testLastname";
        Integer managerID = null;
        
        User user = new User();
        user.setFirstName(userFirstname);
        user.setLastName(userLastname);
        
        target.addUser(user);
        
        //Verify the data that went in.
        verify(mockJdbcTemplate).update(anyString(), eq(userFirstname), eq(userLastname), eq(managerID));      
    }
    
  
    
    
    
    
    
}
