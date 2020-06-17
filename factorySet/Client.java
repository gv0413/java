enum Direction{UP, DOWN}
enum MotorStatus {MOVING, STOPPED}

abstract class Motor {
  private MotorStatus motorStatus;

  public Motor() {
    motorStatus = motorStatus.STOPPED;
  }
  
	public MotorStatus getMotorStatus() {
		return this.motorStatus;
	}
  
  public void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
  }
  
  public void move(Direction direction) {
    
  }
}
public class Client {
  
}