package com.catalyst.teammateria.timeclock.services.impl;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserRoleDao;
import com.catalyst.teammateria.timeclock.servicesimpl.EmailServiceImpl;

@RunWith (MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl target;

    @Mock
    private MailSender sender;
    
    @Mock
    UserDao userDao;
    
    @Mock
    UserRoleDao userRoleDao;
    
    @Mock
    UserRole userRole;
    
    @Mock 
    SimpleMailMessage mockMessage;

    private static final String SUBJECT = "test";

    private static final String BODY = "test";

    @Test
    public void testSendMail() {
        
        UserRole expectedRole = new UserRole();
        expectedRole.setRoleId(2);
        Mockito.when(userRoleDao.getUserRoleById(Mockito.anyInt())).thenReturn(expectedRole);        
        Mockito.when(userDao.getUsersByRole(expectedRole)).thenReturn(createAdminList());
        target.sendMail(SUBJECT, BODY);
        
        // Verify mail has been sent
        Mockito.verify(sender, Mockito.atLeastOnce()).send(Mockito.any(SimpleMailMessage.class));
    }
    
    @Ignore
    private ArrayList<User> createAdminList(){
        User user1 = new User();
        User user2 = new User();
        
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        
        return users;
    }
}
