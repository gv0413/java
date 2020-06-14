package factory_elevator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


interface ElevatorScheduler {
  public int selectElevator(ElevatorManager manager, int destination, Direction direction);
}

class ThroughputScheduler implements ElevatorScheduler {
	private static ElevatorScheduler scheduler;
	private ThroughputScheduler() {}
	public static ElevatorScheduler getInstance( ) {
		if(scheduler == null) scheduler = new ThroughputScheduler();
		return scheduler;
	}
	public int selectElevator(ElevatorManager manager, int destination, Direction direction){
		return 0;
	}
}

class ResponseTimeScheduler implements ElevatorScheduler {
	private static ElevatorScheduler scheduler;
	private ResponseTimeScheduler() {}
	public static ElevatorScheduler getInstance( ) {
		if(scheduler == null) scheduler = new ResponseTimeScheduler();
		return scheduler;
	}
	public int selectElevator(ElevatorManager manager, int destination, Direction direction){
		return 1;
	}
}

enum Direction {UP, DOWN}
abstract class ElevatorManager {
  private List<ElevatorController> controllers; //������ ���������� �����ϵ���
//  private SchedulingStrategyID strategyID;
  public ElevatorManager(int controllerCount) {
    controllers = new ArrayList<ElevatorController>(controllerCount);
    for( int i=0; i<controllerCount; i++) {
      ElevatorController controller = new ElevatorController(i+1);
      controllers.add(controller);
    }
//    this.strategyID = strategyID;
  }
  protected abstract ElevatorScheduler getScheduler();
//  public void setStrategyID(SchedulingStrategyID strategyID) { this.strategyID = strategyID;}
  void requestElevator(int destination, Direction direction) {
    ElevatorScheduler scheduler = getScheduler();
//    System.out.println(scheduler);
    //���� �ڵ�
    int selectedElevator = scheduler.selectElevator(this, destination, direction);
    controllers.get(selectedElevator).gotoFloor(destination);
  }
}

class ElevatorManagerWithThroughputScheduling extends ElevatorManager {
	public ElevatorManagerWithThroughputScheduling(int controllerCount) {
		super(controllerCount);
	}
	protected ElevatorScheduler getScheduler() {
		ElevatorScheduler scheduler = ThroughputScheduler.getInstance();
		return scheduler;
	}
}

class ElevatorManagerWithResponseTimeScheduling extends ElevatorManager {
	public ElevatorManagerWithResponseTimeScheduling(int controllerCount) {
		super(controllerCount);
	}
	protected ElevatorScheduler getScheduler() {
		ElevatorScheduler scheduler = ResponseTimeScheduler.getInstance();
		return scheduler;
	}
}

class ElevatorManagerWithDynamicScheduling extends ElevatorManager {
	public ElevatorManagerWithDynamicScheduling(int controllerCount) {
		super(controllerCount);
	}
	protected ElevatorScheduler getScheduler() {
		ElevatorScheduler scheduler = null;
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour < 12)
			scheduler = ResponseTimeScheduler.getInstance();
		else
			scheduler = ThroughputScheduler.getInstance();
		return scheduler;
	}
}

class ElevatorController {
  private int id;
  private int curFloor;
  public ElevatorController(int id) {
    this.id = id;
    curFloor = 1;
  }
  public void gotoFloor(int destination) {
    System.out.print("Elevator [" + id + "] Floor : " + curFloor);
    curFloor = destination;
    System.out.println(" ==> " + curFloor);
  }
}

enum SchedulingStrategyID { RESPONSE_TIME, THROUGHPUT, DYNAMIC}
class SchedulerFactory {
  public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID) {
	  ElevatorScheduler scheduler = null;
	  switch(strategyID) {
	  case RESPONSE_TIME :
		  scheduler = ResponseTimeScheduler.getInstance();
		  break;
	  case THROUGHPUT :
		  scheduler = ThroughputScheduler.getInstance();
		  break;
	  case DYNAMIC : {
		  int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		  if(hour < 12)
			  scheduler = ResponseTimeScheduler.getInstance();
		  else
			  scheduler = ThroughputScheduler.getInstance();
		  break;
	  }
	  }
	  return scheduler;
  }
}

public class Client {
  public static void main(String[] args) {
	  ElevatorManager emWithResponseTimerScheduler = new ElevatorManagerWithResponseTimeScheduling(2);
	  emWithResponseTimerScheduler.requestElevator(10, Direction.UP);
	  
	  ElevatorManager emWithThroughputScheduler = new ElevatorManagerWithThroughputScheduling(2);
	  emWithThroughputScheduler.requestElevator(10, Direction.UP);
	  
	  ElevatorManager emWithDynamicScheduler = new ElevatorManagerWithDynamicScheduling(2);
	  emWithDynamicScheduler.requestElevator(10, Direction.UP);
  }
}