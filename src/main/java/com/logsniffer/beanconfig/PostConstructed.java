package com.logsniffer.beanconfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicated the post constructor for a configured bean.
 * 
 * @author mbok
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PostConstructed {
	Class<? extends BeanPostConstructor<?>> constructor();
}
