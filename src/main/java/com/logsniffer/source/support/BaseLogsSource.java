package com.logsniffer.source.support;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.logsniffer.field.FieldsMap;
import com.logsniffer.reader.filter.FilteredLogEntryReader;
import com.logsniffer.source.LogInputStream;
import com.logsniffer.source.LogRawAccess;
import com.logsniffer.source.LogSource;
import com.logsniffer.source.Navigation.NavigationType;
import com.logsniffer.util.json.Views;

/**
 * Base log source.
 * 
 * @author mbok
 * 
 */
public abstract class BaseLogsSource<ACCESSTYPE extends LogRawAccess<? extends LogInputStream>>
		implements LogSource<ACCESSTYPE> {
	@JsonProperty
	@JsonView(Views.Info.class)
	private long id;

	@JsonProperty
	@JsonView(Views.Info.class)
	@NotEmpty
	private String name;

	@JsonProperty
	@JsonView(Views.Info.class)
	@Valid
	@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
	private FilteredLogEntryReader<ACCESSTYPE> reader = new FilteredLogEntryReader<>();

	@JsonProperty
	@JsonView(Views.Info.class)
	private FieldsMap uiSettings = new FieldsMap();

	@JsonProperty
	@JsonView(Views.Info.class)
	protected boolean readerConfigurable = true;

	/**
	 * @return the id
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the reader
	 */
	@Override
	public FilteredLogEntryReader<ACCESSTYPE> getReader() {
		return reader;
	}

	/**
	 * @param reader
	 *            the reader to set
	 */
	public void setReader(final FilteredLogEntryReader<ACCESSTYPE> reader) {
		this.reader = reader;
	}

	/**
	 * @return the uiSettings
	 */
	@Override
	public FieldsMap getUiSettings() {
		return uiSettings;
	}

	/**
	 * @param uiSettings
	 *            the uiSettings to set
	 */
	public void setUiSettings(final FieldsMap uiSettings) {
		this.uiSettings = uiSettings;
	}

	/**
	 * Indicates if the reader is configurable for this source. Sometimes a log
	 * source brings its own reader.
	 * 
	 * @return the readerConfigurable
	 */
	public boolean isReaderConfigurable() {
		return readerConfigurable;
	}

	/**
	 * Returns the natively supported navigation type by this source.
	 * 
	 * @return the natively supported navigation type by this source.
	 */
	@JsonProperty(access = Access.READ_ONLY)
	@JsonView(Views.Info.class)
	public abstract NavigationType getNavigationType();

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}
}
