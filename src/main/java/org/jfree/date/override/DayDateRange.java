package org.jfree.date.override;

public enum DayDateRange {
    /**
     * time > targetTime and time < targetTime
     */
    OPEN {
        @Override
        public boolean isIn(int day, int left, int right) {
            return day > left && day < right;
        }
    },
    /**
     * time >= targetTime and time < targetTime
     */
    CLOSED_LEFT {
        @Override
        public boolean isIn(int day, int left, int right) {
            return day >= left && day < right;
        }
    },
    /**
     * time > targetTime and time <= targetTime
     */
    CLOSED_RIGHT {
        @Override
        public boolean isIn(int day, int left, int right) {
            return day > left && day <= right;
        }
    },
    /**
     * time >= targetTime and time <= targetTime
     */
    CLOSED {
        @Override
        public boolean isIn(int day, int left, int right) {
            return day >= left && day <= right;
        }
    },
    ;
    
    public abstract boolean isIn(int day, int left, int right);
}
