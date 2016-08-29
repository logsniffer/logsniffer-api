package com.logsniffer.app;

import java.io.File;

/**
 * Represents the home directory wrapper for LogSniffer initiated at start up.
 * The home directory keeps all runtime and configuration data related to the
 * same LogSniffer instance.
 * 
 * The home directory is write enabled.
 * 
 * @author mbok
 * 
 */
public interface LogSnifferHome {
	/**
	 * 
	 * @return write enabled home directory.
	 */
	File getHomeDir();
}
