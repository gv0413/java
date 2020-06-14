
public class SequenceDiagram {
	public static void main(String[] args) {
//		seq #1
		A1 a1 = new A1();
		a1.doA1();
	}
}

class A1{
	public void doA1() {
		A2 a2 = new A2(); //seq #2
		a2.doA2(this); //seq #3
	}
	public void doIt(A3 a3) {
		a3.doIt(); //seq #5
	}
}
class A2{
	public void doA2(A1 a1) {
		A3 a3 = new A3(); //seq #4
		a3.doIt(); // seq #5
	}
}
class A3{
	public void doIt() {
		
	}
}
