package com.logsniffer.model.support;

import java.io.IOException;

import com.logsniffer.model.LogPointer;

import net.sf.json.JSONObject;

/**
 * Pointer inside one log.
 * 
 * @author mbok
 * 
 */
public class DefaultPointer implements LogPointer {
	private final long offset;
	private final long size;
	private String json;

	public DefaultPointer(final long offset, final long size) {
		this.offset = offset;
		this.size = size;
	}

	public long getOffset() throws IOException {
		return offset;
	}

	@Override
	public boolean isEOF() {
		return offset >= size;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (offset ^ offset >>> 32);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DefaultPointer other = (DefaultPointer) obj;
		if (offset != other.offset) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isSOF() {
		return offset == 0;
	}

	@Override
	public String toString() {
		return "SingleFilePointer [offset=" + offset + ", size=" + size + "]";
	}

	@Override
	public String getJson() {
		if (json == null) {
			json = "{\"o\":" + offset + ",\"s\":" + size + "}";
		}
		return json;
	}

	public static DefaultPointer fromJSON(final String data) {
		final JSONObject json = JSONObject.fromObject(data);
		if (json.has("o") && json.has("s")) {
			return new DefaultPointer(json.getLong("o"), json.getLong("s"));
		} else {
			return null;
		}
	}

}