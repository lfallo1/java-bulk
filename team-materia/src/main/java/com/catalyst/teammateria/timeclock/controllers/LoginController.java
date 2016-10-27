package com.catalyst.teammateria.timeclock.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginController<br>
 * Map requests related to the login view
 * 
 * @author teamMateria
 */
@Controller
public class LoginController {

    /**
     * GET request for /login. return the login.jsp page
     * 
     * @return
     */
    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGET(HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    /**
     * On a successful login redirect to the main controller, which will then
     * forward the request based on the user's role
     * 
     * @return
     */
    @RequestMapping (value = "/loginsuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccessGET() {
        return new ModelAndView("redirect:/");
    }
}
