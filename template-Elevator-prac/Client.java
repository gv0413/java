public class Client {
  public static void main(String[] args) {
    Door door = new Door();
    Motor m = new HDMotor(door);
    m.move(Direction.UP);
  }
}

enum MotorStatus{MOVING, STOPPED}
enum DoorStatus{CLOSED, OPENED}
enum Direction{UP, DOWN}

class Door {
  private DoorStatus doorStatus;
  public Door(){
    this.doorStatus = DoorStatus.CLOSED;
  }
  public void close() {
    this.doorStatus = DoorStatus.CLOSED;
  }
  public void open() {
    this.doorStatus = DoorStatus.OPENED;
  }
  public DoorStatus getDoorStatus() {
    return doorStatus;
  }
}
abstract class Motor {
  private Door door;
  private MotorStatus motorStatus;

  public Motor(Door door) {
    this.door = door;
    motorStatus = MotorStatus.STOPPED;
  }
  public MotorStatus getMotorStatus() {
    return motorStatus;
  }

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
  protected abstract void moveMotor(Direction direction); 
}

class HDMotor extends Motor{
  public HDMotor(Door door) {
    super(door);
  }
  protected void moveMotor(Direction direction) {
    System.out.println(direction+"으로 현대 모터이동");
  }
}

class LGMotor extends Motor{
  public LGMotor(Door door) {
    super(door);
  }
  protected void moveMotor(Direction direction) {
    System.out.println(direction + "으로 이동");
  }
}