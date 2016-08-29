package com.logsniffer.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.logsniffer.app.configvalue.ConfigInjector;
import com.logsniffer.app.configvalue.support.PropertiesBasedSource;

/**
 * Enables the configuration injection and refreshing.
 * 
 * @author mbok
 * 
 */
@Configuration
public class ConfigValueAppConfig {
	public static final String LOGSNIFFER_BASE_URL = "logsniffer.baseUrl";

	@Bean
	public ConfigInjector configInjector() {
		return new ConfigInjector();
	}

	@Bean
	public PropertiesBasedSource propertiesBasedSource() {
		return new PropertiesBasedSource();
	}

	@Bean
	public ConversionService conversionService() {
		return new DefaultFormattingConversionService();
	}
}
