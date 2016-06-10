package litnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class GameUser extends Thread {
	
	static final int SERVER_PORT = 8143;
	static final String SERVER_ADDRESS = "192.168.1.119";
	
	private InputReader cin;
	private String username;
	private Socket server;
	
	public GameUser() {
		cin = new InputReader(System.in);
	}
	public void run() {
		System.out.println("Enter username: ");
		username = cin.next();
		try {
			server = new Socket(SERVER_ADDRESS,SERVER_PORT);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeObject(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		GameUser gameUser = new GameUser();
		gameUser.start();
	}
	class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
		
		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream),32768);
			tokenizer = null;
		}
		
		public String next() {
			while(tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				}
				catch(IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		public int nextInt() {
			return Integer.parseInt(next());
		}
		public double nextDouble() {
			return Double.parseDouble(next());
		}
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}
