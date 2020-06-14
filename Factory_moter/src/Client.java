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
		if(motorStatus == motorStatus.MOVING) {
			return;
		}
		moveMotor(direction);
		setMotorStatus(motorStatus.MOVING);
	}
	
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
	//����ƽ���� ������ ������ �Ź� ��ü�� �������ϴ� �������� �־ ����ƽ���� �޼ҵ带 �������� �Ѵ�.
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

//��ħ�� lg, ���Ŀ� ���벨�� ���°Ÿ� �غ���. ���丮 �޼ҵ忡!