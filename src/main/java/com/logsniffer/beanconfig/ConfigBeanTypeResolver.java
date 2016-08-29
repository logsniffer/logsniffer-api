package com.logsniffer.beanconfig;

/**
 * Resolves type for a {@link ConfiguredBean} class and vice versa used for JSON
 * seriliaztion / desrialiaztion.
 * 
 * @author mbok
 * 
 */
public interface ConfigBeanTypeResolver {
	/**
	 * Resolves a name for given class which can be used later to resolve the
	 * corresponding class.
	 * 
	 * @param clazz
	 *            class of config bean
	 * @return name for given class which can be used later to resolve the
	 *         corresponding class
	 * @return in case no names are defined for the given type
	 */
	String resolveTypeName(Class<? extends ConfiguredBean> clazz) throws ConfigException;

	/**
	 * Resolves the config bean class which corresponds to the given name. Note
	 * that by design multiple names can reference the same class to support
	 * upgrade compatibility.
	 * 
	 * @param name
	 *            name of config bean
	 * @param wantedSuperType
	 *            super type
	 * @return config bean class which corresponds to the given name
	 * @throws ConfigException
	 *             in case of no class could be found which matches the given
	 *             name or in case of embedded exceptions
	 */
	<T extends ConfiguredBean> Class<? extends T> resolveTypeClass(String name, Class<T> wantedSuperType)
			throws ConfigException;
}
