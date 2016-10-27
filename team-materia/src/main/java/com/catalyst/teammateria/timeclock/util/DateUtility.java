package com.catalyst.teammateria.timeclock.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 * A utility class for getting specific Dates
 * 
 * @author aDietrich
 */
public final class DateUtility {

    /**
     * Private constructor for DateUtility
     */
    private DateUtility() {
    }

    /**
     * TODO return the date of the start of the week based on the date paramater
     * passed in; or sunday if the current date is a sunday. (i.e., if the date
     * is Monday October 2nd, this method would return a date object for Sunday
     * October 1st)
     * 
     * @param date
     * @return
     */
    public static LocalDate getStartOfWeek(LocalDate date) {
        LocalDate sunday = date;
        while (sunday.getDayOfWeek() != DateTimeConstants.SUNDAY) {
            sunday = sunday.minusDays(1);
        }
        return sunday;
    }

    /**
     * TODO return the date of the end of the week based on the date paramater
     * passed in, or saturday if the date paramater is a saturday. (i.e., if the
     * date is Thursday October 5th, this method would return a date object for
     * Saturday October 7th)
     * 
     * @param date
     * @return
     */
    public static LocalDate getEndOfWeek(LocalDate date) {
        LocalDate saturday = date;
        while (saturday.getDayOfWeek() != DateTimeConstants.SATURDAY) {
            saturday = saturday.plusDays(1);
        }
        return saturday;
    }
}
