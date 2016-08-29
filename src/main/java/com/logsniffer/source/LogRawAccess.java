package com.logsniffer.source;

import java.io.IOException;

import com.logsniffer.model.LogPointer;

/**
 * General read access to the underlying log with an abstract pointer concept
 * which allows distinct positioning in a log. Whenever {@link #getPointer()}
 * returns a pointer with {@link LogPointer#isEOF()} no data is read anymore
 * even more is written in the mean time after the log instance was
 * instantiated. A new access instance has to be retrieved from the source to
 * get the new data.
 * 
 * @author mbok
 * 
 */
public interface LogRawAccess<STREAMTYPE extends LogInputStream> extends LogPointerFactory {

	/**
	 * Returns an input stream to read from the log beginning from the pointer.
	 * 
	 * @param from
	 *            the pointer to start the stream from; null indicates the log
	 *            start.
	 * @return log stream
	 * @throws IOException
	 *             in case of errors
	 */
	STREAMTYPE getInputStream(LogPointer from) throws IOException;

	Navigation<?> getNavigation();

}
