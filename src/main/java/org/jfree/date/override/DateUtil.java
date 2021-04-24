package org.jfree.date.override;

import static org.jfree.date.override.Month.FEBRUARY;

public class DateUtil {
    
    /**
     * Determines whether or not the specified year is a leap year.
     *
     * @param yyyy  the year (in the range 1900 to 9999).
     *
     * @return <code>true</code> if the specified year is a leap year.
     */
    public static boolean isLeapYear(final int yyyy) {
        boolean fourDivide = yyyy % 4 == 0;
        boolean fourHundredDivide = yyyy % 400 == 0;
        boolean hundredDivide = yyyy % 100 == 0;
        return fourDivide && (!hundredDivide || fourHundredDivide);
    }
    
    /**
     * Returns the number of leap years from 1900 to the specified year
     * INCLUSIVE.
     * <P>
     * Note that 1900 is not a leap year.
     *
     * @param yyyy  the year (in the range 1900 to 9999).
     *
     * @return the number of leap years from 1900 to the specified year.
     */
    public static int leapYearCount(final int yyyy) {
        
        final int leap4 = (yyyy - 1896) / 4;
        final int leap100 = (yyyy - 1800) / 100;
        final int leap400 = (yyyy - 1600) / 400;
        return leap4 - leap100 + leap400;
        
    }
    
    /**
     * Returns the number of the last day of the month, taking into account
     * leap years.
     *
     * @param month  the month.
     * @param yyyy  the year (in the range 1900 to 9999).
     *
     * @return the number of the last day of the month.
     */
    public static int lastDayOfMonth(final Month month, final int yyyy) {
        if (FEBRUARY.equals(month) && DateUtil.isLeapYear(yyyy)) {
            return month.lastDay() + 1;
        } else {
            return month.lastDay();
        }
    }
}
