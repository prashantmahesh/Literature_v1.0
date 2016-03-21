package litcore.util;

import litcore.EventType;

/**
 * Class to define Events occurring within the application.
 * 
 */
public class Event {
	
	/**
	 * 
	 * @param aEventType - Every Event must have an EventType.
	 */
	public Event(EventType aEventType) {
		eventType = aEventType;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	private EventType eventType;
}
