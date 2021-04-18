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
