package com.logsniffer.util;

/**
 * Represents a reference integrity violation in case of deletions.
 * 
 * @author mbok
 * 
 */
public class ReferenceIntegrityException extends Exception {

	private static final long serialVersionUID = 448286180205979538L;
	private final Class<?> resourceType;

	/**
	 * @param message
	 * @param cause
	 */
	public ReferenceIntegrityException(final Class<?> resourceType, final Throwable cause) {
		super(cause);
		this.resourceType = resourceType;
	}

	/**
	 * @return the resourceType
	 */
	public Class<?> getResourceType() {
		return resourceType;
	}
}
