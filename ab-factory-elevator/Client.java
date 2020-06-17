enum DoorStatus {OPENED, CLOSED}
abstract class ElevatorFactory {
  public abstract Motor createMotor();
  public abstract Door createDoor();
}

//어떤 제조사 팩토리를 쓸지 선택하게 
class ElevatorFactoryFactory {
  public static ElevatorFactory getFactory(VendorID vendorID) {
    ElevatorFactory factory = null;
    switch(vendorID) {
      case HYUNDAI :
        factory = HyundaiElevatorFactory.getInstance();
        break;
      case LG :
        factory = LGElevatorFactory.getInstance();
        break;
      case SAMSUNG : 
        factory = SamsungElevatorFactory.getInstance();
        break;
    }
    return factory;
  }
}
class LGElevatorFactory extends ElevatorFactory {
  private static ElevatorFactory factory;
  private LGElevatorFactory(){}
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

class HyundaiElevatorFactory extends ElevatorFactory {
  private static ElevatorFactory factory;
  private HyundaiElevatorFactory(){}
  public static ElevatorFactory getInstance() {
    if(factory == null) factory = new HyundaiElevatorFactory();
    return factory;
  }
  public Motor createMotor() {
    return new HyundaiMotor();
  }
  public Door createDoor() {
    return new HyundaiDoor();
  }
}

class SamsungElevatorFactory extends ElevatorFactory {
  private static ElevatorFactory factory;
  private SamsungElevatorFactory(){}
  public static ElevatorFactory getInstance() {
    if(factory == null) factory = new SamsungElevatorFactory();
    return factory;
  }
  public Motor createMotor() {
    return new SamsungMotor();
  }
  public Door createDoor() {
    return new SamsungDoor();
  }
}

abstract class Door {
  private DoorStatus doorStatus;
  public Door() {
    doorStatus = DoorStatus.CLOSED;
  }
  public DoorStatus getDoorStatus() {
    return doorStatus;
  }
  public void close() {
    if(doorStatus == DoorStatus.CLOSED) return;
    doClose();
    doorStatus = DoorStatus.CLOSED;
  }
  protected abstract void doClose();
  public void open() {
    if(doorStatus == DoorStatus.OPENED) return;
    doOpen();
    doorStatus = DoorStatus.OPENED;
  }
  protected abstract void doOpen();
}

class LGDoor extends Door {
  protected void doClose() {
    System.out.println("close LG Door");
  }
  protected void doOpen() {
    System.out.println("open LG Door");
  }
}

class HyundaiDoor extends Door {
  protected void doClose() {
    System.out.println("close Hyundai Door");
  }
  protected void doOpen() {
    System.out.println("open Hyundai Door");
  }
}

class SamsungDoor extends Door {
  protected void doClose() {
    System.out.println("close Samsung Door");
  }
  protected void doOpen() {
    System.out.println("open Samsung Door");
  }
}

// class DoorFactory {
//   public static Door createDoor(VendorID vendorID) {
//     Door door = null;
//     switch(vendorID){
//       case LG : door = new LGDoor(); break;
//       case Hyundai : door = new HyundaiDoor(); break;
//     }
//     return door;
//   }
// }

enum MotorStatus { MOVING, STOPPED}
abstract class Motor {
  private MotorStatus motorStatus;
  private Door door;

  public Motor() {
    motorStatus = MotorStatus.STOPPED;
  }
  public MotorStatus getMotorStatus() {
    return this.motorStatus;
  }
  public void setMotorStatus(MotorStatus motorStatus){
    this.motorStatus = motorStatus;
  }
  public void move(Direction direction) {
    MotorStatus motorStatus = getMotorStatus();
    if(motorStatus == motorStatus.MOVING){ return;}
    door.close();
    moveMotor(direction);
    setMotorStatus(motorStatus.MOVING);
  }
  protected abstract void moveMotor(Direction direction);
  public void stop(){
   motorStatus = MotorStatus.STOPPED;
  }
  public void setDoor(Door door){
    this.door = door;
  }
 }

class LGMotor extends Motor {
   protected void moveMotor(Direction direction) {
     System.out.println("move LG Motor " + direction);
   }
}

class HyundaiMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move Hyundai Motor " + direction);
  }
}

class SamsungMotor extends Motor {
  protected void moveMotor(Direction direction) {
    System.out.println("move Samsung Motor " + direction);
  }
}

enum Direction {UP, DOWN}
enum VendorID {LG, HYUNDAI, SAMSUNG}
// class MotorFactory {
//   public static Motor createMotor(VendorID vendorID){
//     Motor motor = null;
//     switch(vendorID) {
//       case LG : new LGMotor(); break;
//       case Hyundai : motor = new HyundaiMotor(); break;
//     }
//     return motor;
//   }
// }
public class Client {
  public static void main(String[] args){
    ElevatorFactory factory = ElevatorFactoryFactory.getFactory(VendorID.SAMSUNG);
    Door door = factory.createDoor();
    Motor motor = factory.createMotor();
    motor.setDoor(door);

    door.open();
    motor.move(Direction.UP);
  }
}