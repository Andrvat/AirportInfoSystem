package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbConstrains {
    boolean isPrimaryKey() default false;
    boolean isAllowedNull() default true;
    boolean isUnique() default false;
}
