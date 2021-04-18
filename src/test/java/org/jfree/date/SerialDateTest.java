/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * SerialDateTest.java
 * -------------------
 * (C) Copyright 2001-2014, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: SerialDateTest.java,v 1.7 2007/11/02 17:50:35 taqua Exp $
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 25-Jun-2002 : Removed unnecessary import (DG);
 * 24-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Added serialization test (DG);
 * 05-Jan-2005 : Added test for bug report 1096282 (DG);
 *
 */

package org.jfree.date;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Some JUnit tests for the {@link SerialDate} class.
 */
public class SerialDateTest extends TestCase {
    
    /** Date representing November 9. */
    private SerialDate nov9Y2001;
    
    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public SerialDateTest(final String name) {
        super(name);
    }
    
    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(SerialDateTest.class);
    }
    
    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = SerialDate.createInstance(9, MonthConstants.NOVEMBER, 2001);
    }
    
    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final SerialDate jan9Y2002 = SerialDate.addMonths(2, this.nov9Y2001);
        final SerialDate answer = SerialDate.createInstance(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }
    
    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final SerialDate d1 = SerialDate.createInstance(5, MonthConstants.OCTOBER, 2003);
        final SerialDate d2 = SerialDate.addMonths(2, d1);
        assertEquals(d2, SerialDate.createInstance(5, MonthConstants.DECEMBER, 2003));
    }
    
    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo1Jan2003() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.JANUARY, 2003);
        final SerialDate d2 = SerialDate.addMonths(0, d1);
        assertEquals(d2, d1);
    }
    
    /**
     * Monday preceding Friday 9 November 2001 should be 5 November.
     */
    public void testMondayPrecedingFriday9Nov2001() {
        SerialDate mondayBefore = SerialDate.getPreviousDayOfWeek(SerialDate.MONDAY, this.nov9Y2001);
        assertEquals(5, mondayBefore.getDayOfMonth());
    
        SerialDate previousDayOfWeek1 = SerialDate.getPreviousDayOfWeek(SerialDate.MONDAY, this.nov9Y2001).getPreviousDayOfWeek(SerialDate.MONDAY);
        assertEquals(29, previousDayOfWeek1.getDayOfMonth());
    
        SerialDate saturdayAfter = SerialDate.getPreviousDayOfWeek(SerialDate.SATURDAY, nov9Y2001);
        assertEquals(3, saturdayAfter.getDayOfMonth());
    
        
    }
    
    /**
     * Monday following Friday 9 November 2001 should be 12 November.
     */
    public void testMondayFollowingFriday9Nov2001() {
        SerialDate mondayAfter = SerialDate.getFollowingDayOfWeek(
                SerialDate.MONDAY, this.nov9Y2001
        );
        assertEquals(12, mondayAfter.getDayOfMonth());
    
        SerialDate instance = SerialDate.createInstance(18, SerialDate.APRIL, 2021);
        SerialDate followingDayOfWeek = instance.getFollowingDayOfWeek(SerialDate.MONDAY);
        assertEquals(19, followingDayOfWeek.getDayOfMonth());
    
        SerialDate mondayAfter1 = SerialDate.getFollowingDayOfWeek(SerialDate.SUNDAY, nov9Y2001);
        assertEquals(11, mondayAfter1.getDayOfMonth());
    }
    
    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    public void testMondayNearestFriday9Nov2001() {
        SerialDate mondayNearest = SerialDate.getNearestDayOfWeek(
                SerialDate.MONDAY, this.nov9Y2001
        );
        assertEquals(12, mondayNearest.getDayOfMonth());
    }
    
    /**
     * The Monday nearest to 22nd January 1970 falls on the 19th.
     */
    public void testMondayNearest22Jan1970() {
        SerialDate jan22Y1970 = SerialDate.createInstance(22, MonthConstants.JANUARY, 1970);
        SerialDate mondayNearest = SerialDate.getNearestDayOfWeek(SerialDate.MONDAY, jan22Y1970);
        assertEquals(19, mondayNearest.getDayOfMonth());
        
        
        SerialDate jan22Y1970Other = SerialDate.createInstance(22, MonthConstants.JANUARY, 1970);
        SerialDate nearestDayOfWeek = jan22Y1970Other.getNearestDayOfWeek(SerialDate.MONDAY);
        assertEquals(19, nearestDayOfWeek.getDayOfMonth());
    }
    
    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {
        
        final String test = SerialDate.weekdayCodeToString(SerialDate.SATURDAY);
        assertEquals("星期六", test);
        
    }
    
    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the
     * default locale doesn't use English weekday names...devise a better test!
     * 由于当前环境不是中文环境, 所以需要替换原有的测试用例.
     */
    public void testStringToWeekday() {
        
        int weekday = SerialDate.stringToWeekdayCode("周三");
        assertEquals(SerialDate.WEDNESDAY, weekday);
        
        weekday = SerialDate.stringToWeekdayCode(" 周三 ");
        assertEquals(SerialDate.WEDNESDAY, weekday);
        
        weekday = SerialDate.stringToWeekdayCode("星期三");
        assertEquals(SerialDate.WEDNESDAY, weekday);
    }
    
    /**
     * Test the conversion of a string to a month.  Note that this test will fail if the default
     * locale doesn't use English month names...devise a better test!
     * 由于不是英文环境, 所以测试会失败》
     */
    public void testStringToMonthCode() {
        
        int m = SerialDate.stringToMonthCode("一月");
        assertEquals(MonthConstants.JANUARY, m);
        
        m = SerialDate.stringToMonthCode(" 一月 ");
        assertEquals(MonthConstants.JANUARY, m);
        
        m = SerialDate.stringToMonthCode("1月");
        assertEquals(MonthConstants.JANUARY, m);
    }
    
    /**
     * Tests the conversion of a month code to a string.
     */
    public void testMonthCodeToStringCode() {
        
        final String test = SerialDate.monthCodeToString(MonthConstants.DECEMBER);
        assertEquals("十二月", test);
        
        String shortMonthName = SerialDate.monthCodeToString(MonthConstants.DECEMBER, true);
        assertEquals("12月", shortMonthName);
    }
    
    public void testGetMonths() {
        String[] months = SerialDate.getMonths();
        assertEquals(13, months.length);
        
        String[] months1 = SerialDate.getMonths(false);
        assertEquals(months1[0], months[0]);
        
        String[] months2 = SerialDate.getMonths(true);
        assertEquals("1月", months2[0]);
        assertEquals("2月", months2[1]);
        assertEquals("3月", months2[2]);
        assertEquals("4月", months2[3]);
        assertEquals("5月", months2[4]);
        assertEquals("6月", months2[5]);
        assertEquals("7月", months2[6]);
        assertEquals("8月", months2[7]);
        assertEquals("9月", months2[8]);
        assertEquals("10月", months2[9]);
        assertEquals("11月", months2[10]);
        assertEquals("12月", months2[11]);
    }
    
    /**
     * 1900 is not a leap year.
     */
    public void testIsNotLeapYear1900() {
        System.out.println("4");
        assertFalse(SerialDate.isLeapYear(1900));
    }
    
    /**
     * 2000 is a leap year.
     */
    public void testIsLeapYear2000() {
        assertTrue(SerialDate.isLeapYear(2000));
    }
    
    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    public void testLeapYearCount1899() {
        assertEquals(SerialDate.leapYearCount(1899), 0);
    }
    
    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    public void testLeapYearCount1903() {
        assertEquals(SerialDate.leapYearCount(1903), 0);
    }
    
    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    public void testLeapYearCount1904() {
        assertEquals(SerialDate.leapYearCount(1904), 1);
    }
    
    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    public void testLeapYearCount1999() {
        assertEquals(SerialDate.leapYearCount(1999), 24);
    }
    
    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    public void testLeapYearCount2000() {
        assertEquals(SerialDate.leapYearCount(2000), 25);
    }
    
    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
        
        SerialDate d1 = SerialDate.createInstance(15, 4, 2000);
        SerialDate d2 = null;
        
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();
            
            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (SerialDate) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(d1, d2);
        
    }
    
    /**
     * A test for bug report 1096282 (now fixed).
     */
    public void test1096282() {
        SerialDate d = SerialDate.createInstance(29, 2, 2004);
        d = SerialDate.addYears(1, d);
        SerialDate expected = SerialDate.createInstance(28, 2, 2005);
        assertTrue(d.isOn(expected));
    }
    
    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        SerialDate d1 = SerialDate.createInstance(31, 5, 2004);
        
        SerialDate d2 = SerialDate.addMonths(1, d1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth());
        assertEquals(2004, d2.getYYYY());
        
        SerialDate d3 = SerialDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth());
        assertEquals(2004, d3.getYYYY());
        
        SerialDate d4 = SerialDate.addMonths(1, SerialDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7, d4.getMonth());
        assertEquals(2004, d4.getYYYY());
    }
    
    public void testIsValidMonthCode() {
        assertTrue(SerialDate.isValidMonthCode(1));
        assertTrue(SerialDate.isValidMonthCode(2));
        assertTrue(SerialDate.isValidMonthCode(3));
        assertTrue(SerialDate.isValidMonthCode(4));
        assertTrue(SerialDate.isValidMonthCode(5));
        assertTrue(SerialDate.isValidMonthCode(6));
        assertTrue(SerialDate.isValidMonthCode(7));
        assertTrue(SerialDate.isValidMonthCode(8));
        assertTrue(SerialDate.isValidMonthCode(9));
        assertTrue(SerialDate.isValidMonthCode(10));
        assertTrue(SerialDate.isValidMonthCode(11));
        assertTrue(SerialDate.isValidMonthCode(12));
        assertFalse(SerialDate.isValidMonthCode(13));
    }
    
    public void testIsValidWeekInMonthCode() {
        assertTrue(SerialDate.isValidWeekInMonthCode(1));
        assertTrue(SerialDate.isValidWeekInMonthCode(2));
        assertTrue(SerialDate.isValidWeekInMonthCode(3));
        assertTrue(SerialDate.isValidWeekInMonthCode(4));
        assertFalse(SerialDate.isValidWeekInMonthCode(5));
    }
    
    public void testMonthCodeToQuarter() {
        // 一季度
        assertEquals(1, SerialDate.monthCodeToQuarter(MonthConstants.JANUARY));
        assertEquals(1, SerialDate.monthCodeToQuarter(MonthConstants.FEBRUARY));
        assertEquals(1, SerialDate.monthCodeToQuarter(MonthConstants.MARCH));
        
        // 二季度
        assertEquals(2, SerialDate.monthCodeToQuarter(MonthConstants.APRIL));
        assertEquals(2, SerialDate.monthCodeToQuarter(MonthConstants.MAY));
        assertEquals(2, SerialDate.monthCodeToQuarter(MonthConstants.JUNE));
        
        // 三季度
        assertEquals(3, SerialDate.monthCodeToQuarter(MonthConstants.JULY));
        assertEquals(3, SerialDate.monthCodeToQuarter(MonthConstants.AUGUST));
        assertEquals(3, SerialDate.monthCodeToQuarter(MonthConstants.SEPTEMBER));
        
        // 四季度
        assertEquals(4, SerialDate.monthCodeToQuarter(MonthConstants.OCTOBER));
        assertEquals(4, SerialDate.monthCodeToQuarter(MonthConstants.NOVEMBER));
        assertEquals(4, SerialDate.monthCodeToQuarter(MonthConstants.DECEMBER));
    }
    
    public void testIsValidWeekdayCode() {
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.SUNDAY));
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.MONDAY));
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.TUESDAY));
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.WEDNESDAY));
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.SATURDAY));
        assertTrue(SerialDate.isValidWeekdayCode(SerialDate.SUNDAY));
        assertFalse(SerialDate.isValidWeekdayCode(99));
    }
    
    public void testGetEndOfCurrentMonth() {
        SerialDate endOfCurrentMonth = nov9Y2001.getEndOfCurrentMonth(nov9Y2001);
        assertEquals(30, endOfCurrentMonth.getDayOfMonth());
    
        SerialDate serialDate = SerialDate.createInstance(18, 4, 2021);
        SerialDate endOfCurrentMonth1 = serialDate.getEndOfCurrentMonth(serialDate);
        assertEquals(30, endOfCurrentMonth1.getDayOfMonth());
    }
    
    public void testWeekInMonthToString() {
        assertEquals("First", SerialDate.weekInMonthToString(SerialDate.FIRST_WEEK_IN_MONTH));
        assertEquals("Second", SerialDate.weekInMonthToString(SerialDate.SECOND_WEEK_IN_MONTH));
        assertEquals("Third", SerialDate.weekInMonthToString(SerialDate.THIRD_WEEK_IN_MONTH));
        assertEquals("Fourth", SerialDate.weekInMonthToString(SerialDate.FOURTH_WEEK_IN_MONTH));
        assertEquals("Last", SerialDate.weekInMonthToString(SerialDate.LAST_WEEK_IN_MONTH));
    }
    
    public void testRelativeToString() {
        assertEquals("Preceding", SerialDate.relativeToString(SerialDate.PRECEDING));
        assertEquals("Nearest", SerialDate.relativeToString(SerialDate.NEAREST));
        assertEquals("Following", SerialDate.relativeToString(SerialDate.FOLLOWING));
    }
    
    public void testGetDescription() {
        String description = "6666";
        nov9Y2001.setDescription(description);
        assertEquals(description, nov9Y2001.getDescription());
    }
    
    public void testToString() {
        assertEquals("9-十一月-2001", nov9Y2001.toString());
        
        SerialDate serialDate = SerialDate.createInstance(18, SerialDate.APRIL, 2021);
        assertEquals("18-四月-2021", serialDate.toString());
    }
}