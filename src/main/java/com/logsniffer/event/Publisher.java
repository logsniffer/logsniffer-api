package com.logsniffer.event;

import com.logsniffer.beanconfig.ConfigException;
import com.logsniffer.beanconfig.ConfiguredBean;
import com.logsniffer.beanconfig.WrappedBean;

/**
 * Publisher for an encountered event.
 * 
 * @author mbok
 * 
 */
public interface Publisher extends ConfiguredBean {
	/**
	 * Indicates a failed publishing of an event.
	 * 
	 * @author mbok
	 * 
	 */
	public class PublishException extends Exception {
		private static final long serialVersionUID = -4791344818968763109L;

		public PublishException(final String message, final Throwable cause) {
			super(message, cause);
		}

		public PublishException(final String message) {
			super(message);
		}

	}

	public void publish(Event event) throws PublishException;

	/**
	 * Wrapper for delegated publisher e.g. to allow lazy inititaing.
	 * 
	 * @author mbok
	 */
	public static abstract class PublisherWrapper implements Publisher, WrappedBean<Publisher> {
		private Publisher wrapped;

		public static final Publisher unwrap(final Publisher possiblyWrapped) {
			if (possiblyWrapped instanceof PublisherWrapper) {
				return ((PublisherWrapper) possiblyWrapped).getPublisher();
			}
			return possiblyWrapped;
		}

		private Publisher getPublisher() throws ConfigException {
			if (wrapped == null) {
				wrapped = getWrapped();
			}
			return wrapped;
		}

		@Override
		public void publish(final Event event) throws PublishException {
			try {
				getPublisher().publish(event);
			} catch (final ConfigException e) {
				throw new PublishException("Failed to create configured publisher", e);
			}
		}

	}
}
