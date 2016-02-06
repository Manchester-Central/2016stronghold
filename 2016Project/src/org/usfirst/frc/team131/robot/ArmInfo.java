package org.usfirst.frc.team131.robot;

/** 
 * This class returns info about the arm
 * @author Charles
 *
 */
public interface ArmInfo {
	
	/**
	 * This function gets the angle
	 * @return (the angle of the arm)
	 */
	double getAngle () ;
	
	/**
	 * This function gets the wanted angle
	 * @return (the wanted angle)
	 */
	double getAngleSetpoint () ;
	
	/**
	 * This function gets the shoulder speed
	 * @return (the shoulder speed)
	 */
	double getTestShoulderSpeed () ;
	
}
