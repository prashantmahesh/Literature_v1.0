package litcore.Events;

import litcore.EventType;
import litcore.AskCard.AskCardOutcome;
import litcore.util.Event;

public class AskCardOutcomeEvent extends Event {
	public AskCardOutcomeEvent(AskCardOutcome aAskCardOutcome) {
		super(EventType.ASKCARD_OUTCOME);
		askCardOutcome = aAskCardOutcome;
	}
	
	public AskCardOutcome getAskCardOutcome() {
		return askCardOutcome;
	}
	
	private AskCardOutcome askCardOutcome;
}
