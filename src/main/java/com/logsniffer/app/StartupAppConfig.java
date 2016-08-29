package com.logsniffer.app;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * App config for startup routines.
 * 
 * @author mbok
 * 
 */
@Configuration
public class StartupAppConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${logsniffer.home}")
	private String logSnifferHomeDir;

	@Value("${logsniffer.version}")
	private String version;

	/**
	 * Checks the home dir under ${logsniffer.home} for write access, creates it
	 * if necessary and writes a template config.properties.
	 * 
	 * @return home directory representation
	 * @throws Exception
	 */
	@Bean
	public LogSnifferHome homeDir() throws Exception {
		final File logSnifferHomeDirFile = new File(logSnifferHomeDir);
		logger.info("Starting Logsniffer {} with home directory {}", version, logSnifferHomeDirFile.getPath());
		if (!logSnifferHomeDirFile.exists()) {
			logger.info("Home directory is't present, going to create it");
			try {
				logSnifferHomeDirFile.mkdirs();
			} catch (final Exception e) {
				logger.error(
						"Failed to create home directory \"" + logSnifferHomeDirFile.getPath()
								+ "\". Logsniffer can't operate without a write enabled home directory. Please create the home directory manually and grant the user Logsniffer is running as the write access.",
						e);
				throw e;
			}
		} else if (!logSnifferHomeDirFile.canWrite()) {
			logger.error(
					"Configured home directory \"{}\" isn't write enabled. Logsniffer can't operate without a write enabled home directory. Please grant the user Logsniffer is running as the write access.",
					logSnifferHomeDirFile.getPath());
			throw new SecurityException(
					"Configured home directory \"" + logSnifferHomeDirFile.getPath() + "\" isn't write enabled.");
		}
		final File homeConfigProps = new File(logSnifferHomeDirFile, "config.properties");
		if (!homeConfigProps.exists()) {
			final FileOutputStream fo = new FileOutputStream(homeConfigProps);
			try {
				new Properties().store(fo, "Place here Logsniffer settings");
			} finally {
				fo.close();
			}
		}

		return new LogSnifferHome() {
			@Override
			public File getHomeDir() {
				return new File(logSnifferHomeDir);
			}
		};
	}
}
