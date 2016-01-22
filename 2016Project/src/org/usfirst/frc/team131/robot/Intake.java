package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class Intake {
	private static final double OUTPUT_SPEED = -0.5;
	private static final double INTAKE_SPEED = 1.0;
	private Talon intakeTalon = new Talon(PortConstants.INTAKE_TALON);
	public void ballIntake () {
		intakeTalon.set(INTAKE_SPEED);
		
	}
	public void ballOutput ()  {
		intakeTalon.set(OUTPUT_SPEED);
	}
}