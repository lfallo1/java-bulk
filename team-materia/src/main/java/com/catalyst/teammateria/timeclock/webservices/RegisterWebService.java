package com.catalyst.teammateria.timeclock.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;
import com.catalyst.teammateria.timeclock.services.RegisterService;

/**
 * Web service for handling the registration page's ajax requests
 * 
 * @author dGrimes
 */
@RestController
public class RegisterWebService {

    private RegisterService service;

    /**
     * The abstracted service to be used for this page
     * 
     * @param service
     */
    @Autowired
    public void setService(RegisterService service) {
        this.service = service;
    }

    /**
     * The register action
     * 
     * @param registrationForm
     */
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public String addUser(RegistrationForm registrationForm) {
        return service.add(registrationForm);
    }

    /**
     * AJAX call from the page to return whether a username is available
     * 
     * @param username
     * @return
     */
    @RequestMapping (value = "/register/user/", method = RequestMethod.GET)
    public boolean getUsernameAvail(String username) {
        return service.checkUsername(username);
    }

    /**
     * AJAX call from the page to return whether an email is available
     * 
     * @param email
     * @return
     */
    @RequestMapping (value = "/register/email/", method = RequestMethod.GET)
    public boolean getEmailAvail(String email) {
        return service.checkEmail(email);
    }
}
