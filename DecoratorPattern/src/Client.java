
public class Client {
	public static void main(String[] args) {
//		RoadDisplay road = new RoadDisplay();
//		road.draw();
//		
//		Display roadWithLane = new LaneDecorator(new RoadDisplay());
//		roadWithLane.draw();
//		
//		Display roadWithTraffic = new TrafficDecorator(new RoadDisplay());
//		roadWithTraffic.draw();
//		
//		Display roadWithLaneAndTraffic = new TrafficDecorator(new LaneDecorator(new RoadDisplay()));
//		roadWithLaneAndTraffic.draw();
//		
//		Display roadWithLaneAndTrafficAndCrossing = 
//				new TrafficDecorator(new LaneDecorator(new CrossingDecorator(new RoadDisplay())));
//		roadWithLaneAndTrafficAndCrossing.draw();
//		
		Display dsp = new RoadDisplay();
		dsp = new LaneDecorator(dsp);
		dsp = new TrafficDecorator(dsp);
		dsp = new CrossingDecorator(dsp);
		dsp.draw();
	}
}

abstract class Display {
	public abstract void draw();
}

class RoadDisplay extends Display {
	public void draw() {
		System.out.println("���� �⺻ ǥ��");
	}
}

class DisplayDecorator extends Display {
	private Display decoratedDisplay;
	public DisplayDecorator(Display decoratedDisplay) {
		this.decoratedDisplay = decoratedDisplay;
	}
	
	public void draw() {
		decoratedDisplay.draw();
	}
}

class LaneDecorator extends DisplayDecorator {
	public LaneDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw();
		drawLane();
	}
	private void drawLane() {
		System.out.println("\t���� ǥ��");
	}
}

class TrafficDecorator extends DisplayDecorator {
	public TrafficDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw();
		drawTraffic();
	}
	private void drawTraffic() {
		System.out.println("\t���뷮 ǥ��");
	}
}

class CrossingDecorator extends DisplayDecorator {
	public CrossingDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	public void draw() {
		super.draw();
		drawCrossing();
	}
	private void drawCrossing() {
		System.out.println("\tȾ�ܺ��� ǥ��");
	}
}