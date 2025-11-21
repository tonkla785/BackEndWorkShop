package com.pcc.backend.Utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static void main(String[] args) {
        Date date = new Date();
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("th", "TH"));

        //วันที่ไทย1: 03 พฤษภาคม 2567
        SimpleDateFormat ddMMMMyyyy = new SimpleDateFormat("dd MMMM yyyy", new Locale("th", "TH"));
        ddMMMMyyyy.setDateFormatSymbols(dfs);
        String thaiDate1 = ddMMMMyyyy.format(date);
        System.out.println("วันที่ไทย1: " + thaiDate1);

        //วันที่ไทย2: 03/05/2567
        SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
        ddMMyyyy.setDateFormatSymbols(dfs);
        String thaiDate2 = ddMMyyyy.format(date);
        System.out.println("วันที่ไทย2: " + thaiDate2);

        //วันที่ไทย3: 2567-05-03
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", new Locale("th", "TH"));
        yyyyMMdd.setDateFormatSymbols(dfs);
        String thaiDate3 = yyyyMMdd.format(date);
        System.out.println("วันที่ไทย3: " + thaiDate3);
    }

    public static String convertDDMMYYYYBuddhistYearToYYYYMMDDChristianYear(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .minusYears(543);
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static String convertYYYYMMDDBuddhistYearToYYYYMMDDChristianYear(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .minusYears(543);
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static Date convertDDMMYYYYBuddhistYearToDate(String input) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy")).minusYears(543);
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            return date;
        }
    }

    public static Date convertYYYYMMDDBuddhistYearToDate(String input) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusYears(543);
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            return date;
        }
    }

    public static java.sql.Date getCurrentDatetimeSql() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public static String convertYYYYMMDDtoDDMMYYYY(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    public static String convertDDMMYYYYtoYYYYMMDD(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static LocalDate convertDDMMYYYYtoYYYYMMDDD(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return localDate;
        }
    }

    public static String convertYYYYMMDDtoDDMMYYYY(Date input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(input);
        }
    }

    public static Date convertYYYYMMDDtoDDMMYYYYDateObject(String input) throws ParseException {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(input);
        }
    }

    public static String convertDDMMYYYYtoYYYYMMDD(Date input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(input);
        }
    }

    public static Date convertDDMMYYYYChristianYearToDate(String input) {
        System.out.println("--------------------------------------------------"+input);
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            System.out.println("date--------------------------------------------------"+date);
            return date;
        }
    }

    public static Date convertYYYYMMDDChristianYearToDate(String input) {
        System.out.println("--------------------------------------------------"+input);
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (StringUtils.isEmpty(input)) {
            return null;
        } else {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            System.out.println("date--------------------------------------------------"+date);
            return date;
        }
    }

}
