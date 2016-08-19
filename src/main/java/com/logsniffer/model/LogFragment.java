package com.logsniffer.model;

import java.util.Date;
import java.util.Map;

/**
 * Identifies an object locatable in a log by a start and end offset.
 * 
 * @author mbok
 *
 */
public interface LogFragment extends Map<String, Object> {
	/**
	 * Field key for convenient method {@link #getStartOffset()}.
	 */
	public static final String FIELD_START_OFFSET = "lf_startOffset";

	/**
	 * Field key for convenient method {@link #getEndOffset()}.
	 */
	public static final String FIELD_END_OFFSET = "lf_endOffset";

	/**
	 * Field key for convenient method {@link #getSeverity()}.
	 */
	public static final String FIELD_SEVERITY_LEVEL = "lf_severity";

	/**
	 * Field key for convenient method {@link #getTimestamp()}.
	 */
	public static final String FIELD_TIMESTAMP = "lf_timestamp";

	/**
	 * @return the offset in the log at which this object starts. This method is
	 *         a convenient access to the field {@link #FIELD_START_OFFSET}.
	 */
	LogPointer getStartOffset();

	/**
	 * Sets the start offset. This method is a convenient access to the field
	 * {@link #FIELD_START_OFFSET}.
	 * 
	 * @param startOffset
	 */
	void setStartOffset(LogPointer startOffset);

	/**
	 * @return the offset in the log at which this object ends. This method is a
	 *         convenient access to the field {@link #FIELD_END_OFFSET}.
	 */
	LogPointer getEndOffset();

	/**
	 * Sets the end offset. This method is a convenient access to the field
	 * {@link #FIELD_END_OFFSET}.
	 * 
	 * @param endOffset
	 */
	void setEndOffset(LogPointer endOffset);

	/**
	 * 
	 * @return severity level of this log fragment. This method is a convenient
	 *         access to the field {@link #FIELD_SEVERITY_LEVEL}.
	 */
	SeverityLevel getSeverity();

	/**
	 * Sets the severity level of this log fragment. This method is a convenient
	 * access to the field {@link #FIELD_SEVERITY_LEVEL}.
	 * 
	 * @param severity
	 */
	void setSeverity(SeverityLevel severity);

	/**
	 * 
	 * @return the timestamp/occurrence date of this log fragment. This method
	 *         is a convenient access to the field {@link #FIELD_TIMESTAMP}.
	 */
	Date getTimestamp();

	/**
	 * Sets the timestamo/occurrence date of this log fragment. This method is a
	 * convenient access to the field {@link #FIELD_TIMESTAMP}.
	 * 
	 * @param timestamp
	 */
	void setTimestamp(final Date timestamp);
}
