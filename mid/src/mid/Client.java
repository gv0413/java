package mid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Client{
  public static boolean IS_TEST = true;
  
  public Client() {
	  
  }
  
  public static void main(String[] args) {
    Fish fish1 = new Fish("광어", 35000);
    Fish fish2 = new Fish("우럭", 20000);
    Fish fish3 = new Fish("숭어", 15000);
    ArrayList<Fish> fishes = new ArrayList<Fish>();
    fishes.add(fish1);
    fishes.add(fish2);
    fishes.add(fish3);

    TimeProvider tp;

    if(IS_TEST) {
			tp = new FakeTimeProvider(18);
		}else {
			tp = new RealTimeProvider();
    }
    

    FishMarket fm = new FishMarket(tp);

    Iterator<Fish> itr = fishes.iterator();
    while(itr.hasNext()) {
    	Fish fish = itr.next();
    	System.out.println(""+fish.getName()+"는 "+fm.curPrice(fish, 1)+"원");
    }
//    System.out.println(""+fish1.getName()+"는 "+fm.curPrice(fish1, 1)+"원");
//    System.out.println(""+fish2.getName()+"은 "+fm.curPrice(fish2, 1)+"원");
//    System.out.println(""+fish3.getName()+"는 "+fm.curPrice(fish3, 1)+"원");
  }
}

class Fish {
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

class FishMarket {
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
  public FakeTimeProvider(int hour){
	Calendar tmpCal = Calendar.getInstance();
	tmpCal.set(Calendar.HOUR_OF_DAY, hour);
    this.cal = tmpCal;
  }
  @Override
	public int getTime() {
		return cal.get(Calendar.HOUR_OF_DAY); 
	}
}
