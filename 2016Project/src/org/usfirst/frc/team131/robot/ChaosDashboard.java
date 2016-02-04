package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ChaosDashboard {
	
	double wantedSpeed;
	
	public ChaosDashboard () {
		wantedSpeed = 1.12;
	}
	
	public void displayArmPositions () {
	    
		// all smart dashboard information should go into the periodics


        // inital dashboard constants
        SmartDashboard.putNumber ("First Position", ShoulderArm.FIRST_POSITION);
        SmartDashboard.putNumber("Second Position", ShoulderArm.SECOND_POSITION);
        SmartDashboard.putNumber("Third Position", ShoulderArm.THIRD_POSITION);
        SmartDashboard.putNumber("Fourth Position", ShoulderArm.FOURTH_POSITION);
	}
	
	public void displayArm (ArmInfo arm) {
        SmartDashboard.putNumber("Arm angle", arm.getAngle());
        SmartDashboard.putNumber("Shoulder Arm Speed", arm.getTestShoulderSpeed());
        SmartDashboard.putNumber("setpoint", arm.getAngleSetpoint());
	}
	
	public void diplayShooter (IntakeShooter shooter) {
        SmartDashboard.putNumber("current flywheel angular velocity", shooter.getSpeed());
        SmartDashboard.putBoolean("Fly wheel up to speed", shooter.checkShooterSpeed(wantedSpeed));
	}
	
}
