package com.catalyst.teammateria.timeclock.util;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * A utility class for validation of strings
 * 
 * @author dGrimes
 */
public final class ValidationUtil {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private static final String NUMBER_REGEX = "[\\d]+";

    private static final String ALPHA_REGEX = "[A-Za-z]+";

    private static final String ALPHA_NUM_REGEX = "[A-Za-z0-9]+";

    private static final String PASSWORD_REGEX = "[a-z]+";

    private ValidationUtil() {
    }

    /**
     * Checks the string for digits
     * 
     * @param string
     * @return true if only 0-9
     */
    public static boolean isNumber(String string) {
        return string.matches(NUMBER_REGEX);
    }

    /**
     * Checks the string for alphabetical characters
     * 
     * @param string
     * @return true if only A-Z case insensitive
     */
    public static boolean isAlpha(String string) {
        return string.matches(ALPHA_REGEX);
    }

    /**
     * Checks the string for alphanumeric characters
     * 
     * @param string
     * @return true if only A-Z case insensitive and/or 0-9
     */
    public static boolean isAlphaNumeric(String string) {
        return string.matches(ALPHA_NUM_REGEX);
    }

    /**
     * Uses org.apache.commons.validator.routines.EmailValidator to verify email
     * 
     * @param string
     * @return true if valid email
     */
    public static boolean isEmail(String string) {
        return EmailValidator.getInstance().isValid(string);
    }

    /**
     * Checks that the password meets minimum requirements
     * 
     * @param string
     * @return true if &gt;= 8 chars and at least one capital letter, number or
     *         special character
     */
    public static boolean isValidPassword(String string) {
        return string.length() >= MIN_PASSWORD_LENGTH
                && string.replaceAll(PASSWORD_REGEX, "").length() > 0;
    }
}
