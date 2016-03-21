package litcore;

import litcore.Card.Suit;
import litcore.util.EventDispatcher;

import java.util.Iterator;
public class Main {
	
	public static void displayCards(Player a) {
		System.out.println(a);
		Iterator<Card> iterator = a.getCards().iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	public static void main(String args[]){
		EventDispatcher eventDispatcher = new EventDispatcher();
		Player a = new ConsoleUserPlayer(eventDispatcher, "Player_1");
		Player b = new ConsoleUserPlayer(eventDispatcher, "Player_2");
		a.addCard(new Card(3,Suit.SPADES) );
		a.addCard(new Card(3,Suit.CLUBS) );
		displayCards(a);
		
		b.askCard(a,new Card(3,Suit.SPADES) );
		displayCards(a); 
		displayCards(b);
	}
}
