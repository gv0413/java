
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
//	멤버변수로 tire만 가지고 있게끔
	private Tire tire;
	
	//tire종류를 세팅할 수 있는 setTire함수 추가
	public void setTire(Tire tire) {
		this.tire = tire;
	}
	
	//tire의 type을 출력할 수 있는 코드 추가
	public void getTireType() {
		System.out.println(this.tire.getType());
	}
}

//Tire interface : 나중에 어떤 타이어가 들어왔는지 확인할 수 있게끔 getType
interface Tire{
	public String getType();
}

//interface를 실제 구현하는 class 구현
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