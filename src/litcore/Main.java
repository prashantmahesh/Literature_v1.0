package litcore;

public class Main {
	public static void main(String args[]){
		System.out.println("hello");
		Deck d = new Deck();
		for(int i = 0;i<48;i++){
			System.out.println(d.get(i));
		}
	}
}
