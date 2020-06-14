
public class Client {
	public static void main(String[] args) {
		Robot taekwonV = new TaekwonV("TaekwonV");
		Robot atom = new Atom("Atom");
		
		taekwonV.setMovingStrategy(new WalkingStrategy());
		taekwonV.setAttackStrategy(new MissileStrategy());
		
		atom.setMovingStrategy(new FlyingStrategy());
		atom.setAttackStrategy(new PunchStrategy());
		
		System.out.println(taekwonV.getName());
		taekwonV.move();
		taekwonV.attack();
		
		System.out.println();
		
		System.out.println(atom.getName());
		atom.move();
		atom.attack();
	}
}

abstract class Robot{
	private String name;
	//집약관계를 위해 멤버변수 하나 만들기
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;
	
	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void attack() {
//		attackStrategy형 객체로 처음 로봇이 생성될 때 넣어놨던 상태에 따라 동작하도록
		attackStrategy.attack();
	}
	public void move() {
		movingStrategy.move();
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy = movingStrategy;
//		외부의 무빙스트레티지 객체를 함수를 통해 매개변수를 통해 받아서 내부의 멤버변수에 넣어주는 집약관계
//		외부의 movingStrategy객체와 로봇클래스의 객체는 라이프사이클이 다름
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}
}

class TaekwonV extends Robot{
	public TaekwonV(String name) {
		super(name); //상위클래스에서 가지고있는 생성자를 통해 이름을 넣어줌
	}

}

class Atom extends Robot{
	public Atom(String name) {
		super(name); //상위클래스에서 가지고있는 생성자를 통해 이름을 넣어줌
	}
}

//문제점 1 해결
class Sungard extends Robot{
	public Sungard(String name) {
		super(name); //상위클래스에서 가지고있는 생성자를 통해 이름을 넣어줌
	}
}


interface MovingStrategy{
	public void move();
	
}

class FlyingStrategy implements MovingStrategy {

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("날기");
	}
}

class WalkingStrategy implements MovingStrategy {

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("걷기");
	}
}

interface AttackStrategy{
	public void attack();
}

class MissileStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("미사일 발사");
	}
	
}

class PunchStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("주먹 발사");
	}
	
}