package com.logsniffer.event;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.logsniffer.util.ListQueryBuilder;
import com.logsniffer.util.PageableResult;

/**
 * Persistence for events.
 * 
 * @author mbok
 * 
 */
public interface EventPersistence {

	/**
	 * A histrogram entry.
	 * 
	 * @author mbok
	 * 
	 */
	public static class HistogramEntry {
		private final long time;
		private final long count;

		public HistogramEntry(final long time, final long count) {
			super();
			this.time = time;
			this.count = count;
		}

		/**
		 * @return the time
		 */
		public long getTime() {
			return time;
		}

		/**
		 * @return the count
		 */
		public long getCount() {
			return count;
		}

	}

	/**
	 * Valid histogram intervals.
	 * 
	 * @author mbok
	 * 
	 */
	public enum HistogramInterval {
		SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR
	}

	public static class EventsCountHistogram {
		private List<HistogramEntry> entries;
		private HistogramInterval interval;

		public EventsCountHistogram() {
			super();
		}

		public EventsCountHistogram(final List<HistogramEntry> entries, final HistogramInterval interval) {
			super();
			this.entries = entries;
			this.interval = interval;
		}

		/**
		 * @return the entries
		 */
		public List<HistogramEntry> getEntries() {
			return entries;
		}

		/**
		 * @param entries
		 *            the entries to set
		 */
		public void setEntries(final List<HistogramEntry> entries) {
			this.entries = entries;
		}

		/**
		 * @return the interval
		 */
		public HistogramInterval getInterval() {
			return interval;
		}

		/**
		 * @param interval
		 *            the interval to set
		 */
		public void setInterval(final HistogramInterval interval) {
			this.interval = interval;
		}

	}

	/**
	 * Event result attached by event histogram.
	 * 
	 * @author mbok
	 * 
	 */
	public static class EventsResult extends PageableResult<Event> {
		private EventsCountHistogram eventsCountHistogram;

		public EventsResult() {
			super();
		}

		public EventsResult(final long totalCount, final List<Event> items, final EventsCountHistogram histogram) {
			super(totalCount, items);
			this.eventsCountHistogram = histogram;
		}

		/**
		 * @return the eventsCountHistogram
		 */
		public EventsCountHistogram getEventsCountHistogram() {
			return eventsCountHistogram;
		}

		/**
		 * @param eventsCountHistogram
		 *            the eventsCountHistogram to set
		 */
		public void setEventsCountHistogram(final EventsCountHistogram eventsCountHistogram) {
			this.eventsCountHistogram = eventsCountHistogram;
		}

	}

	public static interface BaseEventQueryBuilder<BuilderType> extends ListQueryBuilder<EventsResult> {
		BuilderType withEventCountTimeHistogram(int maxHistogramIntervalSlots);
	}

	public static interface EventQueryBuilder extends BaseEventQueryBuilder<EventQueryBuilder> {

		EventQueryBuilder sortByEntryTimestamp(boolean desc);

		EventQueryBuilder withOccurrenceFrom(Date from);

		EventQueryBuilder withOccurrenceTo(Date to);
	}

	public static interface NativeQueryBuilder extends BaseEventQueryBuilder<NativeQueryBuilder> {
		NativeQueryBuilder withNativeQuery(final String nativeQuery);
	}

	public String persist(Event event);

	public void delete(long snifferId, String[] eventIds);

	public void deleteAll(long snifferId);

	public EventQueryBuilder getEventsQueryBuilder(long snifferId, long offset, int limit);

	public NativeQueryBuilder getEventsNativeQueryBuilder(long snifferId, long offset, int limit);

	public Event getEvent(long snifferId, String eventId);

	/**
	 * Determines event counts for given sniffer ids.
	 * 
	 * @param snifferIds
	 *            not null array of sniffer ids
	 * @return map of entries with sniffer id as key and value as events count
	 */
	public Map<Long, Long> getEventCount(long[] snifferIds);

}
