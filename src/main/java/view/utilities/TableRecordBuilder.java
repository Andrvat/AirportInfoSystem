package view.utilities;

import annotations.DbColumnBoolean;
import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import entities.AbstractComponent;
import entities.Passenger;
import entities.Ticket;
import model.support.TimeCalendar;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Map;

public class TableRecordBuilder {
    public static void buildByValues(AbstractComponent instance, Map<String, String> keyValues)
            throws IllegalAccessException, ParseException {
        for (var entry : keyValues.entrySet()) {
            String annotationName = entry.getKey();
            String value = entry.getValue();
            for (Field field : instance.getClass().getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations.length != 0) {
                    field.setAccessible(true);
                    Annotation annotation = annotations[0];
                    if (annotation instanceof DbColumnNumber dbColumnNumber) {
                        if (annotationName.equals(dbColumnNumber.name())) {
                            if (field.getType() == Integer.class) {
                                field.set(instance, Integer.valueOf(value));
                            } else if (field.getType() == Float.class) {
                                field.set(instance, Float.valueOf(value));
                            }
                        }
                    } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                        if (annotationName.equals(dbColumnVarchar.name())) {
                            field.set(instance, value);
                        }
                    } else if (annotation instanceof DbColumnBoolean dbColumnBoolean) {
                        if (annotationName.equals(dbColumnBoolean.name())) {
                            field.set(instance, "Y".equals(value));
                        }
                    } else if (annotation instanceof DbColumnDate dbColumnDate) {
                        if (annotationName.equals(dbColumnDate.name())) {
                            field.set(instance, new TimeCalendar(value));
                        }
                    }
                }
            }
        }
    }
}
