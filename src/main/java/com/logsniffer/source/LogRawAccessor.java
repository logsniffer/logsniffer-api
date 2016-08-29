package com.logsniffer.source;

import java.io.IOException;

public interface LogRawAccessor<ACCESSTYPE extends LogRawAccess<? extends LogInputStream>, LOGTYPE extends Log> {
	/**
	 * Returns raw read access to the log associated with given path or null if
	 * log not found.
	 * 
	 * @param log
	 *            log path
	 * @return read access to associated log or null if log not found
	 * @throws IOException
	 *             in case of errors
	 */
	public ACCESSTYPE getLogAccess(LOGTYPE log) throws IOException;
}
