package com.catalyst.teammateria.timeclock.services;

/**
 * Service that handles sending an email.
 * @author aDietrich
 *
 */
public interface EmailService {
    /**
     * This method will send compose and send the message
     * 
     * @param to - Who you are sending the message to
     * @param subject - Subject of the email
     * @param body - Body of the email
     */
    void sendMail(String subject, String body);
}
