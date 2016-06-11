package litnetwork.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import litnetwork.GameMessage;
import litnetwork.GameMessageType;
import litnetwork.NetworkConsole;

public class GameUser extends Thread {
	
	static final int SERVER_PORT = 8243;
	static final String SERVER_ADDRESS = "192.168.1.119";
	
	public GameUser() {
		console = new NetworkConsole();
	}
	
	private void sendMessage(GameMessage message) throws IOException {
		message.setSender(username);
		out.writeObject(message);
	}
	
	public void run() {
		console.log("Enter Username");
		username = console.getString();
		
		//Server Connection
		try {
			server = new Socket(SERVER_ADDRESS,SERVER_PORT);
			out = new ObjectOutputStream(server.getOutputStream());
			console.log("Connected to Game Server..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Sending username
		try {
			out.writeObject(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Starting Listener Thread
		try {
			listenerThread = new GameUserListenerThread(new ObjectInputStream(server.getInputStream()),this);
			listenerThread.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		console.log("Enter 'quit' to exit..");
		String message;
		try {
			do {
				message = console.getString();
				sendMessage(new GameMessage(GameMessageType.BASIC,message));
			} while(!message.equals("quit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void handleMessage(GameMessage message) {
		console.log(message);
	}
	
	private NetworkConsole console;
	private String username;
	private Socket server;
	private ObjectOutputStream out;
	private GameUserListenerThread listenerThread;
	
	public static void main(String[] args) {
		GameUser gameUser = new GameUser();
		gameUser.start();
	}
}
