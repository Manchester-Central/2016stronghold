package org.usfirst.frc.team131.robot;

import java.util.function.Function;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class controls the smart dashboard
 * @author Charles
 *
 */
public class ChaosDashboard {
	
	double wantedSpeed;
	/**
	 * This is the constructor, intializes the wanted speed
	 */
	public ChaosDashboard () {
		wantedSpeed = 2000.0;
	}
	
	/**
	 * This function displays the preset arm positions 
	 */
	public void displayArmPositions () {
	    
		// all smart dashboard information should go into the periodics


        // inital dashboard constants
        SmartDashboard.putNumber ("Down DPad ", ShoulderArm.DOWN_DPAD_ANGLE);
        SmartDashboard.putNumber("Left DPad ", ShoulderArm.LEFT_DPAD_ANGLE);
        SmartDashboard.putNumber("Up DPad ", ShoulderArm.UP_DPAD_ANGLE);
        SmartDashboard.putNumber("Right DPad ", ShoulderArm.RIGHT_DPAD_ANGLE);
	}
	
	/**
	 *  This function displays arm info
	 * @param arm (ArmInfo class)
	 */
	public void displayArm (ArmInfo arm) {
        SmartDashboard.putNumber("Arm angle ", arm.getAngle());
        SmartDashboard.putNumber("Shoulder Arm Speed ", arm.getTestShoulderSpeed());
        SmartDashboard.putNumber("Setpoint ", arm.getAngleSetpoint());
	}
	
	/**
	 * This function displays shooter info
	 * @param shooter (IntakeShooter class)
	 * @param center (BallCenterMechanism class)
	 */
	public void diplayShooter (IntakeShooter shooter, ShooterTrigger center) {
        SmartDashboard.putNumber(" Current Flywheel Angular Velocity ", shooter.getSpeed());
        SmartDashboard.putBoolean(" Flywheel Up to Speed ", shooter.checkShooterSpeed(wantedSpeed));
        SmartDashboard.putBoolean(" Ball is in Position ", center.isBallInSensor());
	}
	
	public void diplayDrive (DriveBase drive) {
		SmartDashboard.putNumber("left encoder", drive.getleftDistanceInInches());
		SmartDashboard.putNumber("right encoder", drive.getRightDistanceInInches());
	}
	
}
