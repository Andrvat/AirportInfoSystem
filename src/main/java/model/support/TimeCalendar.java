package model.support;

import lombok.Builder;

import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeCalendar {
    public static final String SQL_DATE_FORMAT = "YYYY-MM-DD HH24:MI:SS";
    public static final String JAVA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final Calendar calendar = new GregorianCalendar();

    public TimeCalendar(String target) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(JAVA_DATE_FORMAT, Locale.ENGLISH);
        Date result = dateFormat.parse(target);
        this.calendar.setTime(result);
    }

    public TimeCalendar(Date date) {
        this.calendar.setTime(date);
    }

    public TimeCalendar(Integer year, Integer month, Integer day, Integer hours, Integer minutes, Integer seconds) {
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, month);
        this.calendar.set(Calendar.DAY_OF_MONTH, day);
        this.calendar.set(Calendar.HOUR_OF_DAY, hours);
        this.calendar.set(Calendar.MINUTE, minutes);
        this.calendar.set(Calendar.SECOND, seconds);
    }

    @Override
    public String toString() {
        return this.calendar.get(Calendar.YEAR) + "-" +
                this.calendar.get(Calendar.MONTH) + "-" +
                this.calendar.get(Calendar.DAY_OF_MONTH) + " " +
                this.calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                this.calendar.get(Calendar.MINUTE) + ":" +
                this.calendar.get(Calendar.SECOND);
    }

    public String toSqlStringDate() {
        return "TO_DATE(" + "'" + this + "', " + "'" + SQL_DATE_FORMAT + "'" + ")";
    }
}
