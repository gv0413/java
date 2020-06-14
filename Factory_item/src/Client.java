import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {
	public static void main(String[] args) {
		new ItemEx();
	}
}

class ImageService {
	public static final String[] itemImageName = {"rb.png", "success.png", "fail.png"};
}

class ItemEx extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	ItemEx ie;
	Item item;
	
	public ItemEx() {
		super("ItemEx");
		this.ie =this;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setContentPane(new DrawItem());
		
		controller = new Controller(); //Ŭ������ �� ��÷�� ���� �������ִ°� 
		add(controller, BorderLayout.NORTH);
		
		setSize(400, 400);
		setVisible(true);
		
		item = new MainItem();
	}
	class DrawItem extends JPanel {
		private static final long serialVersionUID= 1L;
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ItemManager im = new ItemManager(item);
			im.drawItem(ie, g);
		}
	}

	private class Controller extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private JButton btnRnd;
		
		public Controller() {
			setLayout(new FlowLayout());
			
			btnRnd = new JButton("������ �̱�");
			btnRnd.addActionListener(this);
			add(btnRnd);
		}
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if(btn == btnRnd) {
				item = ItemFactory.getItem(ProbabilityType.P10);
				ie.revalidate();
				ie.repaint();
			}
		}
	}
}


//Ȯ���� ���� ������ ����. ����Ȯ���� ����Ȯ�� ����
enum ProbabilityType{P10, P50, P90}
class ItemFactory {
	public static Item getItem(ProbabilityType type) {
		Item item = null;
		
		Random rd = new Random();
		switch(type) {
		case P10:
			if(rd.nextInt(10) <= 0) { //0~9���� 10���� ������
				item = new SuccessItem();
			}else {
				item = new FailItem();
			}
			break;
		case P50:
			if(rd.nextInt(10) <= 4) {
				item = new SuccessItem();
			}else {
				item = new FailItem();
			}
			break;
		case P90:
			if(rd.nextInt(10) <= 8) {
				item = new SuccessItem();
			}else {
				item = new FailItem();
			}
			break;
		default:
			break;
		}
		
		return item;
	}
}

//�׷������ϱ� ���� �Ŵ��� Ŭ����
class ItemManager {
	private Item item;
	public ItemManager(Item item) {
		this.item = item;
	}
	public void drawItem(ItemEx ie, Graphics g) {
		g.drawImage(this.item.getItemImage(), 0, 0, 400, 400, ie);
	}
}
//Item �����
interface Item {
	public Image getItemImage();
}

class MainItem implements Item {

	@Override
	public Image getItemImage() {
		ImageIcon image = new ImageIcon("./src/img/" + ImageService.itemImageName[0]); 
		//main ó�� �̹���
		Image img = image.getImage();
		return img;
	}
	
}

class SuccessItem implements Item {

	@Override
	public Image getItemImage() {
		// TODO Auto-generated method stub
		ImageIcon image = new ImageIcon("./src/img/" + ImageService.itemImageName[1]); //success img
		Image img = image.getImage();
		return img;
	}
	
}

class FailItem implements Item {

	@Override
	public Image getItemImage() {
		// TODO Auto-generated method stub
		ImageIcon image = new ImageIcon("./src/img/" + ImageService.itemImageName[2]); //fail img
		Image img = image.getImage();
		return img;
	}
	
}












