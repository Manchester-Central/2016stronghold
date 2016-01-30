package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;


public class DriveBase {
	public static final double ENCODER_PULSES_PER_REVOLUTION = 250.0;
	public static final double ENCODER_GEAR_RATIO = 1.0;
	
	Talon frontLeft = new Talon(PortConstants.LEFT_FRONT_TALON);
	Talon middleLeft = new Talon (PortConstants.LEFT_MIDDLE_TALON);
	Talon backLeft = new Talon (PortConstants.LEFT_BACK_TALON);
	
	Talon frontRight = new Talon (PortConstants.RIGHT_FRONT_TALON);
	Talon middleRight = new Talon (PortConstants.RIGHT_MIDDLE_TALON);
	Talon backRight = new Talon (PortConstants.RIGHT_BACK_TALON);
	
	Encoder rightEncoder = new Encoder(PortConstants.RIGHT_ENDOER_1, PortConstants.RIGHT_ENDOER_2, false, Encoder.EncodingType.k4X);
	Encoder leftEncoder = new Encoder(PortConstants.LEFT_ENCODER_1, PortConstants.LEFT_ENCODER_2, false, Encoder.EncodingType.k4X);	
	
	double leftDirection = 1.0;
	double rightDirection = -1.0;
	
	public double getRightDistance () {
		return rightEncoder.getDistance();
	}
	public double getLeftDistance () {
		return leftEncoder.getDistance();
	}
	void setSpeed (double leftSpeed, double rightSpeed) {
		frontLeft.set(leftDirection * leftSpeed);
		middleLeft.set(leftDirection * leftSpeed);
		backLeft.set(leftDirection * leftSpeed);
		
		frontRight.set(rightDirection * rightSpeed);
		middleRight.set(rightDirection * rightSpeed);
		backRight.set(rightDirection * rightSpeed);
		
		
	}
	public DriveBase() {
		// TODO Auto-generated constructor stub
		double distancePerPulse = ENCODER_GEAR_RATIO / ENCODER_PULSES_PER_REVOLUTION;
				
		leftEncoder.setMaxPeriod(0.1);
		leftEncoder.setDistancePerPulse(distancePerPulse);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setSamplesToAverage(7);
		
		rightEncoder.setMaxPeriod(0.1);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setReverseDirection(true);
		rightEncoder.setSamplesToAverage(7);
		
	}

}
