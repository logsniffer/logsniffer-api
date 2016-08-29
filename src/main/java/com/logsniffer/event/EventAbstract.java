package com.logsniffer.event;

import java.util.Date;

/**
 * Represents technical metadata of an event.
 * 
 * @author mbok
 * 
 */
public interface EventAbstract {
	public String getId();

	public long getSnifferId();

	public long getLogSourceId();

	public String getLogPath();

	public Date getPublished();
}
