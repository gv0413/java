package song;

public class Song {
	public double discountPercentage = 0;
	public double getPrice() {
		return 10.0;
	}
	public double calPrice(){
		return this.getPrice()-this.discountPercentage*this.getPrice();
	}
}