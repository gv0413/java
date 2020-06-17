import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


interface State {
	public String on_button_pushed(Light light);
	public String off_button_pushed(Light light);
}

class ON implements State {
	private static ON on;
	private ON() {}
	
	public static ON getInstance() {
		if(on == null) {
			on = new ON();
		}
		return on;
	}
	
	public String on_button_pushed(Light light) {
		light.setState(SLEEPING.getInstance());
		System.out.println("Sleeping!");
		return ImageService.lightImageName[2];
	}
	
	public String off_button_pushed(Light light) {
		light.setState(OFF.getInstance());
		System.out.println("Light Off!");
		return ImageService.lightImageName[1];
	}
}

class OFF implements State{
	private static OFF off;
	private OFF() {}
	
	public static OFF getInstance() {
		if(off == null) {
			off = new OFF();
		}
		return off;
	}
	
	public String on_button_pushed(Light light) {
		light.setState(ON.getInstance());
		System.out.println("Light On!");
		return ImageService.lightImageName[0];
	}
	
	public String off_button_pushed(Light light) {
		System.out.println("반응 없음");
		return ImageService.lightImageName[1];
	}
}

class SLEEPING implements State {
	private static SLEEPING sleeping;
	private SLEEPING() {}
	
	public static SLEEPING getInstance() {
		if(sleeping == null) {
			sleeping = new SLEEPING();
		}
		return sleeping;
	}
	
	public String on_button_pushed(Light light) {
		light.setState(ON.getInstance());
		System.out.println("Light On!");
		return ImageService.lightImageName[0];
	}
	
	public String off_button_pushed(Light light) {
		light.setState(OFF.getInstance());
		System.out.println("Light Off!");
		return ImageService.lightImageName[1];
	}
}

class Light {
	private State state;
	
	public Light() {
		state = OFF.getInstance(); //시작점. 생성자
	}
	
	public String on_button_pushed() {
		return state.on_button_pushed(this);
	}
	
	public String off_button_pushed() {
		return state.off_button_pushed(this);
	}
	
	public void setState(State state) {
		this.state = state;
	}
}

class ImageService{
	public static final String[] lightImageName = { "on.png", "off.png", "sleeping.png"};
}

class Field extends JFrame{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	JLabel lbl;
	
	Light light;
	
	public Field() {
		super("Field");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		light = new Light();
		
		lbl = new JLabel();
		lbl.setIcon(new ImageIcon("img/" + ImageService.lightImageName[1])); //초기에 off
		
		JPanel jp = new JPanel();
		jp.add(lbl, BorderLayout.CENTER);
		add(jp, BorderLayout.CENTER);
		controller = new Controller();
		add(controller, BorderLayout.SOUTH);
		setSize(400, 300);
		setVisible(true);
	}
	
	class Controller extends JPanel implements ActionListener{
		private static final long serialVersionUID = 1L;
		private JButton btnOn, btnOff;
		public Controller() {
			setLayout(new FlowLayout());
			btnOn = new JButton("On");
			btnOff = new JButton("Off");
			btnOn.addActionListener(this);
			btnOff.addActionListener(this);
			add(btnOn);
			add(btnOff);
		}
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if(btn == btnOn) {
				lbl.setIcon(new ImageIcon("img/" + light.on_button_pushed()));
			}
			else if(btn == btnOff) {
				lbl.setIcon(new ImageIcon("img/" + light.off_button_pushed()));
			}
		}
	}
}

public class Client{
	public static void main(String[] args) {
		new Field();
	}
}
