package com.logsniffer.reader;

import java.io.IOException;
import java.util.List;

import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.field.FieldsHost;
import com.logsniffer.model.LogEntry;
import com.logsniffer.model.LogPointer;
import com.logsniffer.model.SeverityLevel;
import com.logsniffer.source.Log;
import com.logsniffer.source.LogInputStream;
import com.logsniffer.source.LogPointerFactory;
import com.logsniffer.source.LogRawAccess;

/**
 * Format dependent log reader. Reading is performed pipeline like.
 * 
 * @author mbok
 * 
 */
public interface LogEntryReader<ACCESSORTYPE extends LogRawAccess<? extends LogInputStream>>
		extends ConfiguredBean, FieldsHost {

	/**
	 * Consumer for log entries, called sequentially when a new entry was read.
	 * 
	 * @author mbok
	 * 
	 */
	public static interface LogEntryConsumer {
		/**
		 * Called to consume the new read log entry.
		 * 
		 * @param log
		 *            the log
		 * @param pointerFactory
		 *            the pointer factory
		 * @param entry
		 *            the read entry
		 * @return return true to continue reading (if EOF isn't reached) or
		 *         false to interrupt further reading.
		 * @throws IOException
		 *             in case of any errors
		 */
		boolean consume(Log log, LogPointerFactory pointerFactory, LogEntry entry) throws IOException;
	}

	/**
	 * Reads non-blocking the log entries beginning with the byte offset in log.
	 * The read entries will be propagated sequentially to the given consumer.
	 * The method returns back when {@link LogEntryConsumer#consume(LogEntry)}
	 * returns false or the boundary is reached.
	 * 
	 * @param log
	 *            the log to read
	 * @param logAccess
	 *            the access to the log to read from
	 * @param startOffset
	 *            the offset pointer in the log to start reading on. A null
	 *            value means start from beginning.
	 * 
	 * @param consumer
	 *            consumer to propagate read entries to
	 */
	public void readEntries(Log log, ACCESSORTYPE logAccess, LogPointer startOffset, LogEntryConsumer consumer)
			throws IOException;

	/**
	 * Reads log entries in a reverse order beginning from the given offset. The
	 * read entries will be propagated sequentially to the given consumer. The
	 * method returns back when {@link LogEntryConsumer#consume(LogEntry)}
	 * returns false or the boundary is reached.
	 * 
	 * @param log
	 *            the log to read
	 * @param logAccess
	 *            the access to the log to read from
	 * @param startOffset
	 *            the offset pointer in the log to start reading on. A null
	 *            value means start from beginning.
	 * 
	 * @param consumer
	 *            consumer to propagate read entries to
	 */
	public void readEntriesReverse(Log log, ACCESSORTYPE logAccess, LogPointer startOffset, LogEntryConsumer consumer)
			throws IOException;

	/**
	 * 
	 * @return list of supported and provided severity levels.
	 */
	public List<SeverityLevel> getSupportedSeverities();

	/**
	 * Wrapper for delegated log entry reader e.g. to allow lazy initiating of
	 * readers.
	 * 
	 * @author mbok
	 * 
	 * @param <ContentType>
	 *            the entry type
	 */
	public static abstract class LogEntryReaderWrapper
			implements LogEntryReader<LogRawAccess<? extends LogInputStream>> {
		private LogEntryReader<LogRawAccess<? extends LogInputStream>> wrapped;

		protected abstract LogEntryReader<LogRawAccess<? extends LogInputStream>> getWrapped() throws IOException;

		private LogEntryReader<LogRawAccess<? extends LogInputStream>> getReader() throws IOException {
			if (wrapped == null) {
				wrapped = getWrapped();
			}
			return wrapped;
		}

		@Override
		public void readEntries(final Log log, final LogRawAccess<? extends LogInputStream> logAccess,
				final LogPointer startOffset, final LogEntryConsumer consumer) throws IOException {
			getReader().readEntries(log, logAccess, startOffset, consumer);
		}

		@Override
		public List<SeverityLevel> getSupportedSeverities() {
			try {
				return getReader().getSupportedSeverities();
			} catch (final Exception e) {
				throw new RuntimeException("Unexpected", e);
			}
		}

	}
}
