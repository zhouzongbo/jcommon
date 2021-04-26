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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jfree.date.override.DateUtil;
import org.jfree.date.override.DayDateFactory;
import org.jfree.date.override.DayOfWeek;
import org.jfree.date.override.Month;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Some JUnit tests for the {@link DayDate} class.
 */
public class DayDateTest extends TestCase {

    /** Date representing November 9. */
    private DayDate nov9Y2001;

    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public DayDateTest(final String name) {
        super(name);
    }

    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(DayDateTest.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = DayDateFactory.makeDate(9, Month.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final DayDate jan9Y2002 = this.nov9Y2001.plusMonths(Month.FEBRUARY);
        final DayDate answer = DayDateFactory.makeDate(9, Month.JANUARY, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDateFactory.makeDate(5, Month.OCTOBER, 2003);
        final DayDate d2 = d1.plusMonths(Month.FEBRUARY);
        assertEquals(d2, DayDateFactory.makeDate(5, Month.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
//    public void testAddMonthsTo1Jan2003() {
//        final SerialDate d1 = SerialDate.createInstance(1, Month.JANUARY, 2003);
//        final SerialDate d2 = SerialDate.addMonths(0, d1);
//        assertEquals(d2, d1);
//    }

    /**
     * Monday preceding Friday 9 November 2001 should be 5 November.
     */
    public void testMondayPrecedingFriday9Nov2001() {
        DayDate mondayBefore = DayDate.getPreviousDayOfWeek(DayOfWeek.MONDAY.toInt(), this.nov9Y2001);
        assertEquals(5, mondayBefore.getDayOfMonth());

        DayDate previousDayOfWeek1 = DayDate.getPreviousDayOfWeek(DayOfWeek.MONDAY.toInt(), this.nov9Y2001).getPreviousDayOfWeek(DayOfWeek.MONDAY.toInt());
        assertEquals(29, previousDayOfWeek1.getDayOfMonth());

        DayDate saturdayAfter = DayDate.getPreviousDayOfWeek(DayOfWeek.SATURDAY.toInt(), nov9Y2001);
        assertEquals(3, saturdayAfter.getDayOfMonth());


    }

    /**
     * Monday following Friday 9 November 2001 should be 12 November.
     */
    public void testMondayFollowingFriday9Nov2001() {
        DayDate mondayAfter = DayDate.getFollowingDayOfWeek(
                DayOfWeek.MONDAY.toInt(), this.nov9Y2001
        );
        assertEquals(12, mondayAfter.getDayOfMonth());

        DayDate instance = DayDateFactory.makeDate(18, Month.APRIL, 2021);
        DayDate followingDayOfWeek = instance.getFollowingDayOfWeek(DayOfWeek.MONDAY.toInt());
        assertEquals(19, followingDayOfWeek.getDayOfMonth());

        DayDate mondayAfter1 = DayDate.getFollowingDayOfWeek(DayOfWeek.SUNDAY.toInt(), nov9Y2001);
        assertEquals(11, mondayAfter1.getDayOfMonth());
    }

    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    public void testMondayNearestFriday9Nov2001() {
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(
                DayOfWeek.MONDAY.toInt(), this.nov9Y2001
        );
        assertEquals(12, mondayNearest.getDayOfMonth());
    }

    /**
     * The Monday nearest to 22nd January 1970 falls on the 19th.
     */
    public void testMondayNearest22Jan1970() {
        DayDate jan22Y1970 = DayDateFactory.makeDate(22, Month.JANUARY, 1970);
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(DayOfWeek.MONDAY.toInt(), jan22Y1970);
        assertEquals(19, mondayNearest.getDayOfMonth());


        DayDate jan22Y1970Other = DayDateFactory.makeDate(22, Month.JANUARY, 1970);
        DayDate nearestDayOfWeek = jan22Y1970Other.getNearestDayOfWeek(DayOfWeek.MONDAY.toInt());
        assertEquals(19, nearestDayOfWeek.getDayOfMonth());
    }

    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {

        final String test = DayOfWeek.weekdayCodeToString(DayOfWeek.SATURDAY.toInt());
        assertEquals("星期六", test);

    }

    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the
     * default locale doesn't use English weekday names...devise a better test!
     * 由于当前环境不是中文环境, 所以需要替换原有的测试用例.
     */
    public void testStringToWeekday() {

        DayOfWeek weekday = DayOfWeek.parse("周三");
        assertEquals(DayOfWeek.WEDNESDAY.toInt(), weekday.toInt());
    
        DayOfWeek weekday1 = DayOfWeek.parse(" 周三 ");
        assertEquals(DayOfWeek.WEDNESDAY.toInt(), weekday1.toInt());
    
        DayOfWeek weekday2 = DayOfWeek.parse("星期三");
        assertEquals(DayOfWeek.WEDNESDAY.toInt(), weekday2.toInt());
    }

    /**
     * Test the conversion of a string to a month.  Note that this test will fail if the default
     * locale doesn't use English month names...devise a better test!
     * 由于不是英文环境, 所以测试会失败》
     */
    public void testStringToMonthCode() {

        Month m = Month.parse("一月");
        assertEquals(Month.JANUARY, m);

        m = Month.parse(" 一月 ");
        assertEquals(Month.JANUARY, m);

        m = Month.parse("1月");
        assertEquals(Month.JANUARY, m);
        
        m = Month.parse("1");
        assertEquals(Month.JANUARY, m);
    }

    /**
     * Tests the conversion of a month code to a string.
     */
    public void testMonthCodeToStringCode() {

        final String test = Month.DECEMBER.toString();
        assertEquals("十二月", test);

        String shortMonthName = Month.DECEMBER.toShortString();
        assertEquals("12月", shortMonthName);
    }

    public void testGetMonths() {
        String[] months = DayOfWeek.getMonths();
        String[] months1 = DayOfWeek.getMonths();
        assertEquals(13, months.length);
        for (int i = 0; i < months.length; i++) {
            assertEquals(months1[i], months[i]);
        }
        
        String[] months2 = DayOfWeek.getShortMonths();
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
        assertFalse(DateUtil.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year.
     */
    public void testIsLeapYear2000() {
        assertTrue(DateUtil.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    public void testLeapYearCount1899() {
        assertEquals(DateUtil.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    public void testLeapYearCount1903() {
        assertEquals(DateUtil.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    public void testLeapYearCount1904() {
        assertEquals(DateUtil.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    public void testLeapYearCount1999() {
        assertEquals(DateUtil.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    public void testLeapYearCount2000() {
        assertEquals(DateUtil.leapYearCount(2000), 25);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        DayDate d1 = DayDateFactory.makeDate(15, Month.APRIL, 2000);
        DayDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DayDate) in.readObject();
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
        DayDate d = DayDateFactory.makeDate(29, Month.FEBRUARY, 2004);
        d = DayDate.addYears(1, d);
        DayDate expected = DayDateFactory.makeDate(28, Month.FEBRUARY, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        DayDate d1 = DayDateFactory.makeDate(31, Month.MAY, 2004);

        DayDate d2 = d1.plusMonths(Month.JANUARY);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth().toInt());
        assertEquals(2004, d2.getYYYY());

        DayDate d3 = d1.plusMonths(Month.FEBRUARY);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth().toInt());
        assertEquals(2004, d3.getYYYY());
    
        DayDate d4 = d1.plusMonths(Month.JANUARY);
        DayDate d5 = d4.plusMonths(Month.JANUARY);
        assertEquals(30, d5.getDayOfMonth());
        assertEquals(7, d5.getMonth().toInt());
        assertEquals(2004, d5.getYYYY());
    }

    public void testGetEndOfCurrentMonth() {
        DayDate endOfCurrentMonth = nov9Y2001.getEndOfCurrentMonth(nov9Y2001);
        assertEquals(30, endOfCurrentMonth.getDayOfMonth());

        DayDate dayDate = DayDateFactory.makeDate(18, Month.APRIL, 2021);
        DayDate endOfCurrentMonth1 = dayDate.getEndOfCurrentMonth(dayDate);
        assertEquals(30, endOfCurrentMonth1.getDayOfMonth());
    }

    public void testGetDescription() {
        String description = "6666";
        nov9Y2001.setDescription(description);
        assertEquals(description, nov9Y2001.getDescription());
    }

    public void testToString() {
        assertEquals("9-十一月-2001", nov9Y2001.toString());

        DayDate dayDate = DayDateFactory.makeDate(18, Month.APRIL, 2021);
        assertEquals("18-四月-2021", dayDate.toString());
    }
    
    public void testCreateInstanceDate() {
        Date now = new Date(121, 4, 18);
        DayDate dayDate = DayDateFactory.makeDate(now);
        assertEquals(2021, dayDate.getYYYY());
        assertEquals(Month.APRIL, dayDate.getMonth());
        assertEquals(18, dayDate.getDayOfMonth());
    }
    
    public void testLastDayOfMonth() {
        // int result = Month.JANUARY.lastDay(2020);
        // assertEquals(result, DayDate.lastDayOfMonth(Month.JANUARY, 2020));
        // System.out.println(result);
    }
}