package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Joystick;
	
public class Controller {
	public static final int LEFT_TRIGGER = 7;
	public static final int RIGHT_TRIGGER = 8;
	
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	
	public static final int SELECT_BUTTON = 9;
	public static final int START_BUTTON = 10;
	
	public static final int LEFT_X_ABXY = 1;
	public static final int DOWN_A_ABXY = 2;
	public static final int RIGHT_B_ABXY = 3;
	public static final int UP_Y_ABXY = 4;
	
	
	public enum DPadDirection {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		UP_RIGHT,
		DOWN_RIGHT,
		UP_LEFT,
		DOWN_LEFT,
		NONE
	}
	
	public Joystick stick;
	public Controller(int port) {
		stick = new Joystick(port);
	}
	public double getLeftX () {
		return stick.getRawAxis(0);
	}
	public double getLeftY () {
		return stick.getRawAxis(1);
	}
	public double getRightX () {
		return stick.getRawAxis(2);	
	}
	public double getRightY () {
		return stick.getRawAxis(3);
	}
	public Boolean buttonPressed (int buttonNum) {
		return stick.getRawButton(buttonNum);
	}
	public DPadDirection getDPad () {
		double x = stick.getRawAxis(4);
		double y = stick.getRawAxis(5);
		if (x >= 0.5) {
			if (y >= 0.5) {
				return DPadDirection.UP_RIGHT;
			}
			if (y <= -0.5) {
				return DPadDirection.DOWN_RIGHT;
			}
			return DPadDirection.RIGHT;
		}
		if (x <= -0.5) {
			if (y >= 0.5) {
				return DPadDirection.UP_LEFT;
			}
			if (y <= -0.5) {
				return DPadDirection.DOWN_LEFT;
			}
			return DPadDirection.LEFT;
		}
		if (y >= 0.5) {
			return DPadDirection.UP;
		}
		if (y <= -0.5) {
			return DPadDirection.DOWN;
		}
		return DPadDirection.NONE;
	}
	/*Driver:
	 * Joysticks Left and Right = Drive
	 * 
	 * Bumper/Trigger = Blink Red
	 * 
	 * 
	 * Operator:
	 * D-Pad = preset Arm Angles
	 * Right Bumper/Trigger = Manually Move Arm
	 * Select Button(9) = Stop Arm
	 * 
	 * Left Bumper/Trigger = Raise/Lower Hook
	 *
	 * Start Button(10) = Shoot
	 *
	 * "X,Y,B Button(1,4,3)" = Set Fly Wheel Speed
	 * 
	 *  A Button (2) = Center Ball (bring in)
	 *  
	 *  subject to change
	 */
}
