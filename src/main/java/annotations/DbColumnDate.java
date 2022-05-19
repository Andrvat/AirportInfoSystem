package annotations;

import model.support.TimeCalendar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumnDate {
    String name() default "";

    TimeCalendar.TimeCalendarType type() default TimeCalendar.TimeCalendarType.FULL_DATE;
    DbConstrains constrains() default @DbConstrains;
}
