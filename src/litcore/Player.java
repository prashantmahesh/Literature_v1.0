package litcore;

import java.util.ArrayList;
import java.util.TreeSet;

import litcore.AskCard.*;
import litcore.Events.AskCardOutcomeEvent;
import litcore.Events.AskCardQueryEvent;
import litcore.Events.TurnChangeEvent;
import litcore.util.Event;
import litcore.util.EventDispatcher;
import litcore.util.EventHandler;

import java.util.Iterator;

public abstract class Player implements EventHandler {
	
	public Player(EventDispatcher aEventDispatcher,String aName) {
		cards = new TreeSet<Card>(new Card.CardComparator());
		completedSets = new TreeSet<Card.Set>(new Card.SetComparator());
		teammates = new ArrayList<Player>();
		eventDispatcher = aEventDispatcher;
		name = aName;
		registerForEvents();
		
	}
	
	@Override
	public String toString() {
		return name;
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
		eventDispatcher.registerEventHandler(EventType.ASKCARD_QUERY,this);
		eventDispatcher.registerEventHandler(EventType.ASKCARD_OUTCOME,this);
	}
	
	@Override
	public void handleEvent(Event aEvent) {
		
		
	}
	public void addCard(Card aCard) {
		cards.add(aCard);
	}
	
	public void removeCard(Card aCard) {
		cards.remove(aCard);
	}
	
	public boolean hasCard(Card aCard) {
		return cards.contains(aCard);
	}
	
	public void setName(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public TreeSet<Card> getCards() {
		return cards;
	}
	
	public void addTeammate(Player aPlayer) {
		teammates.add(aPlayer);
	}
	
	public boolean isTeammate(Player aPlayer) {
		for(int i = 0;i < teammates.size();i++) {
			if(teammates.get(i) == aPlayer)
				return true;
		}
		return false;
	}
	
	public abstract void play();
	
	public boolean hasSetBase(Card.Set aSet) {
		Iterator<Card> iterator = cards.iterator();
		while(iterator.hasNext()) {
			if( aSet.equals( iterator.next().getSet() ) ) {  
				return true;
			}
		}
		return false;
	}
	
	public boolean validCardToAsk(Card aCard) {
		return !hasCard(aCard) && hasSetBase(aCard.getSet());
	}
	
	public void removeSet(Card.Set aSet) {
		
	}
	
	public void addCompletedSet(Card.Set aSet) {
		completedSets.add(aSet);
	}
	
	public boolean declareSet(Card.Set aSet) {
		TreeSet<Card> aCards = new TreeSet<Card>(new Card.CardComparator());
		Iterator<Card> iterator = cards.iterator();
		while(iterator.hasNext()) {
			Card currentCard = iterator.next();
			if(currentCard.getSet().equals(aSet)) {
				aCards.add(currentCard);
			}
		}
		for(int i = 0;i < teammates.size();i++) {
			iterator = teammates.get(i).getCards().iterator();
			while(iterator.hasNext()) {
				Card currentCard = iterator.next();
				if(currentCard.getSet().equals(aSet)) {
					aCards.add(currentCard);
				}
			}
		}
		if(aCards.size() != 6) {
			removeSet(aSet);
			return false;
		}
		return true;
	}
	
	protected boolean giveCard(Player aPlayer,Card aCard) {
		if(!hasCard(aCard))
			return false;
		removeCard(aCard);
		aPlayer.addCard(aCard);
		return true;
	}
	
	protected void askCard(Player aPlayer,Card aCard) {
		
		AskCardQuery askCardQuery = new AskCardQuery(this,aPlayer,aCard);
		
		AskCardQueryEvent askCardQueryEvent = new AskCardQueryEvent(askCardQuery);
		
		getEventDispatcher().postEvent(askCardQueryEvent);
		AskCardOutcome askCardOutcome;
		
		if(aPlayer.giveCard(this,aCard)) {
			askCardOutcome = new AskCardOutcome(AskCardOutcomeType.GaveCard,askCardQuery);
		}
		else {
			askCardOutcome = new AskCardOutcome(AskCardOutcomeType.CardNotPresent,askCardQuery);
		}
		AskCardOutcomeEvent askCardOutcomeEvent = new AskCardOutcomeEvent(askCardOutcome);
		getEventDispatcher().postEvent(askCardOutcomeEvent);
		
		if(askCardOutcome.getOutcomeType() == AskCardOutcomeType.CardNotPresent) {
			TurnChangeEvent turnChangeEvent = new TurnChangeEvent(aPlayer);
			getEventDispatcher().postEvent(turnChangeEvent);
		}
		aPlayer.giveCard(this, aCard);
	}
		
	
	private EventDispatcher eventDispatcher;
	private String name;
	private TreeSet<Card> cards;
	private TreeSet<Card.Set> completedSets;
	private ArrayList<Player> teammates;
}
