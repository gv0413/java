class TV {
	private boolean powerOn = false;
	private boolean muteOn = false;
	
	public void power() {
		powerOn = !powerOn;
		if(powerOn) {
			System.out.println("power on!!");
		}else {
			System.out.println("power off!!");
		}
	}
	
	public void mute() {
		if(!powerOn) return;
		
		muteOn = !muteOn;
		
		if(muteOn) {
			System.out.println("mute on!!");
		}else {
			System.out.println("mute off!!");
		}
	}
}

class Airconditioner {
	private boolean powerOn = false;
	private boolean dehumidificationOn = false; //�������
	
	public void power() {
		powerOn = !powerOn;
		if(powerOn) {
			System.out.println("Power on!!");
		}else {
			System.out.println("Power off!!");
		}
	}
	
	public void dehumidification() {
		if(!powerOn) { 
			return;
		}
		dehumidificationOn = !dehumidificationOn;
		if(dehumidificationOn) {
			System.out.println("dehumidification on!!");
		}else {
			System.out.println("dehumidification off!!");
		}
	}
}

class TwoButtonController {
	private Command command1;
	private Command command2; //tv��ü�� �ٷ� ��������ʰ� command��ü�� �������
	
	public TwoButtonController(Command command1, Command command2) {
		this.command1 = command1;
		this.command2 = command2;
	}
	
	public void button1Pressed() {
		this.command1.execute();
	}
	public void button2Pressed() {
		this.command2.execute();
	}
}

public class Client {
	public static void main(String[] args) {
//		TV tv = new TV(); 
		//tv��ü ������ְ�
//		TVOnCommand toc = new TVOnCommand(tv); 
		//tv��ü�� TVOncommand�� �־��ְ�
//		TVMuteCommand tmc = new TVMuteCommand(tv); 
		//TVMuteCommand�� �־��ش�
		
		Airconditioner tv = new Airconditioner();
		AirconditionerOnCommand toc = new AirconditionerOnCommand(tv); 
		AirconditionerDehumidificationOnCommand tmc = new AirconditionerDehumidificationOnCommand(tv);
		TwoButtonController tbc = new TwoButtonController(toc, tmc); //command1�� command2�� 
		
		tbc.button1Pressed();
		tbc.button2Pressed();
		
		tbc.button1Pressed();
		tbc.button2Pressed();
	}
}

interface Command{
	public abstract void execute();
}

class TVOnCommand implements Command {
	private TV tv;
	
	public TVOnCommand(TV tv) {
		this.tv = tv;
	}
	//	�ܺ��� tv��ü�� �ް� ���������� tv�� �־��ش�
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.tv.power();
	//���������� TwoButtonController���� ���� power�� ����. �̷��� �����ν� ������ ����
	}
	
}

class TVMuteCommand implements Command {
	private TV tv;
	public TVMuteCommand(TV tv){
		this.tv = tv;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.tv.mute();
	}
	
}

class AirconditionerOnCommand implements Command {
	private Airconditioner ad;
	
	public AirconditionerOnCommand(Airconditioner ad) {
		this.ad = ad;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.ad.power();
	}
	
}

class AirconditionerDehumidificationOnCommand implements Command {
	private Airconditioner ad;
	public AirconditionerDehumidificationOnCommand(Airconditioner ad) {
		this.ad = ad;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.ad.dehumidification();
	}
	
}



