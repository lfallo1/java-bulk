package com.catalyst.teammateria.injuryreport.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.service.UserAuthService;

/**
 * Base injury Reporting controller
 * 
 * @author dGrimes
 */
@Controller
public class InjuryReportingController {

    private UserAuthService userAuthService;

    @Autowired
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    /**
     * GET request for /injuryreporting. return the injuryreporting.jsp page. If
     * the currently logged in user is trying create an injury report for
     * himself, redirect to the injuredemployee page
     * 
     * @return
     */
    @RequestMapping (value = "/injuryreporting", method = RequestMethod.GET)
    public ModelAndView reportingGET(HttpServletRequest request,
            Integer employeeId, String firstName, String lastName) {
        String page = "injuryreporting";
        if (userAuthService.userHasId(request.getUserPrincipal().getName(),
                employeeId)) {
            page = "redirect:/injuredemployee";
        }
        return new ModelAndView(page);
    }
}
