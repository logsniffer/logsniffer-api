package com.logsniffer.beanconfig;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logsniffer.app.CoreAppConfig;
import com.logsniffer.source.Log;
import com.logsniffer.source.LogInputStream;
import com.logsniffer.source.LogRawAccess;
import com.logsniffer.source.LogSource;
import com.logsniffer.source.Navigation.NavigationType;
import com.logsniffer.source.support.BaseLogsSource;

/**
 * Test for serializing / deserializing {@link ConfiguredBean}s.
 * 
 * @author mbok
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreAppConfig.class })
@Configuration
public class ConfigBeanJsonTest {
	private static final Logger logger = LoggerFactory.getLogger(ConfigBeanJsonTest.class);
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testSerializing() throws IOException {
		final TestLogsSource source = new TestLogsSource();
		source.setName("Test");
		final String json = mapper.writeValueAsString(source);
		logger.info("Serialized bean: {}", json);

		// Deserialize
		final LogSource source2 = mapper.readValue(json, LogSource.class);
		Assert.assertEquals(TestLogsSource.class, source2.getClass());
	}

	/**
	 * Helper log source.
	 * 
	 * @author mbok
	 *
	 */
	private static class TestLogsSource extends BaseLogsSource<LogRawAccess<? extends LogInputStream>> {

		@Override
		public List<Log> getLogs() throws IOException {
			return null;
		}

		@Override
		public Log getLog(final String path) throws IOException {
			return null;
		}

		@Override
		public LogRawAccess<? extends LogInputStream> getLogAccess(final Log log) throws IOException {
			return null;
		}

		@Override
		public NavigationType getNavigationType() {
			return null;
		}

	}
}
