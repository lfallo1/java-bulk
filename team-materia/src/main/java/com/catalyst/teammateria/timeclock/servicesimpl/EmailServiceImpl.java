package com.catalyst.teammateria.timeclock.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserRoleDao;
import com.catalyst.teammateria.timeclock.services.EmailService;

/**
 * The implementation of EmailService
 * 
 * @author aDietrich
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailSender  mailSender;
    private UserDao     userDao;
    private UserRoleDao userRoleDao;

    /**
     * Sets data access object for interaction with User table
     * 
     * @param userDao
     *            UserDao to be assigned
     */
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Sets data access object for interaction with UserRole table
     * 
     * @param userRoleDao
     *            UserRoleDao to be assigned
     */
    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public void sendMail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        UserRole userRole = userRoleDao.getUserRoleById(2);
        List<User> users = userDao.getUsersByRole(userRole);
        String[] sendTo;
        sendTo = new String[users.size()];
        for (int i = 0; i < users.size(); i++ ) {
            sendTo[i] = users.get(i).getEmail();
        }
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
