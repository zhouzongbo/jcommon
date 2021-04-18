package org.jfree.date.override;

public enum DayOfWeek {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    ;
    
    private final int index;
    
    DayOfWeek(int dayOfWeekIndex) {
        this.index = dayOfWeekIndex;
    }
    
    public int toInt() {
        return index;
    }
}
