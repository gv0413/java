/*
 ���� ���� ���� ������ ���������� ��µ�(��ٸ��� ��, ���������� ����, ������ ���)
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
    for( Observer o : observers) { //observers����Ʈ���� Observer type�� o�� ���鼭
      o.update(); //�������� �������ִ� ������Ʈ�Լ��� ���鼭 ������Ʈ �� �� �ֵ���
    }
 }
}

class ElevatorController extends Subject { //���� ���� ������ �˰��־�� ��
  private int curFloor = 1;

  //Ư�� ������ �̵��ϰԲ�
  public void gotoFloor(int destination) {
    // �����ϱ� curFloor�� �ٲ�
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
  private ElevatorController controller; //��������

  public ElevatorDisplay(ElevatorController controller){
    this.controller = controller;
  }

  public void update() {
    int curFloor = controller.getCurFloor(); 
    //getCurFloor�� ElevatorController�� �ż���
    System.out.println("���������� ���� ���÷��� : " + curFloor);
  }
}

class VoiceNotice implements Observer {
  private ElevatorController controller ;

  public VoiceNotice(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor�� ElevatorController�� �ż���
    System.out.println("��Ҹ� �ȳ� : " + curFloor);
  }
}

class FloorDisplay implements Observer {
  private ElevatorController controller ;

  public FloorDisplay(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor�� ElevatorController�� �ż���
    System.out.println("���� ���÷��� : " + curFloor);
  }
}

class ControlRoomDisplay implements Observer {
  private ElevatorController controller ;

  public ControlRoomDisplay(ElevatorController controller) {
    this.controller = controller;
  }
  public void update() {
    int curFloor = controller.getCurFloor(); //getCurFloor�� ElevatorController�� �ż���
    System.out.println("�߾������� ���÷��� : " + curFloor);
  }
}