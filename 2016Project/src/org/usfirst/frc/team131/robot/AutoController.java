package org.usfirst.frc.team131.robot;

import org.usfirst.frc.team131.robot.Controller.DPadDirection;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoController {

	private static final double INCHES_TO_CROSS_DEFENSE = 96;
	private static final double INCHES_TO_REACH_DEFENSE = 84;

	public static enum autoState {
		START, DRIVE_FORWARD, DRIVE_BACKWARD, TURN_CLOCKWISE, STOP, SET_ARM_FORWARD, SET_ARM_SHOOT, SET_ARM_BACKWARD, SET_ARM_BACKWARD_RAMP, SET_FLYWHEEL_SPEED, SHOOT

	}

	private autoState autoCase;

	public void autoStateBackward(ShoulderArm arm, DriveBase drive) {
		switch (autoCase) {

		case SET_ARM_BACKWARD_RAMP:
			arm.presetAngle(DPadDirection.UP);
			break;
		case DRIVE_BACKWARD:
			drive.setSpeed(-0.2, -0.2);
			break;
		case STOP:
			drive.setSpeed(0, 0);
			break;
		default:

			break;
		}
		if (autoCase == autoState.START) {
			autoCase = autoState.SET_ARM_BACKWARD_RAMP;
		}
		if (arm.getAngle() == arm.getAngleSetpoint()) {
			autoCase = autoState.DRIVE_BACKWARD;
		}
		if (drive.getRightDistanceInInches() >= INCHES_TO_CROSS_DEFENSE
				&& drive.getleftDistanceInInches() >= INCHES_TO_CROSS_DEFENSE) {
			autoCase = autoState.STOP;
		}
	}

	public void autoCheval(DriveBase drive, ShoulderArm arm) {
		boolean isStopped = false;
		switch (autoCase) {

		case SET_ARM_SHOOT:
			arm.presetAngle(DPadDirection.LEFT);
			break;
		case DRIVE_FORWARD:
			drive.setSpeed(0.2, 0.2);
			break;
		case STOP:
			drive.setSpeed(0, 0);
		case SET_ARM_FORWARD:
			arm.presetAngle(DPadDirection.DOWN);
			break;
		default:
			break;
		}

		if (autoCase == autoState.START) {
			autoCase = autoState.SET_ARM_SHOOT;
		}
		if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
			autoCase = autoState.DRIVE_FORWARD;
		}
		if (drive.getRightDistanceInInches() >= INCHES_TO_REACH_DEFENSE
				&& drive.getleftDistanceInInches() >= INCHES_TO_REACH_DEFENSE) {
			autoCase = autoState.STOP;
			isStopped = true;
		}
		if (isStopped) {
			autoCase = autoState.SET_ARM_FORWARD;
			isStopped = false;
		}
		if (arm.getAngle() == arm.getAngleSetpoint()) {
			autoCase = autoState.DRIVE_FORWARD;
		}
	}

	public void autoStateForward(ShoulderArm arm, DriveBase drive) {
		switch (autoCase) {

		case SET_ARM_FORWARD:
			arm.presetAngle(DPadDirection.DOWN);
			break;
		case DRIVE_FORWARD:
			drive.setSpeed(0.2, 0.2);
			break;
		case STOP:
			drive.setSpeed(0, 0);
			break;
		default:

			break;
		}
		if (autoCase == autoState.START) {
			autoCase = autoState.DRIVE_FORWARD;
		}
		if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
			autoCase = autoState.DRIVE_BACKWARD;
		}
		if (drive.getRightDistanceInInches() >= INCHES_TO_CROSS_DEFENSE
				&& drive.getleftDistanceInInches() >= INCHES_TO_CROSS_DEFENSE) {
			autoCase = autoState.STOP;
		}
	}

	public void autoStateSpy(ShoulderArm arm, DriveBase drive, IntakeShooter intakeShooter, ShooterTrigger center,
			DigitalInput frontOpticalSensorA, DigitalInput frontOpticalSensorB) {
		switch (autoCase) {
		case SET_ARM_SHOOT:
			arm.presetAngle(DPadDirection.LEFT);
			break;
		case DRIVE_BACKWARD:
			drive.setSpeed(-0.2, -0.2);
			break;
		case TURN_CLOCKWISE:
			// may need to alter
			drive.setSpeed(0.1, -0.1);
			break;
		case DRIVE_FORWARD:
			drive.setSpeed(0.2, 0.2);
			break;
		case SET_FLYWHEEL_SPEED:
			intakeShooter.ballShoot1();
			break;
		case SHOOT:
			center.readyShot();
			break;
		default:
			break;
		}
		intakeShooter.updateFlywheelSpeed();
		if (autoCase == autoState.START) {
			autoCase = autoState.SET_ARM_SHOOT;
		}
		if (arm.getAngle() == arm.getAngleSetpoint()) {
			autoCase = autoState.DRIVE_BACKWARD;
		}
		if (drive.getleftDistanceInInches() >= 96 && drive.getRightDistanceInInches() >= 96) {
			autoCase = autoState.TURN_CLOCKWISE;
		}
		if (frontOpticalSensorA.get() && frontOpticalSensorB.get()) {
			autoCase = autoState.DRIVE_FORWARD;
		}
		if (drive.getleftDistanceInInches() >= 196 && drive.getRightDistanceInInches() >= 196) {
			autoCase = autoState.SET_FLYWHEEL_SPEED;
		}
		if (intakeShooter.checkShooterSpeed(1)) {
			autoCase = autoState.SHOOT;
		}
	}
	public void straightForward () {
		switch (autoCase) {
		
		}
	}

	public void autoStateDefault() {

	}
}
