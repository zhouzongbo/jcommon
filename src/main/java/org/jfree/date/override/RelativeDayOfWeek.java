package org.jfree.date.override;

public enum RelativeDayOfWeek {
    PRECEDING(-1),
    NEAREST(0),
    FOLLOWING(1),
    ;
    
    private final int index;
    
    RelativeDayOfWeek(int index) {
        this.index = index;
    }
    
    public int toInt() {
        return index;
    }
}
