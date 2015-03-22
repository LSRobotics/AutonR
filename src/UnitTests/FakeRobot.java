package UnitTests;
import recording.Recorder;

/**
 * 
 * Example of typical iterative robot code for testing
 *
 */
public class FakeRobot {

	public static Recorder recorder = new Recorder(true);
	FakeDriveTrain dt = new FakeDriveTrain();
	
	public static void robotInit() { 
		recorder.start();
	}
	
	public static void autononomousPeriodic() { 
		
	}
	
	public static void teleopPeriodic() { 
			
	}
}
