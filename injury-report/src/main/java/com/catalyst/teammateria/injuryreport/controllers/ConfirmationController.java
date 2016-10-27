package com.catalyst.teammateria.injuryreport.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Confirmation page controller
 * 
 * @author dGrimes
 */
@Controller
public class ConfirmationController {

    private static final String URL_CONFIRMATION_ID = "/confirmation/{id}";
	private static final String VIEW_CONFIRMATION = "confirmation";
	private static final String REDIRECT_ROOT = "redirect:/";
	private static final String URL_CONFIRMATION = "/confirmation";
	private static final String REDIRECT_LOGOUT = "redirect:/logout";
	private static final String PATH_VARIABLE_ID = "id";
	private static final String CONFIRMATION_NUMBER = "CONFIRMATION_NUMBER";

	/**
     * GET request for storing a confirmation number in the session
     * 
     * @param request
     *            - HttpServletRequest
     * @param confirmationNumber
     *            - the confirmation number
     * @return redirect to logout
     */
    @RequestMapping (value = URL_CONFIRMATION_ID, method = RequestMethod.GET)
    public ModelAndView reviewGET(HttpServletRequest request,
            @PathVariable (PATH_VARIABLE_ID) Integer id) {
        request.getSession().setAttribute(CONFIRMATION_NUMBER,
        		id);
        return new ModelAndView(REDIRECT_LOGOUT);
    }

    /**
     * GET request for either the confirmation page or a redirect to the root
     * 
     * @param request
     *            - HttpServletRequest
     * @return confirmation page or the root page
     */
    @RequestMapping (value = URL_CONFIRMATION, method = RequestMethod.GET)
    public ModelAndView confirmationGET(HttpServletRequest request) {
        if (request.getAttribute(CONFIRMATION_NUMBER) != null) {
            return new ModelAndView(VIEW_CONFIRMATION);
        } else {
            return new ModelAndView(REDIRECT_ROOT);
        }
    }
}
