package litcore;

import litcore.util.EventDispatcher;

public class ConsoleUserPlayer extends Player {
	
	
	public ConsoleUserPlayer(EventDispatcher aEventDispatcher, String aName,LitConsole aLitConsole) {
		super(aEventDispatcher, aName);
		litConsole = aLitConsole;
	}

	@Override
	public void play() {
		litConsole.printCards(this);
		Player aPlayer = litConsole.getPlayer("Enter player to ask: ");
		while(isTeammate(aPlayer)) {
			litConsole.log("Cannot ask teammate.");
			litConsole.getPlayer(LitConsole.INVALID_PLAYER_MESSAGE);
		}
		Card aCard = litConsole.getCard("Enter card to ask: ");
		while(!validCardToAsk(aCard)) {
			litConsole.log("Cannot ask this card.");
			litConsole.getCard(LitConsole.INVALID_CARD_MESSAGE);
		}
		askCard(aPlayer,aCard);
	}
	
	LitConsole litConsole;
}
