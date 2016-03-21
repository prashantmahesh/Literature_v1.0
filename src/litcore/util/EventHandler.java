package litcore.util;

/**
 * Interface to define the EventHandler functionality. 
 *
 */
public interface EventHandler {
	
	/**
	 * Abstract function to handle events. 
	 * 
	 * @param aEvent
	 */
	public void handleEvent(Event aEvent);
	
	/**
	 * @param aEventDispatcher
	 */
	public void setEventDispatcher(EventDispatcher aEventDispatcher);
	
	/**
	 * 
	 * @return The <i>eventDispatcher</i> object
	 */
	public EventDispatcher getEventDispatcher();
	
	/**
	 * Register all event types in this function. Typically called in constructor.
	 */
	public void registerForEvents();
}
