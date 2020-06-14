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
	private boolean dehumidificationOn = false; //제습기능
	
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
	private Command command2; //tv객체를 바로 사용하지않고 command객체로 만들어줌
	
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
		//tv객체 만들어주고
//		TVOnCommand toc = new TVOnCommand(tv); 
		//tv객체를 TVOncommand에 넣어주고
//		TVMuteCommand tmc = new TVMuteCommand(tv); 
		//TVMuteCommand에 넣어준다
		
		Airconditioner tv = new Airconditioner();
		AirconditionerOnCommand toc = new AirconditionerOnCommand(tv); 
		AirconditionerDehumidificationOnCommand tmc = new AirconditionerDehumidificationOnCommand(tv);
		TwoButtonController tbc = new TwoButtonController(toc, tmc); //command1과 command2로 
		
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
	//	외부의 tv객체를 받고 전역변수의 tv에 넣어준다
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.tv.power();
	//기존에서는 TwoButtonController에서 직접 power를 켰음. 이렇게 함으로써 의존성 제거
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



