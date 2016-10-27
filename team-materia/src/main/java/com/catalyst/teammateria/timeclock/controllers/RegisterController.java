package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the registration page
 * 
 * @author dGrimes
 */
@Controller
public class RegisterController {

    /**
     * Registration page controller
     * 
     * @return registration page
     */
    @RequestMapping (value = "/register", method = RequestMethod.GET)
    public ModelAndView registerGET() {
        return new ModelAndView("register");
    }
}
