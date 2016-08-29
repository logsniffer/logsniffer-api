package com.logsniffer.beanconfig;

/**
 * Reflects a miss-configuration exception.
 * 
 * @author mbok
 * 
 */
public class ConfigException extends RuntimeException {
	private static final long serialVersionUID = -6997110654319860550L;

	public ConfigException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConfigException(final String message) {
		super(message);
	}

}
