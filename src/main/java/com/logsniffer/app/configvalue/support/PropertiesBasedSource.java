package com.logsniffer.app.configvalue.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.logsniffer.app.CoreAppConfig;
import com.logsniffer.app.LogSnifferHome;
import com.logsniffer.app.configvalue.ConfigValueStore;

/**
 * Retrieves properties from
 * {@link CoreAppConfig#logSnifferProperties(org.springframework.context.ApplicationContext)}
 * . To fulfill the {@link ConfigValueStore} interface the properties are stored
 * to permanently to {@link CoreAppConfig#LOGSNIFFER_PROPERTIES_FILE} and
 * replace in the bean
 * {@link CoreAppConfig#logSnifferProperties(org.springframework.context.ApplicationContext)}
 * during current runtime.
 * 
 * @author mbok
 * 
 */
public class PropertiesBasedSource implements ConfigValueStore {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesBasedSource.class);
	@Autowired
	private LogSnifferHome homeDir;

	@Autowired
	@Qualifier(CoreAppConfig.BEAN_LOGSNIFFER_PROPS)
	private Properties logsnifferProperties;

	@Override
	public String getValue(final String key) {
		return logsnifferProperties.getProperty(key);
	}

	@Override
	public void store(final String key, final String value) throws IOException {
		if (value != null) {
			logsnifferProperties.setProperty(key, value);
		} else {
			logsnifferProperties.remove(key);
		}
		final File file = new File(homeDir.getHomeDir(), CoreAppConfig.LOGSNIFFER_PROPERTIES_FILE);
		LOGGER.info("Saving config value for key '{}' to file: {}", key, file.getAbsolutePath());
		final Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (final IOException e) {
			LOGGER.warn("Failed to load current properties from file, continue with empty properties: "
					+ file.getAbsolutePath(), e);
		}
		if (value != null) {
			properties.setProperty(key, value);
		} else {
			properties.remove(key);
		}
		properties.store(new FileOutputStream(file), null);
	}

}
