package com.danylko.yourburger.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    private static final String pattern = "dd-MM-yyyy HH:mm:ss";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    private Locale uaLocal = new Locale("ua", "UA");

    public Date getDate() {
        Calendar calendar = new GregorianCalendar(uaLocal);
        return calendar.getTime();
    }
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormat.parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }

}
