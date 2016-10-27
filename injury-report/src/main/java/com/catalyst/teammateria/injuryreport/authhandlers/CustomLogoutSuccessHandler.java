package com.catalyst.teammateria.injuryreport.authhandlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Simple custom success Handler for logging out
 * 
 * @author dGrimes
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
        implements LogoutSuccessHandler {

	private static final String URI_LOGIN = "/login";
	private static final String URI_CONFIRMATION = "/confirmation";
	private static final String KEY_CONFIRMATION_NUMBER = "CONFIRMATION_NUMBER";

	/**
	 * On a successful logout, check to see if the user is logging out due to a
	 * successful injury report submission (in which case the user should be
	 * sent to a confirmation page), or if it is a general logout.
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Integer confirmationNumber = (Integer) request.getSession().getAttribute(
				KEY_CONFIRMATION_NUMBER);
		request.getSession().invalidate();
		if (confirmationNumber != null) {
			request.setAttribute(KEY_CONFIRMATION_NUMBER, confirmationNumber);
			request.getRequestDispatcher(URI_CONFIRMATION).forward(request,
					response);
		} else {
			request.getRequestDispatcher(URI_LOGIN).forward(request, response);
		}
	}
}
