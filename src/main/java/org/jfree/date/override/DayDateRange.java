package org.jfree.date.override;

public enum  DayDateRange {
    INCLUDE_NONE(0),
    INCLUDE_FIRST(1),
    INCLUDE_SECOND(2),
    INCLUDE_BOTH(3),
    ;
    private final int index;
    
    DayDateRange(int index) {
        this.index = index;
    }
    
    public int toInt() {
        return index;
    }
}
