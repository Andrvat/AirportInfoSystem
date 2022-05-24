package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumnBoolean {
    enum BooleanValueType {
        REAL_BOOL,
        YES_NO,
        MAN_WOMAN,
        GOOD_BAD
    }

    int value() default 1;

    String name() default "";

    BooleanValueType type() default BooleanValueType.REAL_BOOL;

    DbConstrains constrains() default @DbConstrains;
}
