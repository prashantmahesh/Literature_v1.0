package litcore.AskCard;

import litcore.Card;
import litcore.Player;

public class AskCardQuery{
	private Player asker ,respondent;
	private Card card;
	public AskCardQuery(Player aAsker,Player aRespondent,Card aCard){
		asker = aAsker;
		respondent = aRespondent;
		card = aCard;
	}
	public Player getAsker(){
		return asker;
	}
	public Player getRespondent(){
		return respondent;
	}
	public Card getCard(){
		return card;
	}
}
