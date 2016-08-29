package com.logsniffer.event;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.logsniffer.model.LogPointer;
import com.logsniffer.source.LogPointerFactory;

import net.sf.json.JSONObject;

/**
 * Increment data container for continuous event scanning process.
 * 
 * @author mbok
 * 
 */
public final class IncrementData {
	private LogPointer nextOffset;
	private JSONObject data = new JSONObject();

	/**
	 * @return the nextOffset
	 */
	public LogPointer getNextOffset() {
		return nextOffset;
	}

	/**
	 * 
	 * @return the next offset transfered to target pointer, if pointer wasn't
	 *         set before, null is returned that corresponds to the start.
	 * 
	 * @throws IOException
	 */
	public LogPointer getNextOffset(final LogPointerFactory pointerFactory) throws IOException {
		return nextOffset != null && StringUtils.isNotBlank(nextOffset.getJson())
				? pointerFactory.getFromJSON(nextOffset.getJson()) : null;
	}

	/**
	 * @param nextOffset
	 *            the nextOffset to set
	 */
	public void setNextOffset(final LogPointer nextOffset) {
		this.nextOffset = nextOffset;
	}

	/**
	 * @return the data
	 */
	public JSONObject getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final JSONObject data) {
		this.data = data;
	}

}
