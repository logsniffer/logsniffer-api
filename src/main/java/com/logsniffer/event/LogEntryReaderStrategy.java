package com.logsniffer.event;

import java.io.IOException;

import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.event.Scanner.EventConsumer;
import com.logsniffer.model.LogEntry;
import com.logsniffer.model.LogPointer;
import com.logsniffer.reader.LogEntryReader;
import com.logsniffer.source.Log;
import com.logsniffer.source.LogPointerFactory;

/**
 * Strategy for log reader describing how long the scanner should read from log.
 * 
 * @author mbok
 * 
 */
public interface LogEntryReaderStrategy extends ConfiguredBean {
	/**
	 * Called each time
	 * {@link Scanner#find(LogEntryReader, LogEntryReaderStrategy, Log, IncrementData, EventConsumer)}
	 * starts scanning to reset values from previous scan run.
	 * 
	 * @param log
	 *            the log
	 * @param pointerFactory
	 *            pointer factory
	 * @param start
	 *            the start position in log when the reader starts reading,
	 *            possibly null
	 */
	public void reset(Log log, LogPointerFactory pointerFactory, LogPointer start) throws IOException;

	/**
	 * Returns true to indicate the scanner to continue with scanning. If false
	 * is returned the scanner stops the current scanning step.
	 * 
	 * @param log
	 *            the log
	 * @param pointerFactory
	 *            pointer factory
	 * @param entry
	 *            the last read entry
	 * @return true to indicate the scanner to continue with scanning and false
	 *         to stop.
	 */
	public boolean continueReading(Log log, LogPointerFactory pointerFactory, LogEntry currentReadEntry)
			throws IOException;
}
