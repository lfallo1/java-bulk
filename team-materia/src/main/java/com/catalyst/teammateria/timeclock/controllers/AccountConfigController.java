package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Account Config Page routing
 * 
 * @author dGrimes
 */
@Controller
public class AccountConfigController {
    
    /**
     * Return the accountconfig.jsp view
     * @return
     */
    @RequestMapping (value = "/accountconfig", method = RequestMethod.GET)
    public ModelAndView accountConfigGET() {
        return new ModelAndView("accountconfig");
    }
}