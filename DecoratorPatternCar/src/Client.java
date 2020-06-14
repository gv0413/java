/*
������ - ���� ���ݰ� ������ ����

*/

public class Client {
	public static void main(String[] args) {
		CarComponent car = new BasicCar(1000);
		
		car = new AirBagDecorator(car, 400);
		car = new NaviDecorator(car, 100);
		car = new ESCDecorator(car, 50);
		car = new SCCDecorator(car, 200);
		
		System.out.println(car.getCarInfo());
		System.out.println(car.getPrice());
	}
}

//���ݰ� ���� �������� �߻�Ŭ����
abstract class CarComponent {
	public abstract int getPrice();
	public abstract String getCarInfo();
}

class BasicCar extends CarComponent {
	private int price;
	
	public BasicCar(int price) {
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return "Car";
	}
	
}

//�����ڿ��� � ���� �޾ƾ� �ϴ°�? -> ������ƮŸ���� �ٸ� �ɼǵ�
class CarOptionDecorator extends CarComponent {
	private CarComponent decoratedCar;
	
	public CarOptionDecorator(CarComponent decoratedCar) {
		this.decoratedCar = decoratedCar;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return this.decoratedCar.getPrice();
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return this.decoratedCar.getCarInfo();
	}
}

class AirBagDecorator extends CarOptionDecorator {
	private int price;
	
	public AirBagDecorator (CarComponent decoratedCar, int price) {
		super(decoratedCar);
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice() + this.price;
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return super.getCarInfo() + " with Air Bag";
	}
}

class NaviDecorator extends CarOptionDecorator {
	private int price;
	
	public NaviDecorator (CarComponent decoratedCar, int price) {
		super(decoratedCar);
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice() + this.price;
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return super.getCarInfo() + " with Navigation";
	}
}

class ESCDecorator extends CarOptionDecorator {
	private int price;
	
	public ESCDecorator (CarComponent decoratedCar, int price) {
		super(decoratedCar);
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice() + this.price;
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return super.getCarInfo() + " with ESC";
	}
}

class SCCDecorator extends CarOptionDecorator {
	private int price;
	
	public SCCDecorator (CarComponent decoratedCar, int price) {
		super(decoratedCar);
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice() + this.price;
	}

	@Override
	public String getCarInfo() {
		// TODO Auto-generated method stub
		return super.getCarInfo() + " with ESC";
	}
}

