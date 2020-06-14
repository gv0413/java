import java.awt.*;
import javax.swing.*;


public class SequenceDiagramTest_7 {

}

class GUI extends Frame {
	private JLabel w = new JLabel();
	private JTextField tf = new JTextField();
	private JButton b = new JButton();
	
	public GUI() {
		this.init();
	}
	private void init() {
		GridLayout a2 = new GridLayout();
		setLayout(a2);
		BorderLayout a3 = new BorderLayout(); //a3?
		Panel p1 = new Panel(a3);
		p1.add("west", w);
		p1.add("center", tf);
		
		FlowLayout a4 = new FlowLayout();
		Panel p2 = new Panel(a4);
		p2.add(b);
		
		add(p2);
	}
}