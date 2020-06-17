enum Direction{UP, DOWN}
enum MotorStatus {MOVING, STOPPED}
abstract class Motor {
	private MotorStatus motorStatus;
	
	public Motor() {
		motorStatus = MotorStatus.STOPPED;
	}
	public MotorStatus getMotorStatus() {
		return this.motorStatus;
	}
	public void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}
	public void move(Direction direction) {
		MotorStatus motorStatus = getMotorStatus();
		if(motorStatus == MotorStatus.MOVING) {
			return;
		}
		moveMotor(direction);
		setMotorStatus(MotorStatus.MOVING);
	}
	
	//function hook
	protected abstract void moveMotor(Direction direction);
	
	public void stop() {
		motorStatus = MotorStatus.STOPPED;
	}
}

class LGMotor extends Motor {
	@Override
	protected void moveMotor(Direction direction) {
		// TODO Auto-generated method stub
		System.out.println("move LG Motor " + direction);
	}
}
class HyundaiMotor extends Motor {
	@Override
	protected void moveMotor(Direction direction) {
		// TODO Auto-generated method stub
		System.out.println("move LG Motor " + direction);
	}
}

class ElevatorController {
	private int id;
	private int curFloor = 1;
	private Motor motor;
	
	public ElevatorController(int id, Motor motor) {
		this.id = id;
		this.motor = motor;
	}
	
	public void gotoFloor(int destination) {
		if(destination == curFloor) return;
		Direction direction;
		
		if(destination > curFloor)
			direction = Direction.UP;
		else
			direction = Direction.DOWN;
		motor.move(direction);
		
		System.out.println("Elevator [" + id + "] Floor :" + curFloor);
		curFloor = destination;
		System.out.println("==>" + curFloor + " with " + motor.getClass().getName());
		
		motor.stop();
	}
}

public class Client {
	public static void main(String[] args) {
//		Motor lgMotor = new LGMotor();
//		ElevatorController controller1 = new ElevatorController(1, lgMotor);
//		controller1.gotoFloor(5);
//		controller1.gotoFloor(3);
//		
//		Motor hyundaiMotor = new HyundaiMotor();
//		ElevatorController controller2 = new ElevatorController(1, hyundaiMotor);
//		controller2.gotoFloor(4);
//		controller2.gotoFloor(6);
		ElevatorController controller = new ElevatorController(1, MotorFactory.getMotor(MotorVendorID.HYUNDAI));
		controller.gotoFloor(3);
		controller.gotoFloor(2);
	}
}

//factory
enum MotorVendorID{HYUNDAI, LG}
class MotorFactory {
	//스태틱으로 만들지 않으면 매번 객체로 만들어야하는 문제점이 있어서 스태틱으로 메소드를 만들어줘야 한다.
	public static Motor getMotor(MotorVendorID vendorID) {
		Motor motor = null;
		switch(vendorID) {
			case HYUNDAI:
				motor = new HyundaiMotor();
				break;
			case LG:
				motor = new LGMotor();
				break;
			default:
				break;
		}
		
		return motor;
	}
}

//아침엔 lg, 오후엔 현대꺼를 쓰는거를 해보자. 팩토리 메소드에!
