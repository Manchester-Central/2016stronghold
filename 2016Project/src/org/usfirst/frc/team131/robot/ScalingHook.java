package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class ScalingHook {
	Talon hookTalon = new Talon(PortConstants.HOOK_TALON);
	Talon climbingTalon = new Talon(PortConstants.CLIMBING_TALON);
	final double HOOK_MOVING_SPEED = 1.0;
	final double CLIMBING_SPEED = 1.0;
	public void setSpeed (double speed) {
		hookTalon.set(speed);
	}
	public void lowerHook() {
		hookTalon.set(-HOOK_MOVING_SPEED);
	}
	public void raiseHook() {
		hookTalon.set(HOOK_MOVING_SPEED);
	}
	public void climbTower() {
		climbingTalon.set(CLIMBING_SPEED);
	}
	public void descendTower() {
		climbingTalon.set(-CLIMBING_SPEED);
	}
}
