package com.logsniffer.app.configvalue;

/**
 * Abstraction for configurable values.
 * 
 * @author mbok
 * 
 * @param <TYPE>
 */
public interface ConfigValue<TYPE> {
	TYPE get();
}
