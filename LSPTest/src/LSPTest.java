public class LSPTest {
	public static void main(String[] args) {
		Bag b1 = new Bag();
		Bag b2 = new Bag();
		
		b1.setPrice(50000);
		System.out.println(b1.getPrice());
		
		b2.setPrice(b1.getPrice());
		System.out.println(b2.getPrice());
		
		DiscountedBag b3 = new DiscountedBag();
		DiscountedBag b4 = new DiscountedBag();
		
		b3.setPrice(50000);
		System.out.println(b3.getPrice());

		b4.setPrice(b3.getPrice());
		System.out.println(b4.getPrice());
		
		b3.applyDiscount(50000);
		System.out.println(b3.getPrice());
		
	}
}

class Bag {
	private int price; //가격에대한 정보
	
	public void setPrice(int price) {
		this.price = price;
	} //가격의정보를 가져오거나 출력
	
	public int getPrice() {
		return this.price;
	} //가격의 정보를 반환
}

//Bag클래스를 상속받는 DiscountedBag 클래스
class DiscountedBag extends Bag {
	private double discountedRate = 0.5; //50% 할인율
	
	//LSP를 만족하지 않을 때는 setPrice를 재정의했을 때
	public void setPrice(int price) {
		super.setPrice(price - (int)(discountedRate * price)); 
		//부모의 setPrice함수에 할인율 적용
	}
	
	//LSP를 만족할 때는 setPrice를 사용하는 applyDiscount 함수를 생성했을 때
	public void applyDiscount(int price) {
		super.setPrice(price - (int)(discountedRate * price)); 
		//부모의 setPrice함수에 할인율 적용
	}
	
}
