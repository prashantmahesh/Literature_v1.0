package litcore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Keeps track of subscriptions of different EventHandlers and posts events, making each respective EventHandler handle the event.
 *
 */
public class EventDispatcher {
	
	public EventDispatcher() {
		eventToHandler = new HashMap<EventType,ArrayList<EventHandler> >();
	}
	
	/**
	 * Registers the EventHandler to the given EventType, can listen to the specified EventType after this function call.
	 * @param aEventType - The EventType to which the EventHandler is registering
	 * @param aEventHandler - The EventHandler which needs to register to the EventType
	 */
	public void registerEventHandler(EventType aEventType,EventHandler aEventHandler) {
		if(eventToHandler.containsKey(aEventType)) {
			eventToHandler.get(aEventType).add(aEventHandler);
		}
		else {
			ArrayList<EventHandler> temp = new ArrayList<EventHandler>();
			temp.add(aEventHandler);
			eventToHandler.put(aEventType, temp);
		}
	}
	
	/**
	 * Deregisters the EventHandler from the given EventType, cannot listen to the specified EventType after this function call.
	 * @param aEventType
	 * @param aEventHandler
	 */
	public void deregisterEventHandler(EventType aEventType,EventHandler aEventHandler) {
		if(eventToHandler.containsKey(aEventType)) {
			eventToHandler.get(aEventType).remove(aEventHandler);
		}
	}
	
	/**
	 * Posts the event, making the <i>eventHandlers</i> registered to the eventType <i>handle</i> the event.
	 * @param aEvent - Event being posted
	 */
	public void postEvent(Event aEvent) {
		ArrayList<EventHandler> eventHandlers = eventToHandler.get(aEvent.getEventType());
		for(int i = 0;i < eventHandlers.size();i++) {
			eventHandlers.get(i).handleEvent(aEvent);
		}
	}
	
	
	private HashMap<EventType,ArrayList<EventHandler> > eventToHandler; 
}
