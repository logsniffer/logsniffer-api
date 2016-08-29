package com.logsniffer.source;

import java.io.IOException;

import com.logsniffer.model.LogPointer;

/**
 * Factory to create position pointers corresponding to a log.
 * 
 * @author mbok
 * 
 */
public interface LogPointerFactory {
	/**
	 * Returns a signed number of bytes needed to consume continuously from this
	 * position to achieve the new position. The number is negative when the
	 * pointer to compare is positioned before. When the compare pointer is null
	 * the start position is assumed.
	 * 
	 * @param compareTo
	 *            the pointer to compare to
	 * @return signed byte difference between this pointer and the pointer to
	 *         compare to
	 * @throws IOException
	 *             in case of errors
	 */
	public abstract long getDifference(LogPointer source, LogPointer compareTo) throws IOException;

	/**
	 * Returns the pointer from given JSON representation.
	 * 
	 * @param data
	 *            JSON pointer representation
	 * @return the pointer from given JSON representation
	 * @throws IOException
	 *             in case of errors
	 */
	public abstract LogPointer getFromJSON(String data) throws IOException;

	/**
	 * Returns the pointer referencing the tail / end of the log
	 * 
	 * @return pointer referencing the tail / end of the log
	 * @throws IOException
	 */
	LogPointer end() throws IOException;

	/**
	 * Returns the pointer referencing the head / start of the log
	 * 
	 * @return pointer referencing the head / start of the log
	 * @throws IOException
	 */
	LogPointer start() throws IOException;

	/**
	 * Simplified future clone to enable asynchronous navigation.
	 * 
	 * @author mbok
	 *
	 */
	public interface NavigationFuture {
		/**
		 * Blocks the navigation process and returns the pointer when finished.
		 * 
		 * @return the target pointer after navigation is complete.
		 * @throws IOException
		 */
		LogPointer get() throws IOException;
	}

	/**
	 * Refreshes a pointer to fix no longer valid pointers before accessing
	 * content.
	 * 
	 * @param toRefresh
	 *            the pointer to refresh
	 * @return the refreshed pointer
	 * @throws IOException
	 */
	NavigationFuture refresh(LogPointer toRefresh) throws IOException;

}
