package com.catalsyt.teammateria.timeclock.filters;

import javax.servlet.http.HttpSession;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;

/**
 * Directs the user to different pages depending on their user role.
 * @author aDietrich
 *
 */
public final class AuthSuccessHandler {

    private static final String TIMETRACKING = "/timetracking";

    private static final String ADMINSPLASH = "/adminsplash";

    private static final String USER_USER_ROLE = "User";

    private static final String ADMIN_USER_ROLE = "Admin";

    private static final String CURRENT_USER_IN_SESSION = "currentUser";

    private AuthSuccessHandler() {
    }

    /**
     * Determines the target url depending on the role of the user in session
     * 
     * @param session
     * @return
     */
    public static String determineTargetUrl(HttpSession session) {
        String role = ((User)session.getAttribute(CURRENT_USER_IN_SESSION))
                .getRole().getUserRole();
        String targetUrl = "";
        if (role.contains(ADMIN_USER_ROLE)) {
            targetUrl = ADMINSPLASH;
        } else if (role.contains(USER_USER_ROLE)) {
            targetUrl = TIMETRACKING;
        }
        return targetUrl;
    }
}