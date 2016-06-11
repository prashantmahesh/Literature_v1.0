package litnetwork;

import java.io.Serializable;

public class GameMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8411917016548610867L;
	public GameMessage(GameMessageType aGameMessageType) {
		gameMessageType = aGameMessageType;
		sender = "unknown";
	}
	
	public GameMessage(GameMessageType aGameMessageType,String aMessage) {
		gameMessageType = aGameMessageType;
		message = aMessage;
	}
	
	public GameMessage(GameMessageType aGameMessageType,String aMessage,String aSender) {
		gameMessageType = aGameMessageType;
		message = aMessage;
		sender = aSender;
	}
	public void setMessageString(String aMessage) {
		message = aMessage;
	}
	
	public void setSender(String aSender) {
		sender = aSender;
	}
	
	public String getMessageString() {
		return message;
	}
	
	public GameMessageType getMessageType() {
		return gameMessageType;
	}
	
	public String getSender() {
		return sender;
	}
	
	@Override
	public String toString() {
		return "From '" + sender + "' : " + message;
	}
	
	private GameMessageType gameMessageType;
	private String message;
	private String sender;
}
