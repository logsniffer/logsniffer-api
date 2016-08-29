package com.logsniffer.app.configvalue;

/**
 * Source for config values.
 * 
 * @author mbok
 * 
 */
public interface ConfigValueSource {
	/**
	 * Returns the string value for given key or null if not available.
	 * 
	 * @param key
	 *            the key for config value
	 * @return the string value for given key or null if not available
	 */
	String getValue(String key);
}
