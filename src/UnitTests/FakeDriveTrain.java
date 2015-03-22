package UnitTests;

import recording.Action;
import recording.Recorder;

public class FakeDriveTrain {
	
	Recorder recorder;
	
	public FakeDriveTrain(Recorder r) {
		recorder = r;
	}
	
	public void tankDrive(double left, double right) {
		//do something, set motors, etc
	}
}
