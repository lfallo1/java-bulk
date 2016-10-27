package com.catalyst.teammateria.injuryreport.authhandlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Handler for failed login attempts.
 * 
 * @author lfallon
 *
 */
public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private static final String URI_LOGIN_FAILED = "/loginFailed";
	private static final String KEY_LAST_USERNAME = "LAST_USERNAME";
	private static final String KEY_ERROR_TYPE = "error_type";
	private static final String BAD_CREDENTIALS_ERROR = "Bad credentials";
	private static final String USER_IS_DISABLED_ERROR = "User is disabled";
	private String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

	/**
	 * Gets the error type (account locked or bad credentials), and also stores
	 * the failed login username in session.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (USER_IS_DISABLED_ERROR.equals(exception.getMessage())) {
			session.setAttribute(KEY_ERROR_TYPE, USER_IS_DISABLED_ERROR);
		} else {
			session.setAttribute(KEY_ERROR_TYPE, BAD_CREDENTIALS_ERROR);
		}
		session.setAttribute(KEY_LAST_USERNAME,
				request.getParameter(formUsernameKey));
		request.getRequestDispatcher(URI_LOGIN_FAILED).forward(request,
				response);
	}
}
