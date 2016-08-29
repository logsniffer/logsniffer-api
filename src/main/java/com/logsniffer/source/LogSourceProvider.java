package com.logsniffer.source;

import java.util.List;

import com.logsniffer.util.ReferenceIntegrityException;

/**
 * Provides registered {@link LogSource}s.
 * 
 * @author mbok
 * 
 */
public interface LogSourceProvider {
	/**
	 * Returns a list of registered log sources.
	 * 
	 * @return list of registered log sources
	 */
	public List<LogSource<LogRawAccess<? extends LogInputStream>>> getSources();

	/**
	 * Returns a log source for given id.
	 * 
	 * @param id
	 *            id log source to return
	 * @return the destined log source or null if not found
	 */
	public LogSource<LogRawAccess<? extends LogInputStream>> getSourceById(long id);

	/**
	 * Persists a new log source.
	 * 
	 * @param source
	 *            the source to persist.
	 * @return id of the persisted source.
	 */
	public long createSource(LogSource<? extends LogRawAccess<? extends LogInputStream>> source);

	/**
	 * Updates the given source.
	 * 
	 * @param source
	 *            source to update.
	 */
	public void updateSource(LogSource<? extends LogRawAccess<? extends LogInputStream>> source);

	/**
	 * Deletes the given source.
	 * 
	 * @param source
	 */
	public void deleteSource(LogSource<? extends LogRawAccess<? extends LogInputStream>> source)
			throws ReferenceIntegrityException;
}
