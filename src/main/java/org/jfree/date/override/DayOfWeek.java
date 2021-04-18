package org.jfree.date.override;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

public enum DayOfWeek {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    ;
    
    /** Date format symbols. */
    public static final DateFormatSymbols
            DATE_FORMAT_SYMBOLS = new SimpleDateFormat().getDateFormatSymbols();
    private final int index;
    
    DayOfWeek(int dayOfWeekIndex) {
        this.index = dayOfWeekIndex;
    }
    
    public int toInt() {
        return index;
    }
}
