package com.logsniffer.event;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.logsniffer.event.Event.EventTypeSafeDeserializer;
import com.logsniffer.field.FieldsMap;
import com.logsniffer.model.LogEntry;
import com.logsniffer.model.LogFragment;
import com.logsniffer.model.support.CommonLogFragment;

/**
 * An event represents a fact of interest detected from one or multiple
 * {@link LogEntry}s.
 * 
 * @author mbok
 * 
 */
@JsonDeserialize(using = EventTypeSafeDeserializer.class)
public class Event extends CommonLogFragment implements EventAbstract, LogFragment {

	private static final long serialVersionUID = 3694008717847809694L;

	/**
	 * Old field key for embedding events this event arises from. Since version
	 * 0.6 an event is usually a clone of the desired log entry. If needed,
	 * multiple log entries can be still stored within the event as a list using
	 * a common field.
	 */
	@Deprecated
	public static final String FIELD_ENTRIES = "lf_entries";

	/**
	 * Field key for convenient method {@link #getId()}.
	 */
	public static final String FIELD_ID = "_id";

	/**
	 * Field key for convenient method {@link #getSnifferId()}.
	 */
	public static final String FIELD_SNIFFER_ID = "lf_sniffer";

	/**
	 * Field key for convenient method {@link #getLogSourceId()}.
	 */
	public static final String FIELD_SOURCE_ID = "lf_source";

	/**
	 * Field key for convenient method {@link #getLogPath()}.
	 */
	public static final String FIELD_LOG_PATH = "lf_log";

	/**
	 * Field key for convenient method {@link #getPublished()}.
	 */
	public static final String FIELD_PUBLISHED = "lf_published";

	/**
	 * Constructs empty event.
	 */
	public Event() {
		super();
	}

	/**
	 * Constructs empty event with fields copied from given log entry.
	 * 
	 * @param entry
	 *            log entry
	 */
	public Event(final LogFragment entry) {
		super(entry);
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return (String) super.get(FIELD_ID);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		super.put(FIELD_ID, id);
	}

	/**
	 * @return the snifferId
	 */
	@Override
	public long getSnifferId() {
		return (long) super.get(FIELD_SNIFFER_ID);
	}

	/**
	 * @param snifferId
	 *            the snifferId to set
	 */
	public void setSnifferId(final long snifferId) {
		super.put(FIELD_SNIFFER_ID, snifferId);
	}

	/**
	 * @return the logSourceId
	 */
	@Override
	public long getLogSourceId() {
		return (long) super.get(FIELD_SOURCE_ID);
	}

	/**
	 * @param logSourceId
	 *            the logSourceId to set
	 */
	public void setLogSourceId(final long logSourceId) {
		super.put(FIELD_SOURCE_ID, logSourceId);
	}

	/**
	 * @return the logPath
	 */
	@Override
	public String getLogPath() {
		return (String) super.get(FIELD_LOG_PATH);
	}

	/**
	 * @param logPath
	 *            the logPath to set
	 */
	public void setLogPath(final String logPath) {
		super.put(FIELD_LOG_PATH, logPath);
	}

	/**
	 * @return the published
	 */
	@Override
	public Date getPublished() {
		return (Date) super.get(FIELD_PUBLISHED);
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(final Date published) {
		super.put(FIELD_PUBLISHED, published);
		if (super.get(FIELD_TIMESTAMP) == null) {
			setTimestamp(published);
		}
	}

	/**
	 * Type safe deserializer for {@link Event}s.
	 * 
	 * @author mbok
	 *
	 */
	public static class EventTypeSafeDeserializer extends FieldsMapTypeSafeDeserializer {

		@Override
		protected FieldsMap create() {
			return new Event();
		}

	}
}
