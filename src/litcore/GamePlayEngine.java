package litcore;

import litcore.Events.TurnChangeEvent;
import litcore.util.Event;
import litcore.util.EventDispatcher;
import litcore.util.EventHandler;

public class GamePlayEngine implements EventHandler {
	
	public GamePlayEngine(EventDispatcher aEventDispatcher,int aNumberOfPlayers) {
		eventDispatcher = aEventDispatcher;
		numberOfPlayers = aNumberOfPlayers;
		players = new Player[numberOfPlayers];
		registerForEvents();
	}

	@Override
	public void handleEvent(Event aEvent) {
		switch(aEvent.getEventType()) {
		
		case TURNCHANGE: {
			currentPlayer = ((TurnChangeEvent)aEvent).getNewPlayer();
			break;
		}
		default:
			break;
		
		}
	}

	@Override
	public void setEventDispatcher(EventDispatcher aEventDispatcher) {
		eventDispatcher = aEventDispatcher;
	}

	@Override
	public EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	@Override
	public void registerForEvents() {
		getEventDispatcher().registerEventHandler(EventType.TURNCHANGE, this);
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void distribute(Deck aDeck) {
		for(int i = 0;i < 48;i++) {
			players[i % numberOfPlayers].addCard(aDeck.get(i));
		}
	}
	
	public void startGame() {
		getEventDispatcher().postEvent(new TurnChangeEvent(players[0]));
		while(true) {
			Event evt = new Event(EventType.NEWTURN);
			getEventDispatcher().postEvent(evt);
			currentPlayer.play();
		}
	}
	
	private EventDispatcher eventDispatcher;
	private int numberOfPlayers;
	private Player[] players;
	private Player currentPlayer;
}
