package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class BallCenterMechanism {
	private static final double OUTPUT_SPEED = 1.0;
	private static final double INTAKE_SPEED = -0.5;
	private Talon centeringTalon = new Talon(PortConstants.CENTERING_TALON);
	public void ballCenter () {
	
		centeringTalon.set(INTAKE_SPEED);
	}
	public void ballreversal ()  {
		centeringTalon.set(OUTPUT_SPEED);
	}
	public void ballCenterManual (double speed) {
		centeringTalon.set(speed);
	}
}
