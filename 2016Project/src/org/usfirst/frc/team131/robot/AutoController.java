package org.usfirst.frc.team131.robot;

import org.usfirst.frc.team131.robot.Controller.DPadDirection;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoController {
	
	//nothing
	public void autoStateDefault(DriveBase drive) {
		drive.setSpeed(0.0, 0.0);
	}

	// low bar auto
	public void autoStateArmBackward(ShoulderArm arm, DriveBase drive) {
		arm.presetAngle(DPadDirection.RIGHT);
		arm.moveToAngle();
		if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
			arm.stopShoulderArm();
			drive.setSpeed(-0.7, -0.7);
			
		}
		if (drive.getRightDistanceInInches() >= 150 && drive.getleftDistanceInInches() >= 150) {
			drive.setSpeed(0.0, 0.0);
			
			}
		}

	// drive over rough terrain, moat, or rock wall auto
	public void autoStateForward(DriveBase drive) {
		// needs to test
		drive.leftEncoder.reset();
		drive.rightEncoder.reset();
		drive.autoDriveStraight(600, -0.7);
		
//		if( Math.abs(drive.getRightDistanceInInches()) + Math.abs(drive.getleftDistanceInInches()) >= 300 *2) {
//			drive.setSpeed(0.0, 0.0);
//		}else{
//			drive.setSpeed(-0.7, -0.7);
//		}
	}
	
	// Start in the Spybox and score
	public void spyAuto (ShoulderArm arm, DriveBase drive, IntakeShooter intakeShooter, ShooterTrigger center, DigitalInput frontLeftOpticalSensor, DigitalInput frontRightOpticalSensor) {
		if (arm.getAngle() >= arm.getAngleSetpoint() - 5.0 && arm.getAngle() <= arm.getAngleSetpoint() + 5.0) {
			drive.setSpeed(-0.7, -0.7);
			arm.stopShoulderArm();
		}
		if (drive.getleftDistanceInInches() >= 96 && drive.getRightDistanceInInches() >= 96){
			drive.setSpeed(0.7, -0.7);
		}
		if (frontLeftOpticalSensor.get() && frontRightOpticalSensor.get()){
			drive.setSpeed(0.7, 0.7);
		}
		if (drive.getleftDistanceInInches() >= 210 && drive.getRightDistanceInInches() >= 210){
			drive.setSpeed(0, 0);
			intakeShooter.ballShoot1();
			intakeShooter.updateFlywheelSpeed();
		}
		if (intakeShooter.checkShooterSpeed(5500.0)) {
			center.ballReversal();
		}
	}

}
