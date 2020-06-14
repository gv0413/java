import java.util.Calendar;

public class playMP3 {
	
	public static void main(String[] args) {
		TimeReminder rt = new TimeReminder();
		TimeProvider tp;
		tp = new FakeTimeProvider();
		rt.setTimeProvider(tp);
		rt.reminder(new MP3());	
	}
}

interface TimeProvider{
	public int getTime();
}

class RealTimeProvider implements TimeProvider {
	Calendar cal;
	
	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		cal = Calendar.getInstance();
		
		return cal.get(Calendar.HOUR_OF_DAY);
	}
}

class FakeTimeProvider implements TimeProvider {

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 22; //10½Ã
	}
}

class TimeReminder {
	TimeProvider tp;
	
	public void setTimeProvider(TimeProvider tp) {
		this.tp = tp;
	}
	
	public void reminder(MP3 m)  {
		if(tp.getTime() >=22) {
			m.playSong();
		}
	}
}

class MP3 {
	public void playSong() {
		System.out.println("Play MP3~!!");
	}
}