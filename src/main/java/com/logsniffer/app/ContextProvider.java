package com.logsniffer.app;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Singleton for accessing the app context.
 * 
 * @author mbok
 * 
 */
public class ContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;

	public static final String PROFILE_NONE_QA = "NONE_QA";

	/**
	 * @return the context
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public static void setContext(final ApplicationContext context) {
		ContextProvider.context = context;
	}

	@Override
	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		setContext(context);
	}

}
