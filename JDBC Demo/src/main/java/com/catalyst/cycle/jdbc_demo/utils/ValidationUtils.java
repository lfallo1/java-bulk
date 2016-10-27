package com.catalyst.cycle.jdbc_demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Validation methods that can be used from any part of the application.
 * 
 * @author egover
 *
 */
public class ValidationUtils {
    public static final String INVALID_NAME_CHARACTERS = "!@#$%^&*()_+={}][|\\:;\"/?.>,<";
    public static final String DATETIME_FORMAT = "MM/dd/yyyy";

    /**
     * Checks that a given string is satisfies the following criteria:
     * 
     * <pre>
     * Not null
     * Not ""
     * Not a series of whitespaces
     * Does not contain any of the following:
     * {@value #INVALID_NAME_CHARACTERS}
     * 
     * <pre>
     * @param name
     * @return An error code. {@code null} if no errors were found.
     *      empty : The string was null, empty, or just a series of whitespaces
     *      invalid_characters : The string contained any of the following :
     * {@value #INVALID_NAME_CHARACTERS}
     */
    public static String checkValidRequiredName(String name) {
        String errorCode = null;
        if (StringUtils.isBlank(name)) {
            //Input is either empty or just a series of whitespaces
            errorCode = "empty";
        } else if (StringUtils.containsAny(name, INVALID_NAME_CHARACTERS)) {
            //Input contains characters we have defined as invalid.
            errorCode = "invalid_characters";
        }
        return errorCode;
    }

    /**
     * Checks if the given string is a valid DateTime in the format: {@value #DATETIME_FORMAT}
     * @param dateInput
     * @return The error code or {@code null} if there were no errors.
     *      empty :  dateInput was {@code null}, empty, or just a series of whitespaces
     *      invalid_format : dateInput was not of the format: {@value #DATETIME_FORMAT}
     */
    public static String checkValidRequiredDate(String dateInput) {
        String errorCode = null;
 
        if (StringUtils.isBlank(dateInput)) {
          //Input is either empty or just a series of whitespaces
            errorCode = "empty";
        } else {
            try {
                //Parse the string into a DateTime object. If it fails, an exception will be thrown.
                DateTime.parse(dateInput,
                        DateTimeFormat.forPattern(DATETIME_FORMAT));
            } catch (IllegalArgumentException i) {
                errorCode = "invalid_format";
            }
        }

        return errorCode;
    }
}
