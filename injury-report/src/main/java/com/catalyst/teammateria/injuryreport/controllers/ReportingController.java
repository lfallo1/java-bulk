package com.catalyst.teammateria.injuryreport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Admin Reporting controller
 * 
 * @author dGrimes
 */
@Controller
public class ReportingController {

    /**
     * Admin report base url.
     * 
     * @return admin report page
     */
    @RequestMapping (value = "/adminreport", method = RequestMethod.GET)
    public ModelAndView reportingGET() {
        return new ModelAndView("reporting");
    }
}
