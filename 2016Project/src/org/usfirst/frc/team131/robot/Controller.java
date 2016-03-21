package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the controller class
 * 
 * @author Charles
 *
 */
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

	/**
	 * This is the enumeration for the d-pad directions
	 * 
	 * @author Charles
	 *
	 */
	public enum DPadDirection {
		LEFT, RIGHT, UP, DOWN, UP_RIGHT, DOWN_RIGHT, UP_LEFT, DOWN_LEFT, NONE
	}

	public Joystick stick;

	/**
	 * Controller constructor
	 * 
	 * @param port
	 *            (port number for the Controller)
	 */
	public Controller(int port) {
		stick = new Joystick(port);
	}

	/**
	 * gets the horizontal value for the left joystick
	 * 
	 * @return (left joystick horizontal value)
	 */
	public double getLeftX() {
		return stick.getRawAxis(0);
	}

	/**
	 * gets the vertical value for the left joystick
	 * 
	 * @return (left joystick vertical value)
	 */
	public double getLeftY() {
		return stick.getRawAxis(1);
	}

	/**
	 * gets the horizontal value for the right joystick
	 * 
	 * @return (right joystick horizontal value)
	 */
	public double getRightX() {
		return stick.getRawAxis(2);
	}

	/**
	 * gets the vertical value for the right joystick
	 * 
	 * @return (right joystick vertical value)
	 */
	public double getRightY() {
		return stick.getRawAxis(3);
	}

	/**
	 * returns if a certain button is being pressed
	 * 
	 * @param buttonNum
	 *            (button that is being pressed)
	 * @return (returns true if that button is being pressed)
	 */
	public Boolean buttonPressed(int buttonNum) {
		return stick.getRawButton(buttonNum);
	}

	/**
	 * returns which d-pad direction is being pressed
	 * 
	 * @return (d-pad direction)
	 */
	public DPadDirection getDPad() {
		int pov = stick.getPOV();
		switch (pov) {
		case 0:
			return DPadDirection.UP;
		case 90:
			return DPadDirection.RIGHT;
		case 180:
			return DPadDirection.DOWN;
		case 270:
			return DPadDirection.LEFT;
		default:
			return DPadDirection.NONE;

		}
		// double x = stick.getRawAxis(6);
		// double y = stick.getRawAxis(7);
		// if (x >= 0.5) {
		// if (y >= 0.5) {
		// return DPadDirection.UP_RIGHT;
		// }
		// if (y <= -0.5) {
		// return DPadDirection.DOWN_RIGHT;
		// }
		// return DPadDirection.RIGHT;
		// }
		// if (x <= -0.5) {
		// if (y >= 0.5) {
		// return DPadDirection.UP_LEFT;
		// }
		// if (y <= -0.5) {
		// return DPadDirection.DOWN_LEFT;
		// }
		// return DPadDirection.LEFT;
		// }
		// if (y >= 0.5) {
		// return DPadDirection.UP;
		// }
		// if (y <= -0.5) {
		// return DPadDirection.DOWN;
		// }
		// return DPadDirection.NONE;
	}
	/*
	 * Driver: Joysticks Left and Right = Drive
	 * 
	 * Left Bumper = Blink Red
	 * 
	 * Right Bumper/Trigger = Raise/Lower Hook Regular Buttons(Diver Preference)
	 * = Climb/Descend Tower
	 * 
	 * -------------------------------------------------------------------------
	 * 
	 * Operator: D-Pad = preset Arm Angles Right Bumper/Trigger = Manually Move
	 * Arm Select Button(9) = Stop Arm
	 *
	 * Start Button(10) = Shoot
	 *
	 * "X,Y,B Button(1,4,3)" = Set Fly Wheel Speed
	 * 
	 * A Button (2) = Auto Center Ball 
	 * 
	 * (subject to change)
	 */
}
