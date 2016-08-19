package com.logsniffer.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Abstraction of pointing a byte position inside a log. The
 * {@link #equals(Object)} method has to be implemented properly to compare
 * pointers.
 * 
 * @author mbok
 * 
 */
@JsonSerialize(as = LogPointer.class)
public interface LogPointer {
	/**
	 * @return true if this pointer represents the start of log
	 */
	public boolean isSOF();

	/**
	 * @return true if this pointer represents the end of log
	 */
	public boolean isEOF();

	/**
	 * Returns an JSON serialized representation of this pointer.
	 * 
	 * @return an JSON serialized representation of this pointer
	 */
	@JsonRawValue
	public String getJson();
}
