package com.logsniffer.app.configvalue;

import java.io.IOException;

/**
 * Extendes config value source with possibility to store/update values.
 * 
 * @author mbok
 * 
 */
public interface ConfigValueStore extends ConfigValueSource {
	/**
	 * Stores a value with given key.
	 * 
	 * @param key
	 *            the key for config value
	 * @param value
	 *            the value to store
	 * @throws IOException
	 *             in case of store errors
	 */
	public void store(String key, String value) throws IOException;
}
