package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

/**
 * This class represents the mechanism for climbing.
 * @author Charles
 *
 */
public class ScalingHook {
	Talon climbingTalon = new Talon(PortConstants.CLIMBING_TALON_PORT);
	final double HOOK_MOVING_SPEED = 1.0;
	final double CLIMBING_SPEED = 1.0;
	

	/**
	 * This function sets the climb speed
	 * @param speed (takes input from controller) (-1 to 1)
	 */
	public void setClimbSpeed (double speed) {
		climbingTalon.set(speed);
	}

	
	/**
	 * This function sets the climbing motor 
	 */
	public void climbTower() {
		climbingTalon.set(CLIMBING_SPEED);
	}
	
	/**
	 * This function reverses the climbing motor
	 */
	public void descendTower() {
		climbingTalon.set(-CLIMBING_SPEED);
	}
	
}
