package org.jfree.date.override;

import org.jfree.date.DayDate;
import org.jfree.date.SpreadsheetDate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SpreadSheetDateFactory extends DayDateFactory {
    
    @Override
    protected DayDate _makeDate(int ordinal) {
        return new SpreadsheetDate(ordinal);
    }
    
    @Override
    protected DayDate _makeDate(int day, Month month, int year) {
        return new SpreadsheetDate(day, month, year);
    }
    
    @Override
    protected DayDate _makeDate(int day, int month, int year) {
        return new SpreadsheetDate(day, Month.make(month), year);
    }
    
    @Override
    protected DayDate _makeDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return new SpreadsheetDate(calendar.get(Calendar.DATE), Month.make(calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR));
    }
    
    @Override
    protected int _getMinYear() {
        return SpreadsheetDate.MINIMUM_YEAR_SUPPORTED;
    }
    
    @Override
    protected int _getMaxYear() {
        return SpreadsheetDate.MAXIMUM_YEAR_SUPPORTED;
    }
}
