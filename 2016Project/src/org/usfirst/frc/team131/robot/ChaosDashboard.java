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
		wantedSpeed = 1.12;
	}
	
	/**
	 * This function displays the preset arm positions 
	 */
	public void displayArmPositions () {
	    
		// all smart dashboard information should go into the periodics


        // inital dashboard constants
        SmartDashboard.putNumber ("First Position ", ShoulderArm.FORWARD_POSITION);
        SmartDashboard.putNumber("Second Position ", ShoulderArm.SCORING_POSITION);
        SmartDashboard.putNumber("Third Position ", ShoulderArm.BACKWARD_POSITION);
        SmartDashboard.putNumber("Fourth Position ", ShoulderArm.BACKWARDS_RAMP_POSITION);
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
	
}
