package UnitTests;
import recording.Action;
import recording.Recorder;

/**
 * 
 * Example of typical iterative robot code for testing
 *
 */
public class FakeRobot {

	Recorder recorder = new Recorder(true);
	FakeDriveTrain dt = new FakeDriveTrain(recorder);
	FakeGamePad gp = new FakeGamePad();
	FakeForklift fl = new FakeForklift();
	int a_button = 11;
	
	public void robotInit() { 
		recorder.start();
	}

	public void teleopPeriodic() { 
		
		if (gp.getRightTrigger() > .1) {
			double left = gp.getRightTrigger();
			double right = left;
			dt.tankDrive(left, right);
			recorder.add(new Action("tankDrive", new Object[] {left, right}, recorder.getTime()));
		}
		
		if (gp.getRightStickY() > .1) {
			double direction = gp.getRightStickY();
			fl.setDir(direction);
			recorder.add(new Action("setDir", new Object[] {direction}, recorder.getTime()));
		}
		if (gp.buttonPressed()) {
			recorder.stop();
		}
		recorder.clearIter(); //checks to see if actions are still occurring, if they are not, push them to
							  //permanent action list
	}
	
	public void autononomousPeriodic() { 
		
	}
	
	public void testPeriodic() {
		
	}
}
