package com.catalsyt.teammateria.timeclock.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    private static final String URI_REPORTING = "/reporting";

	private static final String URI_TIMEMANAGEMENT = "/timemanagement";

	private static final String URI_ACCOUNTCONFIG = "/accountconfig";

	private static final String FWRD_SLASH = "/";

    private static final String URI_LOGIN = "/login";

    private static final String CURRENT_USER_IN_SESSION = "currentUser";

    private static final String ROLE_USER = "User";

    private static final String URI_ADMINSPLASH = "/adminsplash";

    private static final String URI_TIMETRACKING = "/timetracking";

    private static final String URI_REGISTER = "/register";

    private static final String STATIC_RESOURCES_REGEXP = ".*(css|jpg|png|js|ico)";

    private static final String RESPONSE_HEADER_EXPIRES = "Expires";

    private static final String NO_CACHE = "no-cache";

    private static final String RESPONSE_HEADER_PRAGMA = "Pragma";

    private static final String NO_CACHE_NO_STORE_MUST_REVALIDATE = "no-cache, no-store, must-revalidate";

    private static final String RESPONSE_HEADER_CACHE_CONTROL = "Cache-Control";

    private List<String> userIsRestrictedList;

    /**
     * Default constructor.
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * Filter all incoming requests <br/>
     * <br/>
     * <b>Static Resources and the Register page</b>- allow all requests <br/>
     * <br/>
     * Otherwise, if user is NOT logged in, all other requests will be forward
     * to the login page<br/>
     * <br/>
     * If user is logged, get the role of the user, and check if the requested
     * page is restricted based on the user's role<br/>
     * <br/>
     * Otherwise, forward the request
     * 
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = ((HttpServletRequest)request).getSession();
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        // Allow static resources as well as access to the register page
        if (httpRequest.getRequestURI().matches(STATIC_RESOURCES_REGEXP)
                || httpRequest.getRequestURI().startsWith(URI_REGISTER)) {
            chain.doFilter(request, response);
        } else {
            // Since all pages are considered restricted, disable caching and
            // force browser to request page from server instead of cache or
            // history buffer
            // HTTP 1.1.
            httpResponse.setHeader(RESPONSE_HEADER_CACHE_CONTROL,
                    NO_CACHE_NO_STORE_MUST_REVALIDATE);
            // HTTP 1.0. Proxies.
            httpResponse.setHeader(RESPONSE_HEADER_PRAGMA, NO_CACHE);
            httpResponse.setDateHeader(RESPONSE_HEADER_EXPIRES, 0);
            // If user not logged in, forward any request to login page
            if (session.getAttribute(CURRENT_USER_IN_SESSION) == null) {
                httpRequest.getRequestDispatcher(
                        FWRD_SLASH + httpRequest.getContextPath() + URI_LOGIN)
                        .forward(httpRequest, httpResponse);
            } else {
                String role = ((User)session
                        .getAttribute(CURRENT_USER_IN_SESSION)).getRole()
                        .getUserRole();
                // USER RESTRICTED PAGES
                if (ROLE_USER.equals(role)) {
                	boolean hasAccess = true;
                    RESTRICTED_USER_LOOP: for (String restrictedPage : userIsRestrictedList) {
                        // If the page being accessed is restricted based on the
                        // user's role,
                        // redirect the user to the home/timetracking page
                        if (httpRequest.getRequestURI().contains(restrictedPage)) {
                        	hasAccess = false;
                            httpRequest.getRequestDispatcher(
                                    FWRD_SLASH + httpRequest.getContextPath()
                                            + URI_TIMETRACKING).forward(
                                    httpRequest, httpResponse);
                            break RESTRICTED_USER_LOOP;
                        }
                    }
                	if(hasAccess){
                		chain.doFilter(request, response);
                	}
                    
                } else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    /**
     * Set list of restricted pages for each user role
     * 
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        userIsRestrictedList = new ArrayList<String>();
        userIsRestrictedList.add(URI_ADMINSPLASH);
        userIsRestrictedList.add(URI_ACCOUNTCONFIG);
        userIsRestrictedList.add(URI_TIMEMANAGEMENT);
        userIsRestrictedList.add(URI_REPORTING);
    }
}
