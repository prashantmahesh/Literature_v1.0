package litnetwork;

public class GameMessage {
	
	public GameMessage(GameMessageType aGameMessageType) {
		gameMessageType = aGameMessageType;
	}
	
	public GameMessageType getMessageType() {
		return gameMessageType;
	}
	
	private GameMessageType gameMessageType;
}
