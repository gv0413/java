import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Client {
	public static void main(String[] args) {
		CollisionController cc = new CollisionController(); //바꿔줌
		cc.attach(new Zelda(cc));
	}
}
////////////////////////////////////////
////////////////////////////////////////
interface Observer {
	abstract public void update();
}

class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	public void attach(Observer observer) {
		observers.add(observer);
	}
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	public void notifyObservers() {
		for(Observer o : observers) o.update();
	}
}

class CollisionController extends Subject {
	private int collisionObjectType = 0;
	
	public void onCollisionDetection() {
		notifyObservers();
	}
	
	public void setCollisionObjectType(int type) {
		this.collisionObjectType = type;
	}
	
	public int getCollisionObjectType() {
		return collisionObjectType;
	}
}
////////////////////////////////////////////

class ImageService{
	public static final String[] standingImageName= {"f.png"};
	public static final String[] walkingLeftImageName= {"l_1.png", "l_2.png", "l_3.png", "l_4.png", "l_5.png", "l_6.png", "l_7.png", "l_8.png", "l_9.png", "l_10.png"};
	public static final String[] walkingRightImageName= {"r_1.png", "r_2.png", "r_3.png", "r_4.png", "r_5.png", "r_6.png", "r_7.png", "r_8.png", "r_9.png", "r_10.png"};
}

interface State {
	public void standing_button_pushed(Zelda zelda);
	public void left_button_pushed(Zelda zelda);
	public void right_button_pushed(Zelda zelda);
	public int playAnim(JLabel lbl, int imageIndex);
}

class LEFT implements State{
	private static LEFT left;
	private static final int MOVING_DIRECTION = -1;
	private LEFT(){
		
	}
	public static LEFT getInstance() {
		if(left == null) {
			left = new LEFT();
		}
		
		return left;
	}
	
	@Override
	public void standing_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(STANDING.getInstance());
	}
	@Override
	public void left_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(LEFTFAST.getInstance());
	}
	@Override
	public void right_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(RIGHT.getInstance());
	}
	@Override
	public int playAnim(JLabel lbl, int imageIndex) {
		// TODO Auto-generated method stub
		if(ImageService.walkingLeftImageName.length <= imageIndex) {
			imageIndex = 0;
		}
		
		lbl.setIcon(new ImageIcon("./src/img/" + ImageService.walkingLeftImageName[imageIndex]));
		lbl.setLocation(lbl.getLocation().x + MOVING_DIRECTION, lbl.getLocation().y);
		
		return ++imageIndex;
	}
}

class LEFTFAST implements State {
	private static LEFTFAST leftfast;
	private static final int MOVING_DIRECTION = -10;
	private LEFTFAST(){
		
	}
	public static LEFTFAST getInstance() {
		if(leftfast == null) {
			leftfast = new LEFTFAST();
		}
		
		return leftfast;
	}
	
	@Override
	public void standing_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(STANDING.getInstance());
	}
	@Override
	public void left_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(LEFT.getInstance());
	}
	@Override
	public void right_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(RIGHT.getInstance());
	}
	@Override
	public int playAnim(JLabel lbl, int imageIndex) {
		// TODO Auto-generated method stub
		if(ImageService.walkingLeftImageName.length <= imageIndex) {
			imageIndex = 0;
		}
		
		lbl.setIcon(new ImageIcon("./src/img/" + ImageService.walkingLeftImageName[imageIndex]));
		lbl.setLocation(lbl.getLocation().x + MOVING_DIRECTION, lbl.getLocation().y);
		
		return ++imageIndex;
	}
}

class RIGHT implements State{
	private static RIGHT right;
	private static final int MOVING_DIRECTION = 1;
	private RIGHT(){
		
	}
	public static RIGHT getInstance() {
		if(right == null) {
			right = new RIGHT();
		}
		
		return right;
	}
	
	@Override
	public void standing_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(STANDING.getInstance());
	}
	@Override
	public void left_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(LEFT.getInstance());
	}
	@Override
	public void right_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(RIGHTFAST.getInstance());
	}
	@Override
	public int playAnim(JLabel lbl, int imageIndex) {
		// TODO Auto-generated method stub
		if(ImageService.walkingRightImageName.length <= imageIndex) {
			imageIndex = 0;
		}
		
		lbl.setIcon(new ImageIcon("./src/img/" + ImageService.walkingRightImageName[imageIndex]));
		lbl.setLocation(lbl.getLocation().x + MOVING_DIRECTION, lbl.getLocation().y);
		
		return ++imageIndex;
	}
}

class RIGHTFAST implements State{
	private static RIGHTFAST rightfast;
	private static final int MOVING_DIRECTION = 10;
	private RIGHTFAST(){
		
	}
	public static RIGHTFAST getInstance() {
		if(rightfast == null) {
			rightfast = new RIGHTFAST();
		}
		
		return rightfast;
	}
	
	@Override
	public void standing_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(STANDING.getInstance());
	}
	@Override
	public void left_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(LEFT.getInstance());
	}
	@Override
	public void right_button_pushed(Zelda zelda) {
		// TODO Auto-generated method stub
		zelda.setState(RIGHT.getInstance());
	}
	@Override
	public int playAnim(JLabel lbl, int imageIndex) {
		// TODO Auto-generated method stub
		if(ImageService.walkingRightImageName.length <= imageIndex) {
			imageIndex = 0;
		}
		
		lbl.setIcon(new ImageIcon("./src/img/" + ImageService.walkingRightImageName[imageIndex]));
		lbl.setLocation(lbl.getLocation().x + MOVING_DIRECTION, lbl.getLocation().y);
		
		return ++imageIndex;
	}
}

class STANDING implements State{
	private static STANDING standing;
	private static final int MOVING_DIRECTION = 0; //상수
	
	private STANDING(){
		
	}
	public static STANDING getInstance() {
		if(standing == null) {
			standing = new STANDING();
		}
		
		return standing;
	}
	
	public void standing_button_pushed(Zelda zelda) {
		
	}
	public void left_button_pushed(Zelda zelda) {
		zelda.setState(LEFT.getInstance());
	}
	public void right_button_pushed(Zelda zelda) {
		zelda.setState(RIGHT.getInstance());
	}
	@Override
	public int playAnim(JLabel lbl, int imageIndex) {
		// TODO Auto-generated method stub
		if(ImageService.standingImageName.length <= imageIndex) {
			imageIndex = 0;
		}
		
		lbl.setIcon(new ImageIcon("./src/img/" + ImageService.standingImageName[imageIndex]));
		lbl.setLocation(lbl.getLocation().x + MOVING_DIRECTION, lbl.getLocation().y);
		
		return ++imageIndex;
	}
}
//////////////////////////////////////////////
class Zelda extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private Field field;
	private Controller controller;
	private State state;
	
	private Zelda zelda;
	
	private CollisionController cc;
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//방향을 보고 방향에 따라 캐릭터가 움직이도록 
		if(cc.getCollisionObjectType() == 1) { //왼쪽으로 걷고있다가 부딪힌거군!
			this.setState(RIGHT.getInstance());
		} 
		else if(cc.getCollisionObjectType() == 2) { //오른쪽으로 가다가 부딪혔군!
			this.setState(LEFT.getInstance());
		}
	}
	
	public Zelda(CollisionController cc) {
		super("Zelda");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.cc = cc;
		
		zelda = this;
		state = STANDING.getInstance();
		
		field = new Field();
		controller = new Controller();
		
		add(field, BorderLayout.CENTER);
		add(controller, BorderLayout.SOUTH);
		
		setSize(400, 400);
		setVisible(true);
		
		Thread th = new Thread(field);
		th.start();
	}
	
	private class Field extends JPanel implements Runnable{
		private static final long serialVersionUID = 1L;
		private JLabel lbl;
		
		public Field() {
			setLayout(new BorderLayout());
			lbl = new JLabel();
//			lbl.setIcon(new ImageIcon("./src/img/"+ImageService.standingImageName[0]));
			lbl.setIcon(new ImageIcon("./src/img/f.png"));
			add(lbl, BorderLayout.CENTER);
		}
		
		public void run() {
			int imageIndex = 0;
			while (true) {
				imageIndex = state.playAnim(lbl, imageIndex);
				
				if(lbl.getLocation().x < -100) {
					cc.setCollisionObjectType(1);
					cc.onCollisionDetection();
				} 
				else if(lbl.getLocation().x > 100) {
					cc.setCollisionObjectType(2); //오른쪽에서 만났어
					cc.onCollisionDetection();
				}
				
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
			
			btnStanding.addActionListener(this);
			btnWalkingLeft.addActionListener(this);
			btnWalkingRight.addActionListener(this);
			
			add(btnStanding);
			add(btnWalkingLeft);
			add(btnWalkingRight);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton) e.getSource();
			if(btn == btnStanding) {
				state.standing_button_pushed(zelda); 
			//controller 클래스가 zelda의 이너클래스로 사용되고 있기 때문에 this를 사용할 수 없음. this를 쓰면 controller가 들어오니까
			}else if(btn == btnWalkingLeft) {
				state.left_button_pushed(zelda);
			}else if (btn ==btnWalkingRight) {
				state.right_button_pushed(zelda);
			}
		}
		
	}

}
