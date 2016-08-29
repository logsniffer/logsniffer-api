package com.logsniffer.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.beanconfig.WrappedBean;
import com.logsniffer.field.FieldsMap;
import com.logsniffer.reader.filter.FilteredLogEntryReader;

/**
 * A source for logs related to the same reader.
 * 
 * @author mbok
 * 
 */
public interface LogSource<ACCESSTYPE extends LogRawAccess<? extends LogInputStream>>
		extends ConfiguredBean, LogRawAccessor<ACCESSTYPE, Log> {
	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @return the reader
	 */
	public FilteredLogEntryReader<ACCESSTYPE> getReader();

	public FieldsMap getUiSettings();

	// public int resolveLogs();

	// public List<String> getLogs(int offset, int len) throws IOException;

	public List<Log> getLogs() throws IOException;

	/**
	 * Returns the log associated with given path or null if log not found.
	 * 
	 * @param path
	 *            log path
	 * @return log associated with given path or null if log not found
	 * @throws IOException
	 *             in case of errors
	 */
	public Log getLog(String path) throws IOException;

	/**
	 * Wrapper for delegated log source e.g. to allow lazy inititaing of
	 * sources.
	 * 
	 * @author mbok
	 * 
	 */
	public static abstract class LogSourceWrapper implements LogSource<LogRawAccess<? extends LogInputStream>>,
			WrappedBean<LogSource<LogRawAccess<? extends LogInputStream>>> {
		private LogSource<LogRawAccess<? extends LogInputStream>> wrapped;

		@SuppressWarnings("unchecked")
		public static final LogSource<LogRawAccess<? extends LogInputStream>> unwrap(
				final LogSource<? extends LogRawAccess<? extends LogInputStream>> possiblyWrapped) {
			if (possiblyWrapped instanceof LogSourceWrapper) {
				return ((LogSourceWrapper) possiblyWrapped).getSource();
			}
			return (LogSource<LogRawAccess<? extends LogInputStream>>) possiblyWrapped;
		}

		private LogSource<LogRawAccess<? extends LogInputStream>> getSource() {
			if (wrapped == null) {
				wrapped = getWrapped();
			}
			return wrapped;
		}

		@Override
		public long getId() {
			return getSource().getId();
		}

		@Override
		public String getName() {
			return getSource().getName();
		}

		@Override
		public FilteredLogEntryReader<LogRawAccess<? extends LogInputStream>> getReader() {
			return getSource().getReader();
		}

		@Override
		public List<Log> getLogs() throws IOException {
			return getSource().getLogs();
		}

		@Override
		public Log getLog(final String path) throws IOException {
			return getSource().getLog(path);
		}

		@Override
		public LogRawAccess<? extends LogInputStream> getLogAccess(final Log log) throws IOException {
			return getSource().getLogAccess(log);
		}

		@Override
		public FieldsMap getUiSettings() {
			return getSource().getUiSettings();
		}

	}

	public static final LogSource<LogRawAccess<? extends LogInputStream>> NULL_SOURCE = new LogSource<LogRawAccess<? extends LogInputStream>>() {

		@Override
		public LogRawAccess<LogInputStream> getLogAccess(final Log log) throws IOException {
			return null;
		}

		@Override
		public long getId() {
			return 0;
		}

		@Override
		public String getName() {
			return "undefined";
		}

		@Override
		public FilteredLogEntryReader<LogRawAccess<? extends LogInputStream>> getReader() {
			return null;
		}

		@Override
		public List<Log> getLogs() throws IOException {
			return new ArrayList<>();
		}

		@Override
		public Log getLog(final String path) throws IOException {
			return null;
		}

		@Override
		public FieldsMap getUiSettings() {
			return null;
		}

	};
}
