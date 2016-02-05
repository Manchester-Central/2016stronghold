package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class ShoulderArm {
	private double goalAngle = -1;
	private double angleChange = 5;
	ChaosPot pot = new ChaosPot();
	AngleSpeedModifier modifier = new AngleSpeedModifier();
	private final double TOP = 90;
	private final double BOTTOM = 0;
	private final double UPPER_MIDDLE = 60;
	private final double LOWER_MIDDLE = 30;

	private Talon leftShoulderTalon = new Talon(PortConstants.LEFT_SHOULDER_TALON);
	private Talon rightShoulderTalon = new Talon(PortConstants.RIGHT_SHOULDER_TALON);
	public final double DOWN_SPEED = 1.0;
	public final double UP_SPEED = -1.0;

	public double getAngle() {
		return pot.getAngle();
	}

	public void shoulderManualAngle(boolean isUp) {
		if (isUp) {
			goalAngle = goalAngle + angleChange;
		} else {
			goalAngle = goalAngle - angleChange;
		}

	}

	public double getTestShoulderSpeed() {
		return modifier.adjustSpeed(pot.getAngle(), UPPER_MIDDLE, UP_SPEED);
	}

	public void presetAngle(Controller.DPadDirection direction) {
		// double speed = 0;
		switch (direction) {
		case RIGHT:
			// speed = modifier.adjustSpeed(pot.getAngle(), UPPER_MIDDLE,
			// UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("right");
			goalAngle = UPPER_MIDDLE;
			break;
		case LEFT:
			// speed = modifier.adjustSpeed(pot.getAngle(), LOWER_MIDDLE,
			// DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("left");
			goalAngle = LOWER_MIDDLE;
			break;
		case UP:
			// speed = modifier.adjustSpeed(pot.getAngle(), TOP, UP_SPEED);
			// shoulderManual (speed);
			// System.out.println("up");
			goalAngle = TOP;
			break;
		case DOWN:
			// speed = modifier.adjustSpeed(pot.getAngle(), BOTTOM, DOWN_SPEED);
			// shoulderManual (speed);
			// System.out.println("down");
			goalAngle = BOTTOM;
			break;
		default:
			// shoulderManual (speed);
			// System.out.println("none");
			break;
		}

	}

	public void stopShoulderArm() {
		goalAngle = -1;
	}

	public void moveToAngle() {
		if (goalAngle != -1) {
			double speed = modifier.adjustSpeed(pot.getAngle(), goalAngle, UP_SPEED);
			setShoulderSpeed(speed);
		}

		// TODO Auto-generated method stub

	}

	private void setShoulderSpeed(double speed) {
		leftShoulderTalon.set(speed);
		rightShoulderTalon.set(speed);
	}
}
