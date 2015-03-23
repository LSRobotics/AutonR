package UnitTests;


public class UnitTest {

	public static void main(String[] args) {
		FakeRobot robot = new FakeRobot();
		FakeGamePad gp = new FakeGamePad();
		robot.robotInit();
		for(double right = 0.0; right < .99; right += .01) {
			gp.setRightStickX(right);
			robot.teleopPeriodic();
		}
	}
	
	public static void test1() { 
		
	}

}
