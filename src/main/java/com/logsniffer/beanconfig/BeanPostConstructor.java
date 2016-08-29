package com.logsniffer.beanconfig;

/**
 * A post constructor for configured beans.
 * 
 * @author mbok
 * 
 */
public interface BeanPostConstructor<BeanType> {

	/**
	 * The bean creation and configuration is split by
	 * {@link BeanConfigFactoryManager} into two phases: 1) creating the bean
	 * with proper config using the {@link #createBean(BeanConfig)} method 2)
	 * adapting further bean attributes in a post construct step, usually used
	 * to create nested beans.
	 * 
	 * @param bean
	 *            the configured bean
	 * @param configManager
	 *            involved config manager instance, which can be used to create
	 *            nested beans
	 * @throws ConfigException
	 *             in case of errors.
	 */
	void postConstruct(BeanType bean, BeanConfigFactoryManager configManager) throws ConfigException;
}
