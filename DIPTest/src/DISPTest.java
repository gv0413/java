
public class DISPTest {
	public static void main(String[] args) {
		Car car = new Car();
		car.setTire(new SnowTire());
		car.getTireType();
		
		car.setTire(new WideTire());
		car.getTireType();
	}
}

class Car {
//	��������� tire�� ������ �ְԲ�
	private Tire tire;
	
	//tire������ ������ �� �ִ� setTire�Լ� �߰�
	public void setTire(Tire tire) {
		this.tire = tire;
	}
	
	//tire�� type�� ����� �� �ִ� �ڵ� �߰�
	public void getTireType() {
		System.out.println(this.tire.getType());
	}
}

//Tire interface : ���߿� � Ÿ�̾ ���Դ��� Ȯ���� �� �ְԲ� getType
interface Tire{
	public String getType();
}

//interface�� ���� �����ϴ� class ����
class SnowTire implements Tire {
	public String getType() {
		return "SnowTire";
	}
}

class NormalTire implements Tire {
	public String getType() {
		return "NormalTire";
	}
}

class WideTire implements Tire {
	public String getType() {
		return "WideTire";
	}
}