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
    
    public static DayOfWeek parse(String s) {
        final String[] shortWeekdayNames
                = DATE_FORMAT_SYMBOLS.getShortWeekdays();
        final String[] weekDayNames = DATE_FORMAT_SYMBOLS.getWeekdays();
        
        s = s.trim();
        for (DayOfWeek day : values()) {
            if (s.equalsIgnoreCase(shortWeekdayNames[day.index])
                || s.equalsIgnoreCase(weekDayNames[day.index])) {
                return day;
            }
        }
        throw new IllegalArgumentException(String.format("%s is not a valid weekday string.", s));
    }
}
