import java.util.ArrayList;
import java.util.Iterator;
//Client2.java를 싱글턴패턴으로 만드는 실습

public class Client4 {
	public final static int Printer_NUM = 5;
	
	public final static boolean TEST_CODE = false;
	
	public static void main(String[] args) {
		Item item1 = new Item("축구공", 30000);
		Item item2 = new Item("치킨", 20000);
		
		Sale[] sale = new Sale[Printer_NUM];
		for(int i=0; i<Printer_NUM; i++) {
			sale[i] = new Sale();
			sale[i].setPrinter(HD108ReceipPrinter.getPrinter());
			sale[i].add(item1);
			sale[i].add(item2);
			sale[i].printReceip();
		}
	}
}
class Item{
	private String name;
	private long price;
	
	public Item(String name, long price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return this.name;
	}
	public long getPrice() {
		return this.price;
	}
}

interface ReceiptPrinter{
	public void print(StringBuffer buf);
}

class HD108ReceipPrinter implements ReceiptPrinter{
	private static ReceiptPrinter printer = null;
	private HD108ReceipPrinter() {
		//생성자
	}
	//싱글턴 패턴
	public synchronized static ReceiptPrinter getPrinter() {
		if(printer == null) {
			printer = new HD108ReceipPrinter();
		}
		return printer;
	}
	
	public void print(StringBuffer buf) {

		System.out.println("HD108ReceipPrinter"+ this.toString());
	}
}

class FakePrinter implements ReceiptPrinter{
	public void print(StringBuffer buf) {
		System.out.println(buf.toString());
	}
}


class Sale{
	private ArrayList<Item> items = new ArrayList<Item>();
	private ReceiptPrinter printer;
	
	public void setPrinter(ReceiptPrinter rp) {
		this.printer = rp; // 집약관계
	}
	
	public void printReceip() {
		Iterator<Item> itr = items.iterator();
		StringBuffer buf = new StringBuffer();
		while(itr.hasNext()) {
			Item item = itr.next();
			buf.append(item.getName());
			buf.append(item.getPrice());
		}
		printer.print(buf);
	}
	
	public void add(Item item) {
		items.add(item);
	}
}