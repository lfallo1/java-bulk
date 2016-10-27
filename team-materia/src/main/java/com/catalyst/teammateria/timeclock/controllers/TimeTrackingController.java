package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the time tracking page
 * @author aDietrich
 *
 */
@Controller
public class TimeTrackingController {

    /**
     * return the timetracking.jsp view
     * 
     * @return
     */
    @RequestMapping (value = "/timetracking", method = RequestMethod.GET)
    public ModelAndView timeTrackingGET() {
        return new ModelAndView("timetracking");
    }
}
