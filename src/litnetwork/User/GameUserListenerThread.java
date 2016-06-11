package litnetwork.User;

import java.io.IOException;
import java.io.ObjectInputStream;

import litnetwork.GameMessage;

public class GameUserListenerThread extends Thread {
	
	public GameUserListenerThread(ObjectInputStream serverInputStream,GameUser aGameUser) {
		gameUser = aGameUser;
		in = serverInputStream;
	}
	
	public void run() {
		try {
			GameMessage s = (GameMessage) in.readObject();
			while(!s.getMessageString().equals("quit")) {
				gameUser.handleMessage(s);
				s = (GameMessage) in.readObject();
			}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private GameUser gameUser;
	private ObjectInputStream in;
}
