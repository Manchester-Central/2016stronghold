package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this is the shoulder arm class, it controls its position and speed
 * 
 * @author Charles
 *
 */
public class ShoulderArm implements ArmInfo {
	private double goalAngle = -1;
	private double angleChange = 5;

	private static final double maxAngle = 180;
	private static final double minAngle = 0;

	// 0 DEGREES IS VERTICAL!!!
	// NEGATIVE IS TOWARDS THE ROBOT
	// POSITIVE IS AWAY FROM THE ROBOT
	public static final double DOWN_DPAD_ANGLE = 90.0;
	public static final double LEFT_DPAD_ANGLE = -70.0;
	public static final double UP_DPAD_ANGLE = -23.5; //-23.5
	public static final double RIGHT_DPAD_ANGLE = 81.5;

	DigitalInput armLimitSwitch = new DigitalInput(PortConstants.ARM_LIMIT_SWITCH_PORT);

	ChaosPot pot = new ChaosPot();
	AngleSpeedModifier modifier = new AngleSpeedModifier();

	// private Talon leftShoulderTalon = new
	// Talon(PortConstants.LEFT_SHOULDER_TALON);
	private Talon shoulderTalon = new Talon(PortConstants.SHOULDER_TALON_PORT);
	public final double DOWN_SPEED = 1.0;
	public final double UP_SPEED = -1.0;

	public ShoulderArm() {
		shoulderTalon.setInverted(true);

		// TODO Auto-generated constructor stub
	}

	/**
	 * get the angle for shoulder arm
	 */
	public double getAngle() {
		return pot.getAngle();
	}

	/**
	 * sees which direction the motor needs to go
	 * 
	 * @param isUp
	 *            (if true, go up; if false, go down)
	 */
	public void shoulderManualAngle(boolean isUp) {
		if (isUp) {
			goalAngle = Math.min(maxAngle, goalAngle + angleChange);
		} else {
			goalAngle = Math.max(minAngle, goalAngle - angleChange);
		}

	}

	/**
	 * gets the shoulder speed
	 */
	public double getTestShoulderSpeed() {
		return modifier.adjustSpeed(pot.getAngle(), LEFT_DPAD_ANGLE, UP_SPEED);
	}

	/**
	 * sets arm to preset angles
	 * 
	 * @param direction
	 *            (goes to angle)
	 */
	public void angleAdjust(double rawAdjustValue) {
		double adjustValue = rawAdjustValue;
		if (rawAdjustValue > -0.1 && rawAdjustValue < 0.1) {
			adjustValue = 0.0;
		}
		goalAngle += adjustValue;
	}
	
	public void setGoalAngleManually (double angle) {
		goalAngle = angle;
	}

	public void presetAngle(Controller.DPadDirection direction) {
		// double speed = 0;
		switch (direction) {
		case RIGHT:
			// speed = modifier.adjustSpeed(pot.getAngle(), UPPER_MIDDLE,
			// UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("right");
			goalAngle = RIGHT_DPAD_ANGLE;
			break;
		case LEFT:
			// speed = modifier.adjustSpeed(pot.getAngle(), LOWER_MIDDLE,
			// DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("left");
			goalAngle = LEFT_DPAD_ANGLE;
			break;
		case UP:
			// speed = modifier.adjustSpeed(pot.getAngle(), TOP, UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("up");
			goalAngle = UP_DPAD_ANGLE;
			break;
		case DOWN:
			// speed = modifier.adjustSpeed(pot.getAngle(), BOTTOM, DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("down");
			goalAngle = DOWN_DPAD_ANGLE;
			break;
		default:
			// shoulderManual (speed);
			// System.out.println("none");
			break;
		}

	}

	/**
	 * stops shoulder arm
	 */
	public void stopShoulderArm() {
		goalAngle = -1;
	}

	/**
	 * moves arm to and angle
	 */
	public void moveToAngle() {
		if (goalAngle != -1) {
			//double speed = modifier.adjustSpeed(pot.getAngle(), goalAngle, UP_SPEED);
			double speed = modifier.adjustSpeed(pot.getAngle(), goalAngle, 0.8);
			setShoulderSpeed(speed);
			SmartDashboard.putNumber("shoulder speed", speed);
		}

		// TODO Auto-generated method stub5

	}

	/**
	 * sets shoulder speed
	 * 
	 * @param speed
	 *            (speed)
	 */
	public void setShoulderSpeed(double speed) {
		// leftShoulderTalon.set(speed);
		 if (armLimitSwitch.get() && (speed < 0)) {
		//if (false) {
			 stopShoulderMovement();
		} else {
			shoulderTalon.set(speed);
		}
	}

	@Override
	/**
	 * gets the angle setpoint
	 */
	public double getAngleSetpoint() {
		// TODO Auto-generated method stub
		return goalAngle;
	}

	public void stopShoulderMovement() {
		setShoulderSpeed(0);
	}
}
