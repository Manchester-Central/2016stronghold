package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class ScalingHook {
	Talon hookTalon = new Talon(PortConstants.HOOK_TALON);
	double hookMovingSpeed = 1.0;
	public void setSpeed (double speed) {
		hookTalon.set(speed);
	}
	public void lowerHook() {
		hookTalon.set(-hookMovingSpeed);
	}
	public void raiseHook() {
		hookTalon.set(hookMovingSpeed);
	}
}
