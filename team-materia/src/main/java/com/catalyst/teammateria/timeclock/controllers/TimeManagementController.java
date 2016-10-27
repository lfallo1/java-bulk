package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * TimeManagementController<br>
 * Map requests related to the timemanagement view
 * 
 * @author aDietrich
 *
 */
@Controller
public class TimeManagementController {
    
    /**
     * Return the timemanagement.jsp view
     * @return
     */
    @RequestMapping (value = "/timemanagement", method = RequestMethod.GET)
    public ModelAndView timeManagementGET() {
        return new ModelAndView("timemanagement");
    }
}
