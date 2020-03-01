package com.johnnyly.hackm2020;

//Code intended to use to help detect dates from String inputs
//
//
//
//


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public String returnDate(String input) {
        String string = "";
        int m = 0;
        int d = 0;
        int y = 0;
        Boolean MonthCheck = false;
        Boolean DayCheck = false;
        Boolean YearCheck = false;

        String Months[]= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String SMonths[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String SSMonths[]= {"1/", "2/", "3/", "4/", "5/", "6/", "7/", "8/", "9/", "10/", "11/", "12/"};

        String Day[] = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31st" };
        String SDay[] = {"1,", "2,", "3,", "4,", "5,", "6,", "7,", "8,", "9,", "10,", "11,", "12,", "13,", "14,", "15,", "16,", "17,", "18,", "19,", "20,", "21,", "22,", "23,", "24,", "25,", "26,", "27,", "28,", "29,", "30,", "31," };
        String SSDay[] = {"/1/", "/2/", "/3/", "/4/", "/5/", "/6/", "/7/", "/8/", "/9/", "/10/", "/11/", "/12/", "/13/", "/14/", "/15/", "/16/", "/17/", "/18/", "/19/", "/20/", "/21/", "/22/", "/23/", "/24/", "/25/", "/26/", "/27/", "/28/", "/29/", "/30/", "/31/" };

        String Year[] = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040"};
        String SYear[] = {"/2000", "/2001", "/2002", "/2003", "/2004", "/2005", "/2006", "/2007", "/2008", "/2009", "/2010", "/2011", "/2012", "/2013", "/2014", "/2015", "/2016", "/2017", "/2018", "/2019", "/2020", "/2021", "/2022", "/2023", "/2024", "/2025", "/2026", "/2027", "/2028", "/2029", "/2030", "/2031", "/2032", "/2033", "/2034", "/2035", "/2036", "/2037", "/2038", "/2039", "/2040"};
        String SSYear[] = {"/00", "/01", "/02", "/03", "/04", "/05", "/06", "/07", "/08", "/09", "/10", "/11", "/12", "/13", "/14", "/15", "/16", "/17", "/18", "/19", "/20", "/21", "/22", "/23", "/24", "/25", "/26", "/27", "/28", "/29", "/30", "/31", "/32", "/33", "/34", "/35", "/36", "/37", "/38", "/39", "/40"};
        String lastmonth = "";
        String lastday = "";
        String lastyear = "";



        for (m = 0; m <= 11; m++) {
            MonthCheck=input.contains(Months[m]);
            if (MonthCheck == false) {
                MonthCheck=input.contains(SMonths[m]);
                if (MonthCheck == true) {
                    lastmonth = SMonths[m];
                }	if (MonthCheck == false) {
                    MonthCheck=input.contains(SSMonths[m]);
                    if (MonthCheck == true) {
                        lastmonth = SSMonths[m];
                    }
                }
            }
            if (MonthCheck == true) {
                lastmonth = Months[m];
            }
        }

        if (lastmonth != "") {
            for (d = 0; d < 31; d++) {
                DayCheck=input.contains(Day[d]);
                if (DayCheck == false) {
                    DayCheck=input.contains(SDay[d]);
                    if (DayCheck == true) {
                        lastday = SDay[d];
                    }if (DayCheck == false) {
                        DayCheck=input.contains(SSDay[d]);
                        if (DayCheck == true) {
                            lastday = SDay[d];
                        }
                    }
                }
                if (DayCheck == true) {
                    lastday = Day[d];
                }
            }
        }

        if (lastmonth != "") {
            for (y = 0; y <= 40; y++) {
                YearCheck=input.contains(Year[y]);
                if (YearCheck == false) {
                    YearCheck=input.contains(SYear[y]);
                    if (YearCheck == true) {
                        lastyear = Year[y];
                    } if (YearCheck == false) {
                        YearCheck=input.contains(SSYear[y]);
                        if (YearCheck == true) {
                            lastyear = Year[y];
                        }
                    }
                }
                if (YearCheck == true) {
                    lastyear = Year[y];
                }
            }
        }

        return lastmonth + " " + lastday + " " + lastyear;

    }
}


