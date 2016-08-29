package com.logsniffer.beanconfig;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.logsniffer.beanconfig.WrappedBean.WrapperSerializer;

/**
 * Wrapper bean for lazy unmarshalling of configured beans. Specially used to
 * delegate JSON serialization to the wrapped bean.
 * 
 * @author mbok
 * 
 * @param <BeanType>
 *            the wrapped bean type
 */
@JsonSerialize(using = WrapperSerializer.class)
public interface WrappedBean<BeanType extends ConfiguredBean> extends ConfiguredBean {
	public static class WrapperSerializer extends JsonSerializer<WrappedBean<ConfiguredBean>> {

		@Override
		public void serialize(final WrappedBean<ConfiguredBean> value, final JsonGenerator jgen,
				final SerializerProvider provider) throws IOException, JsonProcessingException {
			provider.defaultSerializeValue(value.getWrapped(), jgen);
		}

		@Override
		public void serializeWithType(final WrappedBean<ConfiguredBean> value, final JsonGenerator jgen,
				final SerializerProvider provider, final TypeSerializer typeSer)
				throws IOException, JsonProcessingException {
			provider.defaultSerializeValue(value.getWrapped(), jgen);
		}

	}

	BeanType getWrapped() throws ConfigException;
}
