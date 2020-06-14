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
	private int price; //���ݿ����� ����
	
	public void setPrice(int price) {
		this.price = price;
	} //������������ �������ų� ���
	
	public int getPrice() {
		return this.price;
	} //������ ������ ��ȯ
}

//BagŬ������ ��ӹ޴� DiscountedBag Ŭ����
class DiscountedBag extends Bag {
	private double discountedRate = 0.5; //50% ������
	
	//LSP�� �������� ���� ���� setPrice�� ���������� ��
	public void setPrice(int price) {
		super.setPrice(price - (int)(discountedRate * price)); 
		//�θ��� setPrice�Լ��� ������ ����
	}
	
	//LSP�� ������ ���� setPrice�� ����ϴ� applyDiscount �Լ��� �������� ��
	public void applyDiscount(int price) {
		super.setPrice(price - (int)(discountedRate * price)); 
		//�θ��� setPrice�Լ��� ������ ����
	}
	
}
