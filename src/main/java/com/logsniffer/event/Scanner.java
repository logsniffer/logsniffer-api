package com.logsniffer.event;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.logsniffer.beanconfig.ConfigException;
import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.beanconfig.WrappedBean;
import com.logsniffer.field.FieldBaseTypes;
import com.logsniffer.field.FieldsHost;
import com.logsniffer.model.LogEntry;
import com.logsniffer.model.LogPointer;
import com.logsniffer.source.Log;
import com.logsniffer.source.LogPointerFactory;

import net.sf.json.JSONObject;

/**
 * Incremental event scanner.
 * 
 * @author mbok
 * 
 */
public interface Scanner extends ConfiguredBean, FieldsHost {
	/**
	 * Consumes events to allow pipeline processing.
	 * 
	 * @author mbok
	 * 
	 */
	public static interface EventConsumer {
		/**
		 * Consumes the event.
		 * 
		 * @param eventData
		 *            the event with extracted information
		 * @param ctx
		 *            the scanner context
		 * @throws IOException
		 */
		void consume(Event eventData, ScannerContext ctx) throws IOException;
	}

	/**
	 * Incremental routine to search for next log events. The routine should
	 * store in incrementData values to continue the search process in the next
	 * time it's called without the need to start from log's start.
	 * 
	 * @param reader
	 *            reader for the log
	 * @param readerStrategy
	 *            Strategy for log reader describing how long the scanner should
	 *            read from log.
	 * @param log
	 *            the log access to search for events in
	 * @param incrementData
	 *            the persistent data between multiple calls to support
	 *            incremental log scanning for event.
	 * @param eventConsumer
	 *            event consumer
	 */
	// public <R extends LogRawAccess<? extends LogInputStream>> void
	// find(LogEntryReader<R> reader,
	// LogEntryReaderStrategy readerStrategy, Log log, R logAccess,
	// IncrementData incrementData,
	// EventConsumer eventConsumer) throws IOException, FormatException;

	public ScannerStream buildScannerStream(EventConsumer eventConsumer, JSONObject state) throws IOException;

	/**
	 * Wrapper for delegated strategy e.g. to allow lazy initiation.
	 * 
	 * @author mbok
	 */
	public static abstract class LogEntryReaderStrategyWrapper
			implements LogEntryReaderStrategy, WrappedBean<LogEntryReaderStrategy> {
		private LogEntryReaderStrategy wrapped;

		public static final LogEntryReaderStrategy unwrap(final LogEntryReaderStrategy possiblyWrapped) {
			if (possiblyWrapped instanceof LogEntryReaderStrategyWrapper) {
				return ((LogEntryReaderStrategyWrapper) possiblyWrapped).getWrappedStrategy();
			}
			return possiblyWrapped;
		}

		public final LogEntryReaderStrategy getWrappedStrategy() throws ConfigException {
			if (wrapped == null) {
				wrapped = getWrapped();
			}
			return wrapped;
		}

		@Override
		public void reset(final Log log, final LogPointerFactory pointerFactory, final LogPointer start)
				throws IOException {
			getWrappedStrategy().reset(log, pointerFactory, start);
		}

		@Override
		public boolean continueReading(final Log log, final LogPointerFactory pointerFactory,
				final LogEntry currentReadEntry) throws IOException {
			return getWrappedStrategy().continueReading(log, pointerFactory, currentReadEntry);
		}

	}

	/**
	 * Wrapper for delegated log scanner e.g. to allow lazy initiation.
	 * 
	 * @author mbok
	 */
	public static abstract class ScannerWrapper implements Scanner, WrappedBean<Scanner> {
		private Scanner wrapped;

		public static final Scanner unwrap(final Scanner possiblyWrapped) {
			if (possiblyWrapped instanceof ScannerWrapper) {
				return ((ScannerWrapper) possiblyWrapped).getWrappedScanner();
			}
			return possiblyWrapped;
		}

		public final Scanner getWrappedScanner() throws ConfigException {
			if (wrapped == null) {
				wrapped = getWrapped();
			}
			return wrapped;
		}

		// @Override
		// public <R extends LogRawAccess<? extends LogInputStream>> void
		// find(final LogEntryReader<R> reader,
		// final LogEntryReaderStrategy readerStrategy, final Log log, final R
		// logAccess,
		// final IncrementData incrementData, final EventConsumer eventConsumer)
		// throws IOException, FormatException {
		// try {
		// getWrappedScanner().find(reader, readerStrategy, log, logAccess,
		// incrementData, eventConsumer);
		// } catch (final ConfigException e) {
		// throw new IOException("Failed to create configured scanner", e);
		// }
		// }

		@Override
		public LinkedHashMap<String, FieldBaseTypes> getFieldTypes() throws IOException {
			try {
				return getWrappedScanner().getFieldTypes();
			} catch (final ConfigException e) {
				throw new IOException("Failed to create configured scanner", e);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.logsniffer.event.Scanner#buildScannerStream(com.logsniffer.event.
		 * Scanner.EventConsumer, net.sf.json.JSONObject)
		 */
		@Override
		public ScannerStream buildScannerStream(final EventConsumer eventConsumer, final JSONObject state)
				throws IOException {
			try {
				return getWrappedScanner().buildScannerStream(eventConsumer, state);
			} catch (final ConfigException e) {
				throw new IOException("Failed to create configured scanner", e);
			}
		}

	}
}
