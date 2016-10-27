package com.catalyst.teammateria.injuryreport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic Controller for injured employee (employee search) page
 * 
 * @author dGrimes
 */
@Controller
public class InjuredEmployeeController {

    /**
     * Base get request for the injured employee page
     * 
     * @return
     */
    @RequestMapping (value = "/injuredemployee", method = RequestMethod.GET)
    public ModelAndView injuredEmployeeGET() {
        return new ModelAndView("injuredemployee");
    }
}
