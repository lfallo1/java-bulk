package com.catalyst.teammateria.injuryreport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base Controller for the admin review page
 * 
 * @author dGrimes
 */
@Controller
public class AdminReviewController {

    /**
     * Main controller to send you to the admin review page
     * @return
     */
    @RequestMapping (value = "/adminreview", method = RequestMethod.GET)
    public ModelAndView getAdminReview() {
        return new ModelAndView("adminreview");
    }
}
