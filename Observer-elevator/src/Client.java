/*
 현재 층에 대한 내용이 여러곳에서 출력됨(기다리는 곳, 엘레베이터 내부, 통제실 등등)
*/
import java.util.List;
import java.util.ArrayList;

class Client {
  public static void main(final String[] args) {
    ElevatorController ec = new ElevatorController();
    ec.attach(new ElevatorDisplay(ec));
    ec.attach(new VoiceNotice(ec));
    ec.attach(new FloorDisplay(ec));
    ec.attach(new ControlRoomDisplay(ec));

    ec.gotoFloor(2);
    ec.gotoFloor(5);
  }
}

abstract class Subject {
  private List<Observer> observers = new ArrayList<Observer>();

  public void attach(Observer observer) {
    observers.add(observer);
  }
  public void detach(Observer observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for( Observer o : observers) { //observers리스트에서 Observer type의 o를 돌면서
      o.update(); //옵저버가 가지고있는 업데이트함수를 돌면서 업데이트 할 수 있도록
    }
 }
}

class ElevatorController extends Subject { //층에 대한 정보를 알고있어야 함
  private int curFloor = 1;

  //특정 층으로 이동하게끔
  public void gotoFloor(int destination) {
    // 갔으니까 curFloor가 바뀜
    curFloor = destination;
    notifyObservers();
  }

  public int getCurFloor() {
    return this.curFloor;
  }
}

interface Observer {
  abstract public void update();
}

class ElevatorDisplay implements Observer {
  private ElevatorController controller; //연관관계

  public ElevatorDisplay(ElevatorController controller){
    this.controller = controller;
  }

  public void update() {
    int curFloor = controller.getCurFloor(); 
    //getCurFloor는 ElevatorController의 매서드
    System.out.println("엘리베이터 내부 디스플레이 : " + curFloor);
  }
}

class VoiceNotice implements Observer {
  private ElevatorController controller ;

  public VoiceNotice(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor는 ElevatorController의 매서드
    System.out.println("목소리 안내 : " + curFloor);
  }
}

class FloorDisplay implements Observer {
  private ElevatorController controller ;

  public FloorDisplay(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor는 ElevatorController의 매서드
    System.out.println("층별 디스플레이 : " + curFloor);
  }
}

class ControlRoomDisplay implements Observer {
  private ElevatorController controller ;

  public ControlRoomDisplay(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor는 ElevatorController의 매서드
    System.out.println("중앙통제실 디스플레이 : " + curFloor);
  }
}