package litcore.Events;

import litcore.EventType;
import litcore.Player;
import litcore.util.Event;

public class TurnChangeEvent extends Event {
	public TurnChangeEvent(Player aNewPlayer) {
		super(EventType.TURNCHANGE);
		newPlayer = aNewPlayer;
	}
	
	public Player getNewPlayer() {
		return newPlayer;
	}
	private Player newPlayer;
}
