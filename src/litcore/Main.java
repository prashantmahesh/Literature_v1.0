package litcore;

import java.util.TreeSet;
import java.util.Iterator;

import litcore.Card.Suit;

public class Main {
	public static void main(String args[]){
		EventDispatcher aEventDispatcher = new EventDispatcher();
		Player aPlayer = new ConsoleUserPlayer(aEventDispatcher,"Player_A");
		aPlayer.addCard(new Card(3,Suit.SPADES) );
		aPlayer.addCard(new Card(5,Suit.HEARTS) );
		aPlayer.addCard(new Card(10,Suit.CLUBS) );
		aPlayer.addCard(new Card(Card.JACK,Suit.DICE) );
		System.out.println("HELLO WORLD");
		TreeSet<Card> cards = aPlayer.getCards();
		Iterator<Card> iterator = cards.iterator();
		System.out.println(aPlayer + " has the following cards.");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		Player aPlayer2 = new ConsoleUserPlayer(aEventDispatcher,"Player_B");
		Card toGive = new Card(3,Suit.SPADES);
		aPlayer.giveCard(aPlayer2, toGive );
		System.out.println(aPlayer + " gives card " + toGive + " to " + aPlayer2);
		iterator = cards.iterator();
		System.out.println(aPlayer + " has the following cards.");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		iterator = aPlayer2.getCards().iterator();
		System.out.println(aPlayer2 + " has the following cards.");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
}
