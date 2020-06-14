
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
	//������踦 ���� ������� �ϳ� �����
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;
	
	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void attack() {
//		attackStrategy�� ��ü�� ó�� �κ��� ������ �� �־���� ���¿� ���� �����ϵ���
		attackStrategy.attack();
	}
	public void move() {
		movingStrategy.move();
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy = movingStrategy;
//		�ܺ��� ������Ʈ��Ƽ�� ��ü�� �Լ��� ���� �Ű������� ���� �޾Ƽ� ������ ��������� �־��ִ� �������
//		�ܺ��� movingStrategy��ü�� �κ�Ŭ������ ��ü�� ����������Ŭ�� �ٸ�
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}
}

class TaekwonV extends Robot{
	public TaekwonV(String name) {
		super(name); //����Ŭ�������� �������ִ� �����ڸ� ���� �̸��� �־���
	}

}

class Atom extends Robot{
	public Atom(String name) {
		super(name); //����Ŭ�������� �������ִ� �����ڸ� ���� �̸��� �־���
	}
}

//������ 1 �ذ�
class Sungard extends Robot{
	public Sungard(String name) {
		super(name); //����Ŭ�������� �������ִ� �����ڸ� ���� �̸��� �־���
	}
}


interface MovingStrategy{
	public void move();
	
}

class FlyingStrategy implements MovingStrategy {

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("����");
	}
}

class WalkingStrategy implements MovingStrategy {

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("�ȱ�");
	}
}

interface AttackStrategy{
	public void attack();
}

class MissileStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("�̻��� �߻�");
	}
	
}

class PunchStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("�ָ� �߻�");
	}
	
}