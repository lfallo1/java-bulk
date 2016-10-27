package com.catalyst.teammateria.timeclock.util;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;

/**
 * A utility class that breaks down the Registration form into it's required
 * parts.
 * 
 * @author dGrimes
 */
public final class RegistrationUtil {

    private RegistrationUtil() {
    }

    /**
     * Returns a new user out of Registration Form
     * 
     * @param form
     * @return new user object
     */
    public static User separateUser(RegistrationForm form) {
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setRole(new UserRole(1, "User"));
        return user;
    }

    /**
     * Creates new password object from a user and hashed password.
     * <strong>CAUTION!</strong>

     * 
     * @param user
     *            - retrieved from {@link #separateUser(RegistrationForm)}
     * @param hash
     *            - prehashed password
     * @return new password object
     */
    public static UserPassword separatePassword(User user, String hash) {
        UserPassword password = new UserPassword();
        password.setUserId(user.getUserId());
        password.setUserHash(hash);
        return password;
    }
}
