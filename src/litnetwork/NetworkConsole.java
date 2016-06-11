package litnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NetworkConsole {
	
	public NetworkConsole() {
		in = new InputReader(System.in);
	}
	
	public void log(String s) {
		System.out.println(s);
	}
	
	public void log(GameMessage aMessage) {
		System.out.println(aMessage);
	}
	
	public String getString() {
		return in.next();
	}
	
	public int getInt() {
		return in.nextInt();
	}
	private InputReader in;
	
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
