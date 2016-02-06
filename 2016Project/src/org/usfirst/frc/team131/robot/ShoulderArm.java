package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

/**
 * this is the shoulder arm class, it controls its position and speed
 * @author Charles
 *
 */
public class ShoulderArm implements ArmInfo {
	private double goalAngle = -1;
	private double angleChange = 5;
	
	private static final double maxAngle = 180;
	private static final double minAngle = 0;
	
	public static final double FIRST_POSITION = 0.0;
	public static final double SECOND_POSITION = 90.0;
	public static final double THIRD_POSITION = 135.0;
	public static final double FOURTH_POSITION = 180.0;
	
	ChaosPot pot = new ChaosPot();
	AngleSpeedModifier modifier = new AngleSpeedModifier();


	private Talon leftShoulderTalon = new Talon(PortConstants.LEFT_SHOULDER_TALON);
	private Talon rightShoulderTalon = new Talon(PortConstants.RIGHT_SHOULDER_TALON);
	public final double DOWN_SPEED = 1.0;
	public final double UP_SPEED = -1.0;
	
	/**
	 * get the angle for shoulder arm
	 */
	public double getAngle() {
		return pot.getAngle();
	}

	/**
	 * sees which direction the motor needs to go
	 * @param isUp (if true, go up; if false, go down)
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
		return modifier.adjustSpeed(pot.getAngle(), SECOND_POSITION, UP_SPEED);
	}

	/**
	 * sets arm to preset angles
	 * @param direction (goes to angle)
	 */
	public void presetAngle(Controller.DPadDirection direction) {
		// double speed = 0;
		switch (direction) {
		case RIGHT:
			// speed = modifier.adjustSpeed(pot.getAngle(), UPPER_MIDDLE,
			// UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("right");
			goalAngle = FOURTH_POSITION;
			break;
		case LEFT:
			// speed = modifier.adjustSpeed(pot.getAngle(), LOWER_MIDDLE,
			// DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("left");
			goalAngle = SECOND_POSITION;
			break;
		case UP:
			// speed = modifier.adjustSpeed(pot.getAngle(), TOP, UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("up");
			goalAngle = THIRD_POSITION;
			break;
		case DOWN:
			// speed = modifier.adjustSpeed(pot.getAngle(), BOTTOM, DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("down");
			goalAngle = FIRST_POSITION;
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
			double speed = modifier.adjustSpeed(pot.getAngle(), goalAngle, UP_SPEED);
			setShoulderSpeed(speed);
		}

		// TODO Auto-generated method stub

	}
	
	/**
	 * sets shoulder speed
	 * @param speed (speed)
	 */
	private void setShoulderSpeed(double speed) {
		leftShoulderTalon.set(speed);
		rightShoulderTalon.set(speed);
	}
	
	@Override
	/**
	 * gets the angle setpoint
	 */
	public double getAngleSetpoint() {
		// TODO Auto-generated method stub
		return 0;
	}
}
