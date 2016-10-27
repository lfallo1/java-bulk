package com.catalyst.teammateria.injuryreport.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main controller
 * 
 * @author lfallon
 *
 */
@Controller
public class MainController {

	private static final String MAPPING_ROOT_URL = "/";
	private static final String ADMINSPLASH_PAGE = "adminsplash";
	private static final String INJURED_EMPLOYEE_PAGE = "injuredemployee";
	private static final String ROLE_USER = "ROLE_USER";

	/**
	 * Handles all get requests with url pattern of "/" If user has role
	 * ROLE_USER send to injuredemployee.jsp If user has role ROLE_ADMIN forward
	 * to adminsplash.jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = MAPPING_ROOT_URL, method = RequestMethod.GET)
	public ModelAndView mainGET(HttpServletRequest request) {
		if (request.isUserInRole(ROLE_USER)) {
			return new ModelAndView(INJURED_EMPLOYEE_PAGE);
		} else {
			return new ModelAndView(ADMINSPLASH_PAGE);
		}
	}
}
