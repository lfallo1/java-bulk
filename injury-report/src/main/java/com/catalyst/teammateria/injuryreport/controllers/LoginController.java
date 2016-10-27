package com.catalyst.teammateria.injuryreport.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login controller
 * 
 * @author lfallon
 *
 */
@Controller
public class LoginController {
	private static final String MAPPING_LOGIN = "/loginFailed";
	private static final String REDIRECT_TO_HOME_CTRL = "redirect:/";
	private static final String LOGIN_PAGE = "login";
	private static final String MAPPING_LOGIN_GET = "/login";

	/**
	 * Either login in (or if logged in go to root)
	 * 
	 * @param request
	 *            - HttpServletRequest to grab info from
	 * @return login or root
	 */
	@RequestMapping(value = MAPPING_LOGIN_GET, method = RequestMethod.GET)
	public ModelAndView loginGET(HttpServletRequest request) {
		if (request.getUserPrincipal() == null) {
			return new ModelAndView(LOGIN_PAGE);
		} else {
			return new ModelAndView(REDIRECT_TO_HOME_CTRL);
		}
	}

	/**
	 * Invoked after a failed login attempt from AuthenticationFailureHandler,
	 * and sends user back to login page
	 * 
	 * @return
	 */
	@RequestMapping(value = MAPPING_LOGIN, method = RequestMethod.POST)
	public ModelAndView loginPOST() {
		return new ModelAndView(LOGIN_PAGE);
	}
}
