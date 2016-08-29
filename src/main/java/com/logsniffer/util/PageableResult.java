package com.logsniffer.util;

import java.util.ArrayList;
import java.util.List;

import com.logsniffer.util.messages.Message;

/**
 * Result wrapper for pageable result items.
 * 
 * @author mbok
 * 
 * @param <ItemType>
 *            the result item type
 */
public class PageableResult<ItemType> {
	private long totalCount;
	private List<ItemType> items;
	private final List<Message> messages = new ArrayList<>();

	public PageableResult(final long totalCount, final List<ItemType> items) {
		super();
		this.totalCount = totalCount;
		this.items = items;
	}

	public PageableResult() {
		super();
	}

	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the items
	 */
	public List<ItemType> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(final List<ItemType> items) {
		this.items = items;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

}
