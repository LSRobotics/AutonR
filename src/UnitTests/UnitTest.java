package UnitTests;

import recording.Action;


public class UnitTest {

	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() { 
		FakeRobot robot = new FakeRobot();
		robot.robotInit();
		for(double right = 0.0; right < .99; right += .01) {
			robot.gp.setRightStickX(right);
			robot.teleopPeriodic();
		}
		for (Action a:robot.recorder.actions) {
			System.out.println(a.params[0]);
		}
		
	}

}
