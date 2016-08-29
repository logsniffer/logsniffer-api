package com.logsniffer.source;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.logsniffer.model.LogPointer;

/**
 * Represents a rolling log.
 * 
 * @author mbok
 * 
 */
@JsonTypeName("rolling")
public interface RollingLog extends Log {
	public Log[] getParts();

	@Deprecated
	public LogPointer getGlobalPointer(String partPath, LogPointer partPointer);
}
