package com.logsniffer.field.filter;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.field.FieldBaseTypes;
import com.logsniffer.field.FieldsMap;
import com.logsniffer.reader.LogEntryReader;

/**
 * Filter for {@link FieldsMap}.
 * 
 * @author mbok
 * 
 */
public interface FieldsFilter extends ConfiguredBean {
	/**
	 * Filters passed fields.
	 * 
	 * @param fields
	 *            fields to filter
	 */
	void filter(FieldsMap fields) throws IOException;

	/**
	 * Filters known fields.
	 * 
	 * @param knownFields
	 *            fields supported and known by a {@link LogEntryReader}
	 */
	void filterKnownFields(LinkedHashMap<String, FieldBaseTypes> knownFields) throws IOException;

}
