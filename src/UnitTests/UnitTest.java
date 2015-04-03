package UnitTests;

import recording.Action;
import recording.Writer;


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
		robot.recorder.stop();
		int i = 0;
		for (Action a:robot.recorder.actions) {
			//System.out.println(a.startTime + " " + a.endTime);
			i++;
		}
		//System.out.println(i); //number of actions
		String s = Writer.write(robot.recorder.actions);
		System.out.println(s);
	}

}
