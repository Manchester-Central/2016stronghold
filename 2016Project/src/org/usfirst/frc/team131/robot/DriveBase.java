package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveBase {
	Talon frontLeft = new Talon(PortConstants.LEFT_FRONT_TALON);
	Talon middleLeft = new Talon (PortConstants.LEFT_MIDDLE_TALON);
	Talon backLeft = new Talon (PortConstants.LEFT_BACK_TALON);
	
	Talon frontRight = new Talon (PortConstants.RIGHT_FRONT_TALON);
	Talon middleRight = new Talon (PortConstants.RIGHT_MIDDLE_TALON);
	Talon backRight = new Talon (PortConstants.RIGHT_BACK_TALON);
	
	double leftDirection = 1.0;
	double rightDirection = -1.0;
	void setSpeed (double leftSpeed, double rightSpeed) {
		frontLeft.set(leftDirection * leftSpeed);
		middleLeft.set(leftDirection * leftSpeed);
		backLeft.set(leftDirection * leftSpeed);
		
		frontRight.set(rightDirection * rightSpeed);
		middleRight.set(rightDirection * rightSpeed);
		backRight.set(rightDirection * rightSpeed);
	}
}
