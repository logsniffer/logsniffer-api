package com.logsniffer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.logsniffer.field.FieldsMap;
import com.logsniffer.model.LogEntry.LogEntryTypeSafeDeserializer;
import com.logsniffer.model.support.CommonLogFragment;

/**
 * Represents an entry in a log with native pointers.
 * 
 * @author mbok
 * 
 */
@JsonDeserialize(using = LogEntryTypeSafeDeserializer.class)
public final class LogEntry extends CommonLogFragment {

	private static final long serialVersionUID = 6930083682998388113L;

	/**
	 * Field key for convenient method {@link #getRawContent()}.
	 */
	public static final String FIELD_RAW_CONTENT = "lf_raw";

	/**
	 * Field key for convenient method {@link #isUnformatted()}.
	 */
	public static final String FIELD_UNFORMATTED = "lf_unformatted";

	/**
	 * @return the content
	 */
	public String getRawContent() {
		return (String) super.get(FIELD_RAW_CONTENT);
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setRawContent(final String content) {
		super.put(FIELD_RAW_CONTENT, content);
	}

	/**
	 * Marks the log entry as unformatted.
	 * 
	 * @param unformatted
	 *            to set
	 */
	public void setUnformatted(final boolean unformatted) {
		super.put(FIELD_UNFORMATTED, unformatted);
	}

	/**
	 * Indicates if the log entry is unformatted.
	 * 
	 * @return true and only if this log entry was explicitly marked as
	 *         unformatted
	 */
	public boolean isUnformatted() {
		final Object u = super.get(FIELD_UNFORMATTED);
		if (u instanceof Boolean) {
			return (Boolean) u;
		}
		return false;
	}

	/**
	 * Type safe deserializer for {@link LogEntry}s.
	 * 
	 * @author mbok
	 *
	 */
	public static class LogEntryTypeSafeDeserializer extends FieldsMapTypeSafeDeserializer {

		@Override
		protected FieldsMap create() {
			return new LogEntry();
		}

	}
}
