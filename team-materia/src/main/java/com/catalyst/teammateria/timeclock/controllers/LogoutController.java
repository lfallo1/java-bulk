package com.catalyst.teammateria.timeclock.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * LogoutController<br>
 * Map requests related to the logout view
 * 
 * @author teamMateria
 */
@Controller
public class LogoutController {

    /**
     * GET request for logout. Redirect to the index url
     * 
     * @return
     */
    @RequestMapping (value = "logout", method = RequestMethod.GET)
    public ModelAndView logoutGET(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
