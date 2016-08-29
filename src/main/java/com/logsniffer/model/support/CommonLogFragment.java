package com.logsniffer.model.support;

import java.util.Date;

import com.logsniffer.field.FieldsMap;
import com.logsniffer.model.LogFragment;
import com.logsniffer.model.LogPointer;
import com.logsniffer.model.SeverityLevel;

/**
 * Common super class for events and entries.
 * 
 * @author mbok
 *
 */
public class CommonLogFragment extends FieldsMap implements LogFragment {
	private static final long serialVersionUID = 8822045650879255490L;

	/**
	 * Instantiates with empty fields map.
	 */
	public CommonLogFragment() {
		super();
	}

	/**
	 * Instantiates by copying fields from given map.
	 * 
	 * @param copy
	 */
	public CommonLogFragment(final FieldsMap copy) {
		super(copy);
	}

	/**
	 * Instantiates by copying fields from log gragment.
	 * 
	 * @param copy
	 */
	public CommonLogFragment(final LogFragment logFragment) {
		super(logFragment);
	}

	/**
	 * @return the startOffset
	 */
	@Override
	public LogPointer getStartOffset() {
		return (LogPointer) super.get(FIELD_START_OFFSET);
	}

	/**
	 * @param startOffset
	 *            the startOffset to set
	 */
	@Override
	public void setStartOffset(final LogPointer startOffset) {
		super.put(FIELD_START_OFFSET, startOffset);
	}

	/**
	 * @return the endOffset
	 */
	@Override
	public LogPointer getEndOffset() {
		return (LogPointer) super.get(FIELD_END_OFFSET);
	}

	/**
	 * @param endOffset
	 *            the endOffset to set
	 */
	@Override
	public void setEndOffset(final LogPointer endOffset) {
		super.put(FIELD_END_OFFSET, endOffset);
	}

	/**
	 * @return the level
	 */
	@Override
	public final SeverityLevel getSeverity() {
		return (SeverityLevel) super.get(FIELD_SEVERITY_LEVEL);
	}

	/**
	 * @param level
	 *            the severity level to set
	 */
	@Override
	public void setSeverity(final SeverityLevel level) {
		super.put(FIELD_SEVERITY_LEVEL, level);
	}

	/**
	 * @return the timeStamp
	 */
	@Override
	public final Date getTimestamp() {
		return (Date) super.get(FIELD_TIMESTAMP);
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	@Override
	public void setTimestamp(final Date timeStamp) {
		super.put(FIELD_TIMESTAMP, timeStamp);
	}

}
