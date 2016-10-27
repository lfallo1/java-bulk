package com.catalyst.teammateria.injuryreport.utils;


/**
 * Date handling utility
 * 
 * @author dGrimes
 */
public final class DateUtil {

    /**
     * private constructor to avoid instantiation
     */
    private DateUtil() {
    }

    /**
     * Convert a date from MM/DD/YYYY format to YYYY-MM-DD
     * 
     * @param date
     * @return
     */
    public static String convertDateFormat(String date) {
        String[] parts = date.split("/");
        StringBuilder sb = new StringBuilder();
        sb.append(parts[2]);
        sb.append("-");
        sb.append(parts[0]);
        sb.append("-");
        sb.append(parts[1]);
        sb.trimToSize();
        return sb.toString();
    }
}
