package iPhoneHomeButtonCommandPattern;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

class IPhoneHomeButton {
	private JLabel mLabel;
	
	public IPhoneHomeButton(JLabel lbl) {
		this.mLabel = lbl;
	}
	public void doClick() {
		this.mLabel.setIcon(new ImageIcon("./src/img/"+ImageService.iPhoneImageName[1]));
		System.out.println("홈 버튼이 클릭 되었습니다.");
	}
	public void doDoubleClick() {
		this.mLabel.setIcon(new ImageIcon("./src/img/"+ImageService.iPhoneImageName[3]));
		System.out.println("홈 버튼이 더블 클릭 되었습니다.");
	}
	public void doLongClick() {
		this.mLabel.setIcon(new ImageIcon("./src/img/"+ImageService.iPhoneImageName[2]));
		System.out.println("홈 버튼이 롱 클릭 되었습니다.");
	}
}

//------------------------------------------
interface Command {
	public abstract void execute();
}

class HomeClickCommand implements Command{
	private IPhoneHomeButton mBtn;
	public HomeClickCommand(IPhoneHomeButton btn) {
		this.mBtn = btn;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mBtn.doClick();
	}
}

class HomeDoubleClickCommand implements Command{
	private IPhoneHomeButton mBtn;
	public HomeDoubleClickCommand(IPhoneHomeButton btn) {
		this.mBtn = btn;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mBtn.doDoubleClick();
	}
}

class HomeLongClickCommand implements Command{
	private IPhoneHomeButton mBtn;
	public HomeLongClickCommand(IPhoneHomeButton btn) {
		this.mBtn = btn;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.mBtn.doLongClick();
	}
}
//------------------------------------------

class HomeButton{
	private Command command;
	
	public HomeButton(Command command) {
		this.command= command;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	} //다른 커멘드 필요할 때
	
	public void pressed() {
		command.execute();
	}
}

class ImageService {
	public static final String[] iPhoneImageName = {"0.png", "1.png", "2.png", "3.png"};
}

class Field extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	JLabel lbl;
	
	public Field() {
		super("iPhone 6s");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		lbl = new JLabel();
		
		JPanel jp = new JPanel();
		jp.add(lbl, BorderLayout.CENTER);
		add(jp, BorderLayout.CENTER);
		controller = new Controller();
		add(controller, BorderLayout.SOUTH);
		setSize(400, 860);
		setVisible(true);
	}
	
	class Controller extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private JButton btnHomeClick, btnDoubleClick, btnHomeLongClick;
		private HomeButton button;
		private IPhoneHomeButton iphoneHomeButton;
		
		public Controller() {
			setLayout(new FlowLayout());
			btnHomeClick = new JButton("홈 버튼 클릭");
			btnHomeClick.addActionListener(this);
			add(btnHomeClick);
			btnDoubleClick = new JButton("홈 버튼 더블 클릭");
			btnDoubleClick.addActionListener(this);
			add(btnDoubleClick);
			btnHomeLongClick = new JButton("홈 버튼 롱 클릭");
			btnHomeLongClick.addActionListener(this);
			add(btnHomeLongClick);
			
			lbl.setIcon(new ImageIcon("./src/img/"+ ImageService.iPhoneImageName[0]));
			iphoneHomeButton = new IPhoneHomeButton(lbl);
			button = new HomeButton(new HomeClickCommand(iphoneHomeButton));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton) e.getSource();
			if(btn == btnHomeClick) {
				button.setCommand(new HomeClickCommand(iphoneHomeButton));
				button.pressed();
			}
			else if (btn == btnDoubleClick) {
				button.setCommand(new HomeDoubleClickCommand(iphoneHomeButton));
				button.pressed();			
			}
			else if(btn == btnHomeLongClick) {
				button.setCommand(new HomeLongClickCommand(iphoneHomeButton));
				button.pressed();
			}
		}
	}
}