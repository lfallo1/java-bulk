package com.catalyst.teammateria.timeclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Controller for the reporting page
 *  
 * @author aDietrich
 *
 */
@Controller
public class ReportingController {

    /**
     * GET request for /reporting. return the reporting.jsp page
     * @return
     */
	@RequestMapping(value = "/reporting", method=RequestMethod.GET)
	public ModelAndView reportingGET(){
		return new ModelAndView("reporting");
	}
}
