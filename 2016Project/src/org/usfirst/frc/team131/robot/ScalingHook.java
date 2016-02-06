package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

/**
 * This class represents the mechanism for climbing.
 * @author Charles
 *
 */
public class ScalingHook {
	Talon hookTalon = new Talon(PortConstants.HOOK_TALON);
	Talon climbingTalon = new Talon(PortConstants.CLIMBING_TALON);
	final double HOOK_MOVING_SPEED = 1.0;
	final double CLIMBING_SPEED = 1.0;
	
	/**
	 * This function sets the hook speed
	 * @param speed (takes input from controller)(-1 to 1)
	 */
	public void setHookSpeed (double speed) {
		hookTalon.set(speed);
	}
	
	/**
	 * This function sets the climb speed
	 * @param speed (takes input from controller) (-1 to 1)
	 */
	public void setClimbSpeed (double speed) {
		climbingTalon.set(speed);
	}
	/**
	 * This function lowers the hook
	 */
	public void lowerHook() {
		hookTalon.set(-HOOK_MOVING_SPEED);
	}
	
	/**
	 * This function raises the hook
	 */
	public void raiseHook() {
		hookTalon.set(HOOK_MOVING_SPEED);
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
