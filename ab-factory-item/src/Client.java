import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {
  public static void main(String[] args) {
    new AFactoryFrame();
  }
}
class ImageService {
  public static final String[] charactorImageName =  {"base.png", "sam_cap.png", "sam_body.png",
    "sam_leg.png", "doo_cap.png", "doo_body.png", "doo_leg.png"};
    
}
class AFactoryFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  private Controller controller ;
  AFactoryFrame aff;

  CharactorFactory factory;

  public AFactoryFrame() {
    super("AFactoryFrame");
    this.aff = this;

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setContentPane(new DrawItem());

    controller = new Controller();
    add(controller, BorderLayout.NORTH);

    setSize(400, 400);
    setVisible(true);
  }
  class DrawItem extends JPanel {
	  private static final long serialVersionUID = 1L;

	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    //기본이미지
	    ImageIcon image = new ImageIcon("./src/img/" + ImageService.charactorImageName[0]);
	    Image img = image.getImage();
	    g.drawImage(img, 0,0,400,600,aff);

	    // factory클래스의 create ... 불러서 실제로 드로잉하도록
	    if(factory != null) {
	      factory.createCap().draw(g, aff);
	      factory.createBody().draw(g, aff);
	      factory.createLeg().draw(g, aff);
	    }
	  }
	}
  private class Controller extends JPanel implements ActionListener {
	  private static final long serialVersionUID = 1L;
	  private JButton btnSam;
	  private JButton btnDoo;

	  public Controller() {
	    setLayout(new FlowLayout());

	    btnSam = new JButton("삼성라이온즈");
	    btnSam.addActionListener(this);
	    add(btnSam);

	    btnDoo = new JButton("두산베어스");
	    btnDoo.addActionListener(this);
	    add(btnDoo);
	  }

	  public void actionPerformed(ActionEvent e){
	    JButton btn = (JButton) e.getSource();
	    if(btn == btnSam){
	      factory = SamsungLionsFactory.getInstance();
	      aff.revalidate();
	      aff.repaint();
	    }else if(btn == btnDoo) {
	      factory = DoosanBearsFactory.getInstance();
	      aff.revalidate();
	      aff.repaint();
	    }

	  }
	}
}


abstract class CharactorFactory {
  public abstract DrawImage createCap();
  public abstract DrawImage createBody();
  public abstract DrawImage createLeg();
}

class SamsungLionsFactory extends CharactorFactory {
  private static CharactorFactory factory;
  private SamsungLionsFactory() {

  }
  public static CharactorFactory getInstance() {
    if(factory ==null) factory = new SamsungLionsFactory();
    return factory;
  }
  public DrawImage createCap() {
    return new SamsungCap();
  }
  public DrawImage createBody() {
    return new SamsungBody();
  }
  public DrawImage createLeg() {
    return new SamsungLeg();
  }
}
class DoosanBearsFactory extends CharactorFactory {
  private static CharactorFactory factory;
  private DoosanBearsFactory() {

  }
  public static CharactorFactory getInstance() {
    if(factory ==null) factory = new DoosanBearsFactory();
    return factory;
  }
  public DrawImage createCap() {
    return new DoosanCap();
  }
  public DrawImage createBody() {
    return new DoosanBody();
  }
  public DrawImage createLeg() {
    return new DoosanLeg();
  }
}

// 공통적인 product들 - 이미지출력 기능
abstract class DrawImage {
  public void draw(Graphics g, JFrame jf){
    // template메서드 패턴 적용
    ImageIcon image = getImageIcon(); //해당객체 받아옴. 하위클래스에서 날아온 객체
    Image img = image.getImage();
    g.drawImage(img, 0,0,400,600,jf);

  }
  //hook function
  public abstract ImageIcon getImageIcon();
}

class SamsungCap extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[1]);
  }
}
class SamsungBody extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[2]);
  }
}
class SamsungLeg extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[3]);
  }
}

class DoosanCap extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[4]);
  }
}
class DoosanBody extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[5]);
  }
}
class DoosanLeg extends DrawImage {
  public ImageIcon getImageIcon() {
    return new ImageIcon("./src/img/" + ImageService.charactorImageName[6]);
  }
}