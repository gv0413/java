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
  protected abstract void moveMotor(Direction direction);

  public void stop() {
    motorStatus = MotorStatus.STOPPED;
  }
}

class LGMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move LG Moter" + direction);
  }
}
class HDMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move HD Moter" + direction);
  }
}
// class FLEX extends Motor {
//   protected void moveMotor(Direction direction) {
//     if(HOUR<12)
//       System.out.println("move LG Motor" + direction);
//     else
//       System.out.println("move HD Motor" + direction);
//   }
// }

class ElevatorController {
  private int id; //elevator id
  private int curFloor =1;
  private Motor motor;

  public ElevatorController(int id, Motor motor) {
    this.id = id;
    this.motor = motor;
  }

  public void gotoFloor(int destination) {
    if(destination == curFloor) return;
    Direction direction;

    if(destination > curFloor) {
      direction = Direction.UP;
    }
    else{
      direction = Direction.DOWN;
    }
    motor.move(direction);

    System.out.println("Elevator [" + id + "] Floor :" + curFloor);
		curFloor = destination;
		System.out.println("==>" + curFloor + " with " + motor.getClass().getName());
		
		motor.stop();
  }
}
public class Client {
  public static void main(String[] args) {
    ElevatorController controller = new ElevatorController(1, MotorFactory.getMotor(MotorVendorID.LG));
    controller.gotoFloor(2);
    controller.gotoFloor(5);
  }
}
enum MotorVendorID{HD, LG}
class MotorFactory {
  public static Motor getMotor(MotorVendorID vendorID) {
    Motor motor = null;
    switch(vendorID) {
      case HD:
        motor = new HDMotor();
        break;
      case LG:
        motor = new LGMotor();
        break;
      // case FLEX :
      //   motor = new FLEX();
      //   break;
    }
    return motor;
  }
}