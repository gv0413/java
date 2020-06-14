import java.util.Calendar;

public class playMP3{
  public static boolean IS_TEST = false;

  public static void main(String[] args) {
    Fish fish1 = new Fish("광어", 35000);
    Fish fish2 = new Fish("우럭", 20000);
    Fish fish3 = new Fish("숭어", 15000);

    TimeProvider tp;

    if(IS_TEST) {
			tp = new RealTimeProvider();
		}else {
			tp = new FakeTimeProvider(new Calendar(20));
    }
    

    FishMarket fm = new FishMarket(tp);

    System.out.prinln(""+fish1.name+"는"+fm.curPrice(fish1, 1)+"원");
  }
}

public class Fish {
  private String name;
  private int price;

  public Fish(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName(){
    return this.name;
  }

  public int getPrice(){
    return this.price;
  }
}

public class FishMarket {
  TimeProvider tp;
  PricePolicy pp;
  
  public FishMarket(TimeProvider tp){
    this.tp = tp;

    if(this.tp.getTime()>=0 && this.tp.getTime()<12){
      this.pp = new MorningPricePollicy();
    } 
    else if(this.tp.getTime() >= 12 && this.tp.getTime()<16){
      this.pp = new AfternoonPricePollicy();
    } 
    else{
      this.pp = new NightPricePollicy();
    } 
  }

  public int curPrice(Fish fish, int n){
    return this.pp.calcPrice(fish.getPrice(), n);
  }
  
  public void setTimeProvider(TimeProvider tp) {
		this.tp = tp;
  }
  
}

interface PricePolicy{
  //가격과 개수를 받아 정해진 룰에 따라 가격 계산  
  public int calcPrice(int price, int n);
}

class MorningPricePollicy implements PricePolicy {
  public int calcPrice(int price, int n){
    return price * n;
  }
}
class AfternoonPricePollicy implements PricePolicy {
  public int calcPrice(int price, int n){
    return (int)(price * n *0.8);
  }
}
class NightPricePollicy implements PricePolicy {
  public int calcPrice(int price, int n){
    return (int)(price * n * 0.5);
  }
}

interface TimeProvider{
  public int getTime();
}

class RealTimeProvider implements TimeProvider {
  Calendar cal;

  @Override
	public int getTime() {
		cal = Calendar.getInstance();
		
		return cal.get(Calendar.HOUR_OF_DAY);
	}
}
class FakeTimeProvider implements TimeProvider {
  //생성자에서 가짜 현재시간 받아서 calendar객체에 넣은 후 그 값을 반환받음
  // 0~24 
  Calendar cal;
  public FakeTimeProvider(Calendar cal){
    this.cal = cal;
  }
  @Override
	public int getTime() {
		return cal.get(Calendar.HOUR_OF_DAY); 
	}
}
