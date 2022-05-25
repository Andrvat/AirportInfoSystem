package model.support;

import lombok.Builder;
import view.utilities.TableRecordBuilder;

import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeCalendar {
    public static final String SQL_DATE_FORMAT = "YYYY-MM-DD HH24:MI:SS";
    public static final String JAVA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public enum TimeCalendarType {
        DATE_ONLY,
        TIME_ONLY,
        FULL_DATE
    }

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

    public TimeCalendar(Integer hours, Integer minutes, Integer seconds) {
        this.calendar.set(Calendar.HOUR_OF_DAY, hours);
        this.calendar.set(Calendar.MINUTE, minutes);
        this.calendar.set(Calendar.SECOND, seconds);
    }

    public String toTypedString(TimeCalendarType type) {
        switch (type) {
            case DATE_ONLY -> {
                return this.calendar.get(Calendar.YEAR) + "-" +
                        (this.calendar.get(Calendar.MONTH) + 1) + "-" +
                        this.calendar.get(Calendar.DAY_OF_MONTH);
            }
            case TIME_ONLY -> {
                return this.calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                        this.calendar.get(Calendar.MINUTE) + ":" +
                        this.calendar.get(Calendar.SECOND);
            }
            case FULL_DATE -> {
                return this.calendar.get(Calendar.YEAR) + "-" +
                        (this.calendar.get(Calendar.MONTH) + 1) + "-" +
                        this.calendar.get(Calendar.DAY_OF_MONTH) + " " +
                        this.calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                        this.calendar.get(Calendar.MINUTE) + ":" +
                        this.calendar.get(Calendar.SECOND);
            }
        }
        return null;
    }

    public boolean isBetween(TimeCalendar min, TimeCalendar max) {
        return min.getSecondsFromMidnight() < this.getSecondsFromMidnight() &&
                this.getSecondsFromMidnight() < max.getSecondsFromMidnight();
    }

    public String toSqlStringDate() {
        return "TO_DATE(" + "'" + this + "', " + "'" + SQL_DATE_FORMAT + "'" + ")";
    }

    private int getSecondsFromMidnight() {
        return this.calendar.get(Calendar.HOUR_OF_DAY) * 3600 +
                this.calendar.get(Calendar.MINUTE) * 60 +
                this.calendar.get(Calendar.SECOND);
    }
}
