package com.logsniffer.util;

/**
 * Common query builder for listing entries.
 * 
 * @author mbok
 * 
 * @param <ResultType>
 *            Result type
 */
public interface ListQueryBuilder<ResultType extends PageableResult<?>> {
	public ResultType list();
}
