package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

/**
 * This class is the drive base
 * @author Charles
 *
 */
public class DriveBase {
	public static final double ENCODER_PULSES_PER_REVOLUTION = 360.0;
	public static final double ENCODER_GEAR_RATIO = 1.0;
	
	Victor frontLeft = new Victor(PortConstants.LEFT_FRONT_VICTOR);
	Victor middleLeft = new Victor (PortConstants.LEFT_MIDDLE_VICTOR);
	Victor backLeft = new Victor (PortConstants.LEFT_BACK_VICTOR);
	
	Victor frontRight = new Victor (PortConstants.RIGHT_FRONT_VICTOR);
	Victor middleRight = new Victor (PortConstants.RIGHT_MIDDLE_VICTOR);
	Victor backRight = new Victor (PortConstants.RIGHT_BACK_VICTOR);
	
	Encoder rightEncoder = new Encoder(PortConstants.RIGHT_ENCODER_1, PortConstants.RIGHT_ENCODER_2, false, Encoder.EncodingType.k4X);
	Encoder leftEncoder = new Encoder(PortConstants.LEFT_ENCODER_1, PortConstants.LEFT_ENCODER_2, false, Encoder.EncodingType.k4X);	
	
	double leftDirection = -1.0;
	double rightDirection = 1.0;
	
	public void reverseDirection () {
		rightDirection *= -1.0;
		leftDirection *= -1.0;
	}
	
	/**
	 * This function gets the distance traveled of the right side motors
	 * @return (distance traveled)
	 */
	public double getRightDistance () {
		return rightEncoder.getDistance();
	}
	
	/**
	 * This function gets the distance traveled of the left side motors
	 * @return (distance traveled)
	 */
	public double getLeftDistance () {
		return leftEncoder.getDistance();
	}
	
	/**
	 * This function sets the speed of the drive
	 * @param leftSpeed (speed of left motors)
	 * @param rightSpeed (speed of right motors
	 */
	void setSpeed (double leftSpeed, double rightSpeed) {
		frontLeft.set(leftDirection * leftSpeed);
		middleLeft.set(leftDirection * leftSpeed);
		backLeft.set(leftDirection * leftSpeed);
		
		frontRight.set(rightDirection * rightSpeed);
		middleRight.set(rightDirection * rightSpeed);
		backRight.set(rightDirection * rightSpeed);
	}
	
	/**
	 * This is the constructor for the encoders
	 */
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
