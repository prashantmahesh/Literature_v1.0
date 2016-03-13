package litcore;
import java.util.concurrent.ThreadLocalRandom;


public class Deck {
	
	//Deck of cards
	private Card[] fCards;
	// Constructor 
	public Deck(){
		fCards = new Card[52];
		int count = 0;
		for(int i = 0;i < 4;i++) {
	        for(int j = 2;j <= Card.ACE;j++) {
	            if(j == 8) continue;
	            Card nCard = new Card(j,i);
	            fCards[count++] = nCard;       
	        }
	    }
	}
	//shuffling the deck
	public void shuffle(){
		for(int i = 0;i < 47;i++) {
	        int ts = ThreadLocalRandom.current().nextInt(i+1, 48);
	        Card temp = new Card(8,0);
	        temp = fCards[i];
	        fCards[i] = fCards [ts];
	        fCards[ts] = temp;
	    }
	}
	// returns i th card of the deck
	public Card get(int i){
		return fCards[i];
	}
	
}

