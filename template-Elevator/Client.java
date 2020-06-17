public class Client {
  public static void main(String[] args) {
    Door door = new Door();
    Motor m = new HyundaiMotor(door);
    m.move(Direction.UP);
  }
}
enum DoorStatus{CLOSED, OPENED}
enum MotorStatus{MOVING, STOPPED}
enum Direction {UP, DOWN}

class Door {
  private DoorStatus doorStatus;
  public Door() {
    doorStatus = DoorStatus.CLOSED;
  }
  public DoorStatus getDoorStatus() {
    return doorStatus;
  }
  public void close(){
    doorStatus = DoorStatus.CLOSED;
  }
  public void open() {
    doorStatus = DoorStatus.OPENED;
  }
}
abstract class Motor {
  private Door door;
  private MotorStatus motorStatus;

  public Motor(Door door) {
    this.door = door;
    motorStatus = motorStatus.STOPPED;
  }
  public MotorStatus getMotorStatus() {return motorStatus;}
  private void setMotorStatus(MotorStatus motorStatus) {
    this.motorStatus = motorStatus;
  }

  public void move(Direction direction) {
    MotorStatus motorStatus = getMotorStatus();
    if(motorStatus == MotorStatus.MOVING) return;

    DoorStatus doorStatus = door.getDoorStatus();
    if(doorStatus == DoorStatus.OPENED) door.close();

    moveMotor(direction);
    setMotorStatus(motorStatus.MOVING);
  }
  protected abstract void moveMotor(Direction direction); //function hook
}

class HyundaiMotor extends Motor {
  public HyundaiMotor(Door door) {
    super(door);
  }
  protected void moveMotor(Direction direction) {
    //구동
    System.out.println("현대모터"+direction+" 으로 이동합니다");
  }
}

class LGMotor extends Motor {
  public LGMotor(Door door) {
    super(door);
  }
  protected void moveMotor(Direction direction) {
    //구동
    System.out.println("엘쥐모터" + direction+"으로 이동합니다");
  }
}