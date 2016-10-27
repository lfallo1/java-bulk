package com.catalyst.teammateria.timeclock.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catalsyt.teammateria.timeclock.filters.AuthSuccessHandler;

/**
 * Controller for the main page
 * 
 * @author dGrimes
 */
@Controller
public class MainController {

    /**
     * Main page controller, redirect request based on role
     * 
     * @return view based on role
     */
    @RequestMapping (value = "/", method = RequestMethod.GET)
    public ModelAndView mainGET(HttpSession session) {
        return new ModelAndView("redirect:"
                + AuthSuccessHandler.determineTargetUrl(session));
    }
}
