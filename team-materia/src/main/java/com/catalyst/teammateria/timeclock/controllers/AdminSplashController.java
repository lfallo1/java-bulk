package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminSplashController<br/>
 * Map requests related to the adminsplash view
 * 
 * @author lfallon
 */
@Controller
public class AdminSplashController {

    /**
     * return the adminsplash.jsp view
     * 
     * @return
     */
    @RequestMapping (value = "/adminsplash", method = RequestMethod.GET)
    public ModelAndView adminSplashGET() {
        return new ModelAndView("adminsplash");
    }
}