package litnetwork.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class GameServer extends Thread {
	
	private ServerSocket serverSocket;
	
	private HashMap<String, Socket> onlineUsers;
	
	public GameServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		onlineUsers = new HashMap<String,Socket>();
	}
	
	public void run() {
		System.out.println("Literature Game Server Started..");
		while(true) {
			try {
				Socket newClient = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(newClient.getInputStream());
				String username = (String)in.readObject();
				login(username,newClient);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void login(String username,Socket client) {
		if(onlineUsers.containsKey(username)) {
			//TODO Handle Error
			return;
		}
		onlineUsers.put(username,client);
		new GameServerListenerThread(username, client,this).start();
		System.out.println(username + " has entered.. ");
	}
	
	public void logout(String username) {
		assert onlineUsers.containsKey(username);
		onlineUsers.remove(username);
		System.out.println(username + " has exited.. ");
	}
	
	public static void main(String[] args) {
		int port = 8143;
		GameServer gameServer = null;
		try {
			gameServer = new GameServer(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		gameServer.start();
	}
	
	
}
