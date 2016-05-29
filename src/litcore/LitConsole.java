package litcore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import litcore.Card.Suit;
import litcore.AskCard.AskCardOutcome;
import litcore.AskCard.AskCardOutcomeType;
import litcore.AskCard.AskCardQuery;
import litcore.Events.AskCardOutcomeEvent;
import litcore.Events.AskCardQueryEvent;
import litcore.Events.TurnChangeEvent;
import litcore.util.Event;
import litcore.util.EventDispatcher;
import litcore.util.EventHandler;

public class LitConsole implements EventHandler {
	
	public static final String INVALID_PLAYER_MESSAGE = "Enter a valid player name: ";
	public static final String INVALID_CARD_MESSAGE = "Enter a valid card: ";
	
	public LitConsole(EventDispatcher aEventDispatcher,GamePlayEngine aGamePlayEngine) {
		eventDispatcher = aEventDispatcher;
		gamePlayEngine = aGamePlayEngine;
		in = new InputReader(System.in);
		registerForEvents();
	}

	@Override
	public void handleEvent(Event aEvent) {
		switch (aEvent.getEventType()) {
			case ASKCARD_QUERY: {
				AskCardQueryEvent askCardQueryEvent = (AskCardQueryEvent)aEvent;
				AskCardQuery askCardQuery = askCardQueryEvent.getAskCardQuery();
				log(askCardQuery.getAsker() + " asks " + askCardQuery.getRespondent() + " the card " + askCardQuery.getCard());
				break;
			}
			case ASKCARD_OUTCOME: {
				AskCardOutcomeEvent askCardOutcomeEvent = (AskCardOutcomeEvent)aEvent;
				AskCardOutcome askCardOutcome = askCardOutcomeEvent.getAskCardOutcome();
				AskCardQuery askCardQuery = askCardOutcome.getAskCardQuery();
				if(askCardOutcome.getOutcomeType() == AskCardOutcomeType.CardNotPresent) {
					log(askCardQuery.getRespondent() + " does not have the card " + askCardQuery.getCard());
				}
				else {
					log(askCardQuery.getRespondent() + " gave the card " + askCardQuery.getCard() + " to " + askCardQuery.getAsker());
				}
				break;
			}
			case TURNCHANGE: {
				TurnChangeEvent turnChangeEvent = (TurnChangeEvent)aEvent;
				log(turnChangeEvent.getNewPlayer() + "'s turn: ");
				break;
			}
			case NEWTURN: {
				
				break;
			}
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
		getEventDispatcher().registerEventHandler(EventType.ASKCARD_OUTCOME,this);
		getEventDispatcher().registerEventHandler(EventType.ASKCARD_QUERY,this);
		getEventDispatcher().registerEventHandler(EventType.NEWTURN,this);
		getEventDispatcher().registerEventHandler(EventType.TURNCHANGE,this);
	}
	
	public void printCards(Player aPlayer) {
		System.out.println(aPlayer + "'s cards: ");
		TreeSet<Card> cards = aPlayer.getCards();
		Iterator<Card> it = cards.iterator();
		int c = 1;
		while(it.hasNext()) {
			System.out.println(c + ". " + it.next());
			c++;
		}
	}
	
	public Player getPlayer(String message) {
		System.out.print(message);
		String name = in.next();
		Player[] players = gamePlayEngine.getPlayers();
		for(int i = 0;i < gamePlayEngine.getNumberOfPlayers();i++) {
			if(name.equals(players[i].getName())) {
				return players[i];
			}
		}
		return getPlayer(INVALID_PLAYER_MESSAGE); 
	}
	
	public Card getCard(String message) {
		System.out.print(message);
		String rankString = in.next();
		String suitString = in.next();
		rankString = rankString.toLowerCase();
		int rank = 0;
		try {
			rank = Integer.parseInt(rankString);
		} catch(NumberFormatException e) {
			switch (rankString) {
				case "jack": {
					rank = Card.JACK;
					break;
				}
				case "queen": {
					rank = Card.QUEEN;
					break;
				}
				case "king": {
					rank = Card.KING;
					break;
				}
				case "ace": {
					rank = Card.ACE;
					break;
				}
				default: {
					rank = 0;
					break;
				}
			}
		}
		if(rank < 2 || rank > Card.ACE) {
			return getCard(INVALID_CARD_MESSAGE);
		}
		Card.Suit suit = null;
		suitString = suitString.toLowerCase();
		switch (suitString) {
			case "hearts": {
				suit = Suit.HEARTS;
				break;
			}
			case "dice": {
				suit = Suit.DICE;
				break;
			}
			case "spades": {
				suit = Suit.SPADES;
				break;
			}
			case "clubs": {
				suit = Suit.CLUBS;
				break;
			}
			default: {
				suit = null;
				break;
			}
		}
		if(suit == null) {
			return getCard(INVALID_CARD_MESSAGE);
		}
		return new Card(rank, suit);
	}
	
	public void log(String aMessage) {
		System.out.println(aMessage);
	}
	
	public void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public void waitForEnterKey() throws IOException {
		System.out.println("Press Enter to Continue..");
		System.in.read();
	}

	private EventDispatcher eventDispatcher;
	private GamePlayEngine gamePlayEngine;
	private InputReader in;
	
	class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
		
		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream),32768);
			tokenizer = null;
		}
		
		public String next() {
			while(tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				}
				catch(IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		public int nextInt() {
			return Integer.parseInt(next());
		}
		public double nextDouble() {
			return Double.parseDouble(next());
		}
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}
