package litnetwork.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import litnetwork.GameMessage;

public class GameServerListenerThread extends Thread {
	
	public GameServerListenerThread(String aUsername,Socket aClient,GameServer aGameServer) {
		gameServer = aGameServer;
		client = aClient;
		username = aUsername;
	}
	
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			System.out.println("Connected to Client: " + client.getRemoteSocketAddress());
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			out.writeUTF("Server here!");
			GameMessage s = (GameMessage) in.readObject();
			while(!s.equals("quit")) {
				System.out.println(client.getRemoteSocketAddress() + " says: " + s);
				s = (GameMessage) in.readObject();
			}
			gameServer.logout(username);
		}
		catch (IOException | ClassNotFoundException e) {
			gameServer.logout(username);
		}
	}
	
	
	private GameServer gameServer;
	private Socket client;
	private String username;
}
