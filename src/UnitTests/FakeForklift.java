package UnitTests;

import recording.Recorder;

public class FakeForklift {
	
	Recorder recorder;
	
	public FakeForklift(Recorder r){ 
		recorder = r;
	}
	
	public FakeForklift() {
		
	}
	
	public void setDir(double direction) {
		//do something, set motors, etc
	}
}
