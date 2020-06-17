/*
전체에 해당하는 컴퓨터 클래스도 컴퓨터 디바이스를 상속받는다
왜냐면 getPrice와 getPower를 같이 갖고있기 때문에
*/
public class Client {
  public static void main(String[] args){
    Body body = new Body(100, 70);
    Keyboard keyboard = new Keyboard(5,2);
    Monitor monitor = new Monitor(20,30);
  
    Computer computer = new Computer();
    computer.addComponent(body);
    computer.addComponent(keyboard);
    computer.addComponent(monitor);
  
    int computerPrice = computer.getPrice();
    int computerPower = computer.getPower();
    System.out.println("Computer Power: " + computerPower + " W");
    System.out.println("Computer Price: " + computerPrice + " 만원");
  }
}

abstract class ComputerDevice {
  public abstract int getPrice();
  public abstract int getPower();
}

class Keyboard extends ComputerDevice {
  private int price;
  private int power;

  public KeyBoard(int power, int price) {
    this.power = power;
    this.price = price;
  }
}

class Body extends ComputerDevice {
  private int price;
  private int power;

  public Body(int power, int price) {
    this.power = power;
    this.price = price;
  }
}

class Moniter extends ComputerDevice {
  private int price;
  private int power;

  public Moniter(int power, int price) {
    this.power = power;
    this.price = price;
  }
}
/*
일대 다로 구현해야하니까 리스트타입으로
*/
class Computer extends ComputerDevice {
  private List <ComputerDevice> components = new ArrayList<ComputerDevice>();
  public void addComponent(ComputerDevice component){
    components.add(component);
  }
  public void removeComponent(ComputerDevice component) {
    components.remove(component);
  }
  public int getPrice() {
    int price = 0;
    for (ComputerDevice component : components)
      price += component.getPrice();
    return price;
  }
  public int getPower() {
    int price = 0;
    for (ComputerDevice component : components)
      price += component.getPower();
    return price;
  }
}