import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {
	public static void main(String[] args) {
		new RockType();
	}
}

class ImageService {
	public static final String[] rockImageName = { "base_1.png", "decorator_1.png","decorator_2.png","decorator_3.png"};
}

//윈도우창 만드는 클래스
class RockType extends JFrame {
	private static final long serialVersionUID = 1L;
	
	RockType rt;
	
	public RockType() {
		super("RockType");
		
		this.rt = this;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setContentPane(new DrawRock());
		
		setSize(420, 450);
		setVisible(true);
	}
	
	class DrawRock extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Display rp = new RockDisplay();
			
			Random random = new Random();
			
			if(random.nextBoolean()) {
				rp = new Rock_1_Decorator(rp);
			}else if(random.nextBoolean()) {
				rp = new Rock_2_Decorator(rp);
			}if(random.nextBoolean()) {
				rp = new Rock_3_Decorator(rp);
			}
			
			rp.draw(rt, g);
		}
	}
}


abstract class Display {
	public abstract void draw(RockType rt, Graphics g);
}

//기본 돌을 그리는 클래스
class RockDisplay extends Display {
	public void draw(RockType rt, Graphics g) {
		ImageIcon image = new ImageIcon("./src/img/" + ImageService.rockImageName[0]);
		Image img = image.getImage(); //이미지형식으로 바꿔줌
		g.drawImage(img, 0, 0, 400, 400, rt); //좌표값,넓이,높이,rt
	}
}

class DisplayDecorator extends Display {
	private Display decoratedDisplay ;
	
	public DisplayDecorator(Display decoratedDisplay) {
		this.decoratedDisplay = decoratedDisplay;
	}
	
	public void draw(RockType rt, Graphics g) {
		decoratedDisplay.draw(rt, g);
	}
}

class Rock_1_Decorator extends DisplayDecorator {
	public Rock_1_Decorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	@Override
	public void draw(RockType rt, Graphics g) {
		super.draw(rt, g);

		ImageIcon image = new ImageIcon("./src/img/" + ImageService.rockImageName[1]);
		Image img = image.getImage(); //이미지형식으로 바꿔줌
		g.drawImage(img, 0, 0, 400, 400, rt); //좌표값,넓이,높이,rt
	}
	
}

class Rock_2_Decorator extends DisplayDecorator {
	public Rock_2_Decorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	@Override
	public void draw(RockType rt, Graphics g) {
		super.draw(rt, g);

		ImageIcon image = new ImageIcon("./src/img/" + ImageService.rockImageName[2]);
		Image img = image.getImage(); //이미지형식으로 바꿔줌
		g.drawImage(img, 0, 0, 400, 400, rt); //좌표값,넓이,높이,rt
	}
	
}

class Rock_3_Decorator extends DisplayDecorator {
	public Rock_3_Decorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	@Override
	public void draw(RockType rt, Graphics g) {
		super.draw(rt, g);

		ImageIcon image = new ImageIcon("./src/img/" + ImageService.rockImageName[3]);
		Image img = image.getImage(); //이미지형식으로 바꿔줌
		g.drawImage(img, 0, 0, 400, 400, rt); //좌표값,넓이,높이,rt
	}
	
}

