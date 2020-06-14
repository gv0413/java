import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Client3 {
	public static void main(String[] args) {
		new Zelda();
	}
}

class ImageService{
	public static final String[] standingImageName= {"f.png"};
	public static final String[] walkingLeftImageName= {"l_1.png", "l_2.png", "l_3.png", "l_4.png", "l_5.png", "l_6.png", "l_7.png", "l_8.png", "l_9.png", "l_10.png"};
	public static final String[] walkingRightImageName= {"r_1.png", "r_2.png", "r_3.png", "r_4.png", "r_5.png", "r_6.png", "r_7.png", "r_8.png", "r_9.png", "r_10.png"};

}
class PositionService{
	public static int position = 1;
	public static int standingPosition= 0;
	public static int walkingLeftPosition= -position;
	public static int walkingRightPosition= position;
}

abstract class MovingStrategy{
	abstract public String[] getImages();
	abstract public int getPositions();
}

class StandingStrategy extends MovingStrategy{
	public String[] getImages() {
		return ImageService.standingImageName;
	}
	public int getPositions() {
		return PositionService.standingPosition;
	}
}

class WalkingLeftStrategy extends MovingStrategy{
	public String[] getImages() {
		return ImageService.walkingLeftImageName;
	}
	public int getPositions() {
		return PositionService.walkingLeftPosition;
	}
}

class WalkingRightStrategy extends MovingStrategy{
	public String[] getImages() {
		return ImageService.walkingRightImageName;
	}
	public int getPositions() {
		return PositionService.walkingRightPosition;
	}
}

class Zelda extends JFrame{
	private static final long serialVersionUID = 1L;
	private Field field1;
	private Field field2;
	private Controller controller;
	private MovingStrategy strategy = new StandingStrategy();
	
	public Zelda() {
		super("Zelda");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		field1 = new Field();
		field2 = new Field();
		controller = new Controller();
		
		add(field1, BorderLayout.CENTER);
		add(field2, BorderLayout.NORTH);
		add(controller, BorderLayout.SOUTH);
		
		setSize(600, 800);
		setVisible(true);
		
		Thread th1 = new Thread(field1);
		Thread th2 = new Thread(field2);
		th1.start();
		th2.start();
	}
	
	private class Field extends JPanel implements Runnable{
		private static final long serialVersionUID = 1L;
		private JLabel lbl;
		
		public Field() {
			setLayout(new BorderLayout());
			lbl = new JLabel();
			lbl.setIcon(new ImageIcon("./src/img/"+strategy.getImages()[0]));
			add(lbl, BorderLayout.CENTER);
		}
		public void run() {
			int imageIndex = 0;
			while (true) {
				String[] path = strategy.getImages();
				int position = strategy.getPositions();
				
				if(path.length <= imageIndex) {
					imageIndex = 0;
				}
				lbl.setIcon(new ImageIcon("./src/img/"+path[imageIndex]));
				int currentPosition = lbl.getX();
				lbl.setLocation(currentPosition+position,0);
				imageIndex++;
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	private class Controller extends JPanel implements ActionListener{
		private JButton btnStanding, btnWalkingLeft, btnWalkingRight;
		private JSlider speedSlider;
		
		public Controller() {
			setLayout(new FlowLayout());
			btnStanding = new JButton("Standing");
			btnWalkingLeft = new JButton("WalkingLeft");
			btnWalkingRight = new JButton("WalkingRight");
			speedSlider = new JSlider(1,20,1);
			
			btnStanding.addActionListener(this);
			btnWalkingLeft.addActionListener(this);
			btnWalkingRight.addActionListener(this);
			speedSlider.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                 Settings setting = new Settings();
	                 setting.setPosition(speedSlider.getValue());
	            }
	        });
    
			add(btnStanding);
			add(btnWalkingLeft);
			add(btnWalkingRight);
			add(speedSlider);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton) e.getSource();
			if(btn == btnStanding) {
				strategy = new StandingStrategy();
			}else if(btn == btnWalkingLeft) {
				strategy = new WalkingLeftStrategy();
			}else if (btn ==btnWalkingRight) {
				strategy = new WalkingRightStrategy();
			}
		}
		
	}
}

class Settings {
	public void setPosition(int value) {
		PositionService.position = value;
		PositionService.walkingLeftPosition = -PositionService.position;
		PositionService.walkingRightPosition = PositionService.position;
	}
}