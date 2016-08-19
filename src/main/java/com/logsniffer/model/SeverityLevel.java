package com.logsniffer.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a severity level of a {@link LogFragment}.
 * 
 * @author mbok
 * 
 */
public final class SeverityLevel implements Comparable<SeverityLevel> {

	/**
	 * Due to severity levels are arbitrary a rough static classification is
	 * required e.g. to highlight entries in relation to the class. The
	 * classification is adapted to the syslog severity field.
	 * 
	 * @author mbok
	 * 
	 */
	@JsonFormat(shape = Shape.NUMBER)
	public enum SeverityClassification {
		EMERGENCY, ALERT, CRITICAL, ERROR, WARNING, NOTICE, INFORMATIONAL, DEBUG, TRACE;
	}

	private int ordinalNumber;
	@NotNull
	private SeverityClassification classification = SeverityClassification.INFORMATIONAL;
	@NotEmpty
	private String name;

	public SeverityLevel() {
		super();
	}

	public SeverityLevel(final String name, final int ordinalNumber, final SeverityClassification classification) {
		super();
		this.name = name;
		this.ordinalNumber = ordinalNumber;
		this.classification = classification;
	}

	/**
	 * @return the ordinalNumber
	 */
	@JsonProperty("o")
	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	/**
	 * @return the classification
	 */
	@JsonProperty("c")
	public SeverityClassification getClassification() {
		return classification;
	}

	/**
	 * @return the name
	 */
	@JsonProperty("n")
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(final SeverityLevel o) {
		return ((Integer) this.getOrdinalNumber()).compareTo(o.getOrdinalNumber());
	}

	/**
	 * @param ordinalNumber
	 *            the ordinalNumber to set
	 */
	public void setOrdinalNumber(final int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	/**
	 * @param classification
	 *            the classification to set
	 */
	public void setClassification(final SeverityClassification classification) {
		this.classification = classification;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classification == null) ? 0 : classification.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ordinalNumber;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SeverityLevel))
			return false;
		final SeverityLevel other = (SeverityLevel) obj;
		if (classification != other.classification)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ordinalNumber != other.ordinalNumber)
			return false;
		return true;
	}

}
