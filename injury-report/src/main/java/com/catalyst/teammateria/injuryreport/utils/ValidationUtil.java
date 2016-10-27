package com.catalyst.teammateria.injuryreport.utils;

/**
 * Validation utility for things like names, usernames, passwords etc.
 * 
 * @author dGrimes
 */
public final class ValidationUtil {

    /**
     * Private constructor to avoid having this utility instantiated
     */
    private ValidationUtil() {
    }

    /**
     * Method to check for a valid name conforming currently to [A-Za-z]+
     * 
     * @param str
     *            - the string to check
     * @return true if a valid name
     */
    public static boolean isValidName(String str) {
        return str.matches("[A-Za-z]+");
    }

    /**
     * Method to check for a valid username conforming currently to [A-Za-z0-9]+
     * 
     * @param str
     *            - the string to check
     * @return true if a valid name
     */
    public static boolean isValidUsername(String str) {
        return str.matches("[A-Za-z0-9]+");
    }
}
