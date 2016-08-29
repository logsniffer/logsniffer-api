package com.logsniffer.app.configvalue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logsniffer.app.ConfigValueAppConfig;
import com.logsniffer.app.CoreAppConfig;
import com.logsniffer.app.configvalue.ConfigInjectorTest.HelperAppConfig;

/**
 * Test for {@link ConfigInjector}.
 * 
 * @author mbok
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigValueAppConfig.class, HelperAppConfig.class, CoreAppConfig.class })
public class ConfigInjectorTest {

	@Configuration
	public static class HelperAppConfig {
		@Bean
		@Primary
		public ConfigValueSource source() {
			return Mockito.mock(ConfigValueSource.class);
		}
	}

	@Autowired
	private ConfigValueSource source;

	@Configured(value = "test.abc", defaultValue = "def")
	private ConfigValue<String> testDefault;

	@Configured("test.bool")
	private ConfigValue<Boolean> testBool;

	@Test
	public void testDefaultValue() {
		Assert.assertEquals("def", testDefault.get());
		Mockito.when(source.getValue("test.bool")).thenReturn("true");
		Assert.assertEquals(true, testBool.get());
	}
}
