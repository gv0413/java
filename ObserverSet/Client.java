// ElevatorController - ConcreateSubject
// Subject - attach, detach, notifyObservers
// Observer 추가필요
// ---concreateObserver ---
// - ElevatorDisplay
// - VoiceNotice
// - FloorDisplay
// - ControlRoomDisplay

import java.util.List;
import java.util.ArrayList;

interface Observer {
  abstract public void update();
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
    for( Observer o : observers) o.update(); //옵저버가 가지고있는 업데이트함수를 돌면서 업데이트 할 수 있도록
  }
}

class ConcreateSubject extends Subject {
  //데이터 변경 이루어짐
}

class ConcreateObserver1 implements Observer {
  private ConcreateSubject cs;
  //private으로 ConcreateSubject 가지고있음
  //생성자의 매개변수로 ConcreateSubject 해서 멤버변수에 매개변수 넣어줌
  //Observer interface의 추상메서드 concreate구현
  public void update(){
    
  }
}
class ConcreateObserver2 implements Observer {
  private ConcreateSubject cs;
  //private으로 ConcreateSubject 가지고있음
  //생성자의 매개변수로 ConcreateSubject 해서 멤버변수에 매개변수 넣어줌
  //Observer interface의 추상메서드 concreate구현
  public void update(){
    
  }
}

