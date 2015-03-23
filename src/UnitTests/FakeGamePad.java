package UnitTests;

public class FakeGamePad {
	
	private double rightTrigger;
	private double leftTrigger;
	private double leftStick;
	private double rightStick;
	private boolean button;
	
	public FakeGamePad() {
		rightTrigger = leftTrigger = leftStick = rightStick = 0;
		button = false;
	}
	
	public boolean buttonPressed() {
		return button;
	}
	
	public double getRightTrigger() {
		return rightTrigger;
	}
	
	public double getLeftTrigger() {
		return leftTrigger;
	}
	
	public double getRightStickX() {
		return rightStick;
	}
	
	public double getRightStickY() {
		return leftStick;
	}
	
	public void setButtonPressed(boolean b) {
		button = b;
	}
	
	public void setRightTrigger(double r) {
		rightTrigger = r;
	}
	
	public void setLeftTrigger(double l) {
		leftTrigger = l;
	}
	
	public void setRightStickX(double x) {
		rightStick = x;
	}
	
	public void setRightStickY(double y) {
		leftStick = y;
	}
}
