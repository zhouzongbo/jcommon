package org.jfree.date.override;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12),
    ;
    private final int index;
    
    /** The serial number for 1 January 1900. */
    public static final int SERIAL_LOWER_BOUND = 2;
    
    /** The serial number for 31 December 9999. */
    public static final int SERIAL_UPPER_BOUND = 2958465;
    
    public static final int[] LAST_DAY_OF_MONTH =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    /** The number of days in a (non-leap) year up to the end of each month. */
    public static final int[] AGGREGATE_DAYS_TO_END_OF_MONTH =
            {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    
    /** The number of days in a year up to the end of the preceding month. */
    public static final int[] AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH =
            {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    
    /** The number of days in a leap year up to the end of each month. */
    public static final int[] LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_MONTH =
            {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};
    
    /**
     * The number of days in a leap year up to the end of the preceding month.
     */
    public static final int[]
            LEAP_YEAR_AGGREGATE_DAYS_TO_END_OF_PRECEDING_MONTH =
            {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};
    
    Month(int index) {
        this.index = index;
    }
    
    public static Month make(int monthIndex) {
        for (Month value : values()) {
            if (value.index == monthIndex) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid month index : " + monthIndex);
    }
    
    public int toInt() {
        return index;
    }
}
