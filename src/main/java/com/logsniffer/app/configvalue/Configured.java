package com.logsniffer.app.configvalue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated {@link ConfigValue} properties will get injected the configured
 * values reconfigurable on demand.
 * 
 * @author mbok
 * 
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Configured {
	String value();

	String defaultValue() default "";
}
