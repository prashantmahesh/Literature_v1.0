package litcore;

import litcore.util.EventDispatcher;

public class Main {
	public static void main(String args[]){
		EventDispatcher eventDispatcher = new EventDispatcher();
		GamePlayEngine gamePlayEngine = new GamePlayEngine(eventDispatcher, 4);
		LitConsole litConsole = new LitConsole(eventDispatcher, gamePlayEngine);
		Player[] players = gamePlayEngine.getPlayers();
		for(int i = 0;i < 4;i++) {
			players[i] = new ConsoleUserPlayer(eventDispatcher,"Player_" + (i + 1), litConsole);
		}
		Deck deck = new Deck();
		gamePlayEngine.distribute(deck);
		for(int i = 0;i < 4;i++) {
			litConsole.printCards(gamePlayEngine.getPlayers()[i]);
		}
		gamePlayEngine.startGame();
	}
}
