package com.danylko.yourburger.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Locale uaLocal = new Locale("ua", "UA");

    public Date getDate() {
        Calendar calendar = new GregorianCalendar(uaLocal);
        return calendar.getTime();
    }
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return DATE_FORMAT.parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return DATE_FORMAT.format(date);
    }

}
