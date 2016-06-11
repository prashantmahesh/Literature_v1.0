package litnetwork.Server;

import java.io.IOException;
import java.io.ObjectInputStream;

import litnetwork.GameMessage;

public class GameServerListenerThread extends Thread {
	
	public GameServerListenerThread(String aUsername,ObjectInputStream clientInputStream,GameServer aGameServer) {
		gameServer = aGameServer;
		in = clientInputStream;
		username = aUsername;
	}
	
	public void run() {
		try {
			GameMessage s = (GameMessage) in.readObject();
			while(!s.getMessageString().equals("quit")) {
				gameServer.handleMessage(s);
				s = (GameMessage) in.readObject();
			}
			gameServer.logout(username);
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			gameServer.logout(username);
		}
	}
	
	
	private GameServer gameServer;
	private String username;
	private ObjectInputStream in;
}
