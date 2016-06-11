package litnetwork.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import litnetwork.GameMessage;
import litnetwork.NetworkConsole;

public class GameServer extends Thread {
	
	
	
	public GameServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		onlineUsers = new HashMap<String,ObjectOutputStream>();
		console = new NetworkConsole();
	}
	
	public void run() {
		System.out.println("Literature Game Server Started..");
		while(true) {
			try {
				Socket newClient = serverSocket.accept();
				login(newClient);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}	
	}
	
	public void handleMessage(GameMessage message) throws IOException {
		console.log(message);
		for(Map.Entry<String,ObjectOutputStream> entry : onlineUsers.entrySet()) {
			if(entry.getKey() != message.getSender()) {
				sendMessage(message,entry.getKey());
			}
		}
	}
	
	public boolean sendMessage(GameMessage message,String toUsername) throws IOException {
		if(onlineUsers.containsKey(toUsername)) {
			onlineUsers.get(toUsername).writeObject(message);
			return true;
		}
		else
			return false;
	}
	
	public void login(Socket client) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(client.getInputStream());
		String username = (String)in.readObject();
		if(onlineUsers.containsKey(username)) {
			//TODO Handle Error
			return;
		}
		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
		onlineUsers.put(username,out);
		new GameServerListenerThread(username,in,this).start();
		console.log(username + " has entered..");
	}
	
	public void logout(String username) {
		assert onlineUsers.containsKey(username);
		onlineUsers.remove(username);
		console.log(username + " has exited..");
	}
	
	private ServerSocket serverSocket;
	private HashMap<String, ObjectOutputStream> onlineUsers;
	private NetworkConsole console;
	
	public static void main(String[] args) {
		int port = 8243;
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
