package litcore;

import Card.Set;
import Card.Suit;

public class Card {
	public enum Suit {
		HEARTS,
		DICE,
		SPADES,
		CLUBS;
		public static Suit retenum(int i){
			switch(i){
				case 0:return HEARTS;
				case 1:return DICE;
				case 2:return SPADES;
				case 3:return CLUBS;
			}
		}
	}
	
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;
	
	public static final int MINOR = 1;
	public static final int MAJOR = 1;
			
	public class Set {
		public int type;
		public Suit suit;
		
		@Override
		public boolean equals(Object obj) {
			Set s = (Set)obj;
			return type == s.type && suit == s.suit;
		}
	}
	
	public Card(int aRank,int aSuit) {
		suit = Suit.retenum(aSuit);
		rank = aRank;
		set = new Set();
		set.suit = Suit.retenum(aSuit);
		if(aRank > 8)
			set.type = MAJOR;
		else
			set.type = MINOR;
	}
	
	@Override
	public String toString() {
		String name = "Null";
		if(rank <= 10) {
			name = Integer.toString(rank);
		}
		else {
			switch(rank) {
				case JACK: {
					name = "Jack";
					break;
				}
				case QUEEN: {
					name = "Queen";
					break;
				}
				case KING: {
					name = "King";
					break;
				}
				case ACE: {
					name = "Ace";
					break;
				}
			}
		}
		name+=" ";
		switch (suit) {
			case HEARTS: {
				name+="Hearts";
			}
			case DICE: {
				name+="Dice";
			}
			case SPADES: {
				name+="Spades";
			}
			case CLUBS: {
				name+="Clubs";
			}
		}
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Card card = (Card)obj;
		return suit == card.suit && rank == card.rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Set getSet() {
		return set;
	}
	private Suit suit;
	private int rank;
	Set set;
}