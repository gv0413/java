import java.util.ArrayList;
import java.util.Iterator;


public class Client2 {
	public final static boolean TEST_CODE = false;
	
	public static void main(String[] args) {
		Item item1 = new Item("축구공", 30000);
		Item item2 = new Item("치킨", 20000);
		
		Sale sale = new Sale();
		if(!TEST_CODE) {
			sale.setPrinter(new HD108ReceipPrinter());	
		} else {
			sale.setPrinter(new FakePrinter());
		}
		
		sale.add(item1);
		sale.add(item2);
		
		sale.printReceip();
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
	public void print(StringBuffer buf) {

		System.out.println("HD108ReceipPrinter");
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