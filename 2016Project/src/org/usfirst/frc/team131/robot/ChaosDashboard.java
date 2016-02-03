package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ChaosDashboard {
	
	public void throwInformationOnDashboard (ShoulderArm arm) {
	    
		// all smart dashboard information should go into the periodics
        SmartDashboard.putNumber("Arm Pot", arm.getAngle());
        SmartDashboard.putNumber("Shoulder Arm Speed", arm.getTestShoulderSpeed());
        SmartDashboard.putNumber("current flywheel angular velocity", 6.01);
        SmartDashboard.putBoolean("Fly wheel up to speed", false);
        SmartDashboard.putNumber("setpoint", ShoulderArm.THIRD_POSITION);
        SmartDashboard.putNumber("current position", 175.5);
        
        // inital dashboard constants
        SmartDashboard.putNumber ("First Position", ShoulderArm.FIRST_POSITION);
        SmartDashboard.putNumber("Second Position", ShoulderArm.SECOND_POSITION);
        SmartDashboard.putNumber("Third Position", ShoulderArm.THIRD_POSITION);
        SmartDashboard.putNumber("Fourth Position", ShoulderArm.FOURTH_POSITION);
	}
	
}
