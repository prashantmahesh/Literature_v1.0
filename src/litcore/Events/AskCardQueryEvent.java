package litcore.Events;

import litcore.EventType;
import litcore.AskCard.AskCardQuery;
import litcore.util.Event;

public class AskCardQueryEvent extends Event {
	public AskCardQueryEvent(AskCardQuery aAskCardQuery) {
		super(EventType.ASKCARD_QUERY);
		askCardQuery = aAskCardQuery;
	}
	
	AskCardQuery getAskCardQuery() {
		return askCardQuery;
	}
	
	private AskCardQuery askCardQuery;
	
}
