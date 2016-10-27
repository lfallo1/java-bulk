package com.catalyst.teammateria.injuryreport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller class to direct the user to the admin tools control center
 * 
 * @author dGrimes
 */
@Controller
public class AdminToolsController {

    /**
     * Main controller to send you to the admin tools page
     * 
     * @return admin tools page
     */
    @RequestMapping (value = "/admintools", method = RequestMethod.GET)
    public ModelAndView adminToolsGET() {
        return new ModelAndView("admintools");
    }
}
