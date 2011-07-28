package safrain.pool.reseter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import safrain.pool.reseter.CustomObjectReseter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OPoolCustom {
	Class<? extends CustomObjectReseter<?>> reseter();
}
