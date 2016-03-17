package litcore;

import java.util.Comparator;

public class Card {
	public enum Suit {
		HEARTS(0),
		DICE(1),
		SPADES(2),
		CLUBS(3);
		
		private Suit(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		public static Suit retenum(int i){
			switch(i){
				case 0:return HEARTS;
				case 1:return DICE;
				case 2:return SPADES;
				case 3:return CLUBS;
			}
			return HEARTS;
		}
		private final int value;
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
	
	public Card(int aRank,Suit aSuit) {
		suit = aSuit;
		rank = aRank;
		set = new Set();
		set.suit = aSuit;
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
				break;
			}
			case DICE: {
				name+="Dice";
				break;
			}
			case SPADES: {
				name+="Spades";
				break;
			}
			case CLUBS: {
				name+="Clubs";
				break;
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
	
	public static class CardComparator implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			if(o1.suit == o2.suit) 
				return Integer.compare(o1.rank, o2.rank);
			else 
				return Integer.compare(o1.suit.getValue(), o2.suit.getValue());
		}
		
	}
	
	public static class SetComparator implements Comparator<Set> {

		@Override
		public int compare(Set o1, Set o2) {
			if(o1.suit == o2.suit)
				return Integer.compare(o1.type,o2.type);
			else
				return Integer.compare(o1.suit.getValue(),o2.suit.getValue());
		}
		
	}
	
	private Suit suit;
	private int rank;
	Set set;
}