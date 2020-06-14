package song;

public class Main {
	public static void main(String[] args) {
		Song s1 = new Song();
		Song s2 = new Song();
		Song s3 = new OnSale();
		Song s4 = new TodayEvent();
		
		CartForSongs c = new CartForSongs();
		c.add(s1);
		c.add(s2); 
		c.add(s3);
		c.add(s4);
		
		System.out.println(c.calculateTotalPrice());
	}
}
