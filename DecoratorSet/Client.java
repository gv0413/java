/*
차량 구성  - 차량 가격 / 설명 + 옵션 
추가옵션 : AirBag, Navi, ESC, SCC
*/

public class Client {
  Component component = new ConcreateComponent(aaa)
  component = new ConcreateDecoratorA(component)
}

//추상 클래스 컴포넌트 안에 추상메서드 나열
abstract class Component {
  public abstract int getPrice();
  public abstract String getCarInfo();
}

//컴포넌트 상속받는 ConcreateComponent. component의 추상메서드를 구체화해준다.
class ConcreateComponent extends Component {
  public ConcreateComponent() {
  }

  public class int getPrice {
    return this.price;
  }
  public class int getPrice {
    return "Car"
  }
}

//Component를 상속받는 Decorator. 여기서는 private으로 Component객체 멤버변수 갖고있는다. 
//생성자에서는 Component객체 받아서 멤버변수에 넣어준다
//추상메서드는 멤버변수.메서드()로 대충 매꾼다.
class Decorator extends Component {
  private Component decoratedAAA;
  public Decorator(Component decoratedAAA) {
    this.decoratedAAA = decoratedAAA;
  }
  public int getPrice() {
    // return this.decoratedCar.getPrice();
  }
  public String getCarInfo() {
    // return this.decoratedCar.getCarInfo();
  }
}

//ConcreateDecoratorA이다. 생성자에서는 컴포넌트형 객체 받아서 부모클래스에 넣어준다 + 필요한 것 받아서 세팅
//메서드에서는 부모의메서드와 추가옵션의 메서드를 합쳐서 넣어준다.
class ConcreateDecoratorA extends Decorator{
  public ConcreateDecoratorA(Component decoratedAAA) {
    super(decoratedAAA);
    // this.price = price;
  }
  public int getPrice() {
    // return super.getPrice() + this.price;
  }
  private void getCarInfo() {
    // return super.getCarInfo() + "With Air Bag"
  }
}