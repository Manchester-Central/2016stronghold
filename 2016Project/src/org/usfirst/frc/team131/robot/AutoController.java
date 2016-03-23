package org.usfirst.frc.team131.robot;

import org.usfirst.frc.team131.robot.Controller.DPadDirection;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoController {
	private final double DRIVE_DIRECTION = -1.0;
	private final double DISTANCE_TO_TOWER = 24.0; // 128
	private final double DISTANCE_TO_TOWER_FROM_CORNER = 12.0; // 36
	// distances need to be verified. may not be correct.

	private final int START_STATE = 0;
	private final int FORWARD_DRIVE_STATE = 1;
	private final int FORWARD_AGAIN_STATE = 2;
	private final int FORWARD_AGAIN_AGAIN_STATE = 3;
	private final int TURN_STATE = 4;
	private final int BACKWARDS_DRIVE_STATE = 5;
	private final int ARM_UP_STATE = 6;
	private final int ARM_DOWN_STATE = 7;
	private final int LOW_SHOT_STATE = 8;
	private final int HIGH_SHOT_STATE = 9;
	private final int FINISH_STATE = 10;

	private int preAutoState = START_STATE;
	int currentAutoState = FORWARD_DRIVE_STATE;

	// nothing
	public void doNothingAuto(DriveBase drive) {
		drive.setSpeed(0.0, 0.0);
	}

	// low bar auto
	public void lowBarLowShotAuto(ShoulderArm arm, DriveBase drive, IntakeShooter intakeShooter,
			ShooterTrigger shooterTrigger) {
		// @ TODO add some ifs; make it work; fix
		switch (currentAutoState) {
		case FORWARD_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(14.0, 0.7 * DRIVE_DIRECTION);
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_DOWN_STATE;
				}
			}
			break;

		case ARM_DOWN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				arm.presetAngle(DPadDirection.RIGHT);
				arm.moveToAngle();
			} else {
				if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
					arm.stopShoulderArm();
					currentAutoState = FORWARD_AGAIN_STATE;
				}
			}
			break;
		case FORWARD_AGAIN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(DISTANCE_TO_TOWER, 0.7 * DRIVE_DIRECTION);
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_DOWN_STATE;
				}
			}
			break;
		case TURN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoTurn(90);
			} else {
				if (!drive.isTurning) {
					currentAutoState = BACKWARDS_DRIVE_STATE;
				}
			}
			break;
		case BACKWARDS_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.autoDriveStraightBackwards(-DISTANCE_TO_TOWER_FROM_CORNER, -0.5 * DRIVE_DIRECTION);
			} else {
				if (drive.isDriving) {
					currentAutoState = LOW_SHOT_STATE;
				}
			}
			break;
		case LOW_SHOT_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				intakeShooter.ballShoot2();
				intakeShooter.updateFlywheelSpeed();
			} else {
				intakeShooter.updateFlywheelSpeed();
				if (intakeShooter.checkShooterSpeed(2016.0)) {
					shooterTrigger.ballReversal();
					if (!shooterTrigger.isBallInSensor()) {
						intakeShooter.flywheelStop();
						currentAutoState = FINISH_STATE;
					}
				}
			}
			break;
		case FINISH_STATE:
		default:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
			}
			System.out.println("donezo");
			break;
		}
		// if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle()
		// <= arm.getAngleSetpoint() + 5.0) {
		// arm.stopShoulderArm();
		// drive.setSpeed( 0.7 * DRIVE_DIRECTION, 0.7 * DRIVE_DIRECTION);
		//
		// }
		// if (drive.getRightDistanceInInches() >= 150 &&
		// drive.getleftDistanceInInches() >= 150) {
		// drive.setSpeed(0.0, 0.0);
		// }
	}

	public void lowBarHighShotAuto(ShoulderArm arm, DriveBase drive, IntakeShooter intakeShooter,
			ShooterTrigger shooterTrigger) {
		// @ TODO add some ifs; make it work; fix
		switch (currentAutoState) {
		case FORWARD_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(14.0, 0.7 * DRIVE_DIRECTION);
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_DOWN_STATE;
				}
			}
			break;

		case ARM_DOWN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				arm.presetAngle(DPadDirection.RIGHT);
				arm.moveToAngle();
			} else {
				if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
					arm.stopShoulderArm();
					currentAutoState = FORWARD_AGAIN_STATE;
				}
			}
			break;
		case FORWARD_AGAIN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(DISTANCE_TO_TOWER, 0.7 * DRIVE_DIRECTION);
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_DOWN_STATE;
				}
			}
			break;
		case TURN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoTurn(-90);
			} else {
				if (!drive.isTurning) {
					currentAutoState = FORWARD_AGAIN_AGAIN_STATE;
				}
			}
			break;
		case FORWARD_AGAIN_AGAIN_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.autoDriveStraight(DISTANCE_TO_TOWER_FROM_CORNER, 0.5 * DRIVE_DIRECTION);
			} else {
				if (drive.isDriving) {
					currentAutoState = HIGH_SHOT_STATE;
				}
			}
			break;
		case HIGH_SHOT_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				intakeShooter.ballShoot1();
				intakeShooter.updateFlywheelSpeed();
			} else {
				intakeShooter.updateFlywheelSpeed();
				if (intakeShooter.checkShooterSpeed(6000.0)) {
					shooterTrigger.ballReversal();
					if (!shooterTrigger.isBallInSensor()) {
						intakeShooter.flywheelStop();
						currentAutoState = FINISH_STATE;
					}
				}
			}
			break;
		case FINISH_STATE:
		default:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
			}
			System.out.println("donezo");
			break;
		}
	}

	// drive over rough terrain, moat, or rock wall auto
	public void driveFowardAuto(DriveBase drive) {
		// needs to test
		switch (currentAutoState) {
		case FORWARD_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(24.0, 0.7 * DRIVE_DIRECTION); // 600
			} else {
				if (!drive.isDriving) {
					currentAutoState = FINISH_STATE;
				}
			}
			break;
		case FINISH_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.setSpeed(0.0, 0.0);
			}
			System.out.println("donezo");
			break;
		}

		// if( Math.abs(drive.getRightDistanceInInches()) +
		// Math.abs(drive.getleftDistanceInInches()) >= 300 *2) {
		// drive.setSpeed(0.0, 0.0);
		// }else{
		// drive.setSpeed(-0.7, -0.7);
		// }
	}

	// Start in the Spybox and score
	public void spyAuto(ShoulderArm arm, DriveBase drive, IntakeShooter intakeShooter, ShooterTrigger center,
			DigitalInput frontLeftOpticalSensor, DigitalInput frontRightOpticalSensor) {
		switch (currentAutoState) {
		case FORWARD_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(DISTANCE_TO_TOWER_FROM_CORNER, 0.5 * DRIVE_DIRECTION);
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_UP_STATE;
				}
			}
			break;
		case ARM_UP_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				arm.presetAngle(DPadDirection.LEFT);
				arm.moveToAngle();
			} else {
				if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
					arm.stopShoulderArm();
					currentAutoState = HIGH_SHOT_STATE;
				}
			}
			break;
		case HIGH_SHOT_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				intakeShooter.ballShoot1();
				intakeShooter.updateFlywheelSpeed();
			} else {
				intakeShooter.updateFlywheelSpeed();
				if (intakeShooter.checkShooterSpeed(6000.0)) {
					center.ballReversal();
					if (!center.isBallInSensor()) {
						intakeShooter.flywheelStop();
						currentAutoState = FINISH_STATE;
					}
				}
			}
			break;
		case FINISH_STATE:
		default:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
			}
			System.out.println("donezo");
			break;
		}
	}
	
	public void forwardDriveHighShot (DriveBase drive, ShooterTrigger shooterTrigger, ShoulderArm arm, IntakeShooter intakeShooter) {
		// needs to test
		switch (currentAutoState) {
		case FORWARD_DRIVE_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.resetEncoders();
				drive.autoDriveStraight(DISTANCE_TO_TOWER, 0.7 * DRIVE_DIRECTION); // 600
			} else {
				if (!drive.isDriving) {
					currentAutoState = ARM_UP_STATE;
				}
			}
			break;
		case ARM_UP_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				arm.presetAngle(DPadDirection.LEFT);
				arm.moveToAngle();
			} else {
				if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
					arm.stopShoulderArm();
					currentAutoState = HIGH_SHOT_STATE;
				}
			}
			break;
		case HIGH_SHOT_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				intakeShooter.ballShoot1();
				intakeShooter.updateFlywheelSpeed();
			} else {
				intakeShooter.updateFlywheelSpeed();
				if (intakeShooter.checkShooterSpeed(6000.0)) {
					shooterTrigger.ballReversal();
					if (!shooterTrigger.isBallInSensor()) {
						intakeShooter.flywheelStop();
						currentAutoState = FINISH_STATE;
					}
				}
			}
			break;
		case FINISH_STATE:
			if (preAutoState != currentAutoState) {
				preAutoState = currentAutoState;
				drive.setSpeed(0.0, 0.0);
			}
			System.out.println("donezo");
			break;
		}
	
	}
}