package org.jfree.date.override;

import org.jfree.date.DayDate;

/**
 *
 */
public abstract class DayDateFactory {
    
    /**
     * TODO 从父类初始化子类，可能会引发死锁
     */
    private static DayDateFactory factory = new SpreadSheetDateFactory();
    
    public static void setInstance(DayDateFactory factory) {
        DayDateFactory.factory = factory;
    }
    
    protected abstract DayDate _makeDate(int ordinal);
    protected abstract DayDate _makeDate(int day, Month month, int year);
    protected abstract DayDate _makeDate(int day, int month, int year);
    protected abstract DayDate _makeDate(java.util.Date date);
    protected abstract int _getMinYear();
    protected abstract int _getMaxYear();
    
    public static DayDate makeDate(int ordinal) {
        return factory._makeDate(ordinal);
    }
    
    public static DayDate makeDate(int day, Month month, int year) {
        return factory._makeDate(day, month, year);
    }
    
    public static DayDate makeDate(int day, int month, int year) {
        return factory._makeDate(day, month, year);
    }
    
    public static DayDate makeDate(java.util.Date date) {
        return factory._makeDate(date);
    }
    
    public static int getMinYear() {
        return factory._getMinYear();
    }
    
    public static int getMaxYear() {
        return factory._getMaxYear();
    }
}
