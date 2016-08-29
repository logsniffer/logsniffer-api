package com.logsniffer.source;

import java.io.IOException;

import com.logsniffer.model.LogPointer;

/**
 * Log input stream with an abstract pointer concept which allows distinct
 * positioning in a log file. Whenever {@link #getPointer()} returns a pointer
 * with {@link LogPointer#isEOF()} no data is read anymore even more is written
 * in the mean time after the log instance was instantiated. A new log instance
 * has to be retrieved from the source to get the new data.
 * 
 * @author mbok
 * 
 */
public interface LogInputStream {

	/**
	 * Returns the actual position in a stream after read some data.
	 * 
	 * @return actual position in a stream after read some data.
	 */
	public abstract LogPointer getPointer() throws IOException;

}
