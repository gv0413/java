enum DoorStatus{CLOSED, OPENED}
enum MotorStatus{MOVING, STOPPED}

abstract class Door {
  private DoorStatus doorStatus;
  public Door() {
    doorStatus = DoorStatus.CLOSED;
  }
  public DoorStatus getDoorStatus() {
    return this.doorStatus;
  }

  public void open() {
    if(doorStatus == DoorStatus.OPENED) return;
    doOpen();
    doorStatus = DoorStatus.OPENED;
  }
  public void close() {
    if(doorStatus == DoorStatus.CLOSED) return;
    doClose();
    doorStatus = DoorStatus.CLOSED;
  }

  protected abstract void doOpen();
  protected abstract void doClose();
}

class LGDoor extends Door {
  protected void doOpen() {
    System.out.println("LG Door open");
  }
  protected void doClose() {
    System.out.println("LG Door close");
  }
}

class HDDoor extends Door {
  protected void doOpen() {
    System.out.println("HD Door open");
  }
  protected void doClose() {
    System.out.println("HD Door close");
  }
}

abstract class Motor {
  private Door door;
  private MotorStatus motorStatus;
  public Motor() {
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
    door.close();
    moveMotor(direction);
    setMotorStatus(motorStatus.MOVING);
  }

  protected abstract void moveMotor(Direction direction);

  public void setDoor(Door door) {
    this.door = door;
  }
}

class LGMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move LG Motor" + direction);
  }
}
class HDMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move HD Motor" + direction);
  }
}

enum Direction{UP,DOWN}
enum VendorID{LG, HD}

abstract class ElevatorFactory {
  public abstract Motor createMotor();
  public abstract Door createDoor();
}

class LGElevatorFactory extends ElevatorFactory {
  private static ElevatorFactory factory;
  private LGElevatorFactory() {}
  public static ElevatorFactory getInstance() {
    if(factory == null) factory = new LGElevatorFactory();
    return factory;
  }
  public Motor createMotor() {
    return new LGMotor();
  }
  public Door createDoor() {
    return new LGDoor(); 
  }
}
class HDElevatorFactory extends ElevatorFactory {
  private static ElevatorFactory factory;
  private HDElevatorFactory() {}
  public static ElevatorFactory getInstance() {
    if(factory == null) factory = new HDElevatorFactory();
    return factory;
  }
  public Motor createMotor() {
    return new HDMotor();
  }
  public Door createDoor() {
  return new HDDoor(); 
  }
}

class ElevatorFactoryFactory {
  public static ElevatorFactory getFactory(VendorID vendorID) {
    ElevatorFactory factory =null;
    switch (vendorID) {
      case LG:
        factory = LGElevatorFactory.getInstance();
        break;
      case HD:
        factory = HDElevatorFactory.getInstance();
        break;
      default:
        break;
    }
    return factory;
  }
}
public class Client {
  public static void main(String[] args){
    ElevatorFactory factory = ElevatorFactoryFactory.getFactory(VendorID.LG);
    Door door = factory.createDoor();
    Motor motor = factory.createMotor();
    motor.setDoor(door);

    door.open();
    motor.move(Direction.UP);
  }
}