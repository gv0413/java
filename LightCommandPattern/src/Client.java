import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.lang.reflect.Field;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Client {
	public static void main(String[] args) {
		new Field();
	}
}

class Lamp {
	private JLabel mLabel;
	
	public Lamp(JLabel lbl) {
		this.mLabel = lbl;
	}
	public void turnOn() {
		System.out.println("Lamp On");
		this.mLabel.setIcon(new ImageIcon("./src/img/"+ImageService.lightImageName[0]));
	}
	public void turnOff()  {
		System.out.println("Lamp Off");
		this.mLabel.setIcon(new ImageIcon("./src/img/" + ImageService.lightImageName[1]));
	}
}

class Alarm {
	private JLabel mLabel;
	
	public Alarm(JLabel lbl) {
		this.mLabel = lbl;
	}
	public void start() {
		System.out.println("Alarming...");
		this.mLabel.setIcon(new ImageIcon("./src/img/"+ImageService.lightImageName[1]));
	}
}

interface Command {
	abstract public void execute();
}

class LampOnCommand implements Command {
	private Lamp mLamp;
	public LampOnCommand(Lamp lamp) {
		this.mLamp = lamp;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mLamp.turnOn();
	}
}

class LampOffCommand implements Command {
	private Lamp mLamp;
	public LampOffCommand(Lamp lamp) {
		this.mLamp = lamp;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mLamp.turnOff();
	}
}

class AlarmOnCommand implements Command {
	private Alarm mAlarm;
	public AlarmOnCommand(Alarm alarm) {
		this.mAlarm = alarm;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mAlarm.start();
	}
}

class Button {
	private Command mCommand;
	public Button(Command command) {
		this.mCommand = command;
	}
	public void setNewCommand(Command newCommand) {
		this.mCommand = newCommand;
	}
	public void pressed() {
		this.mCommand.execute();
	}
}

class ImageService {
	public static final String[] lightImageName = {"on.png", "off.png", "alarm.jpg"};
}

class Field extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	JLabel lbl;
	
	public Field() {
		super("Field");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		lbl = new JLabel();
		
		JPanel jp = new JPanel();
		jp.add(lbl, BorderLayout.CENTER);
		add(jp, BorderLayout.CENTER);
		controller = new Controller();
		add(controller, BorderLayout.SOUTH);
		setSize(400, 300);
		setVisible(true);
	}

	enum MODE {LAMP, ALARM};
	class Controller extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private JButton btnAll;
		Button btnAlmighty;
		private MODE mMode = MODE.LAMP;
		
		public Controller() {
			setLayout(new FlowLayout());
			btnAll = new JButton("만능 버튼");
			btnAll.addActionListener(this);
			add(btnAll);
			
			LampOnCommand loc = new LampOnCommand(new Lamp(lbl));
			btnAlmighty = new Button(loc);
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton) e.getSource();
			if(btn == btnAll) {
				btnAlmighty.pressed();
				if(mMode == MODE.LAMP) {
					btnAlmighty.setNewCommand(new AlarmOnCommand(new Alarm(lbl)));
					mMode = MODE.ALARM;
				}
				else {
					btnAlmighty.setNewCommand(new LampOnCommand(new Lamp(lbl)));
					mMode = MODE.LAMP;
				}
			}
		}
	}
}