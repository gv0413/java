package song;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CartForSongs {
	ArrayList<Song> cart = new ArrayList<Song>();
	public double calculateTotalPrice() {
		double total = 0.0;
		Iterator<Song> itr = cart.iterator();
		
		while(itr.hasNext()) {
			Song s = itr.next();
			total = total + s.calPrice();
		}
		return total;
	}
	
	public void add(Song s) {
		cart.add(s);
	}
}
