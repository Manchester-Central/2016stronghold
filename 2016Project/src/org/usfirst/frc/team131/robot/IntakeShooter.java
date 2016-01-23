package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class IntakeShooter {
	private static final double OUTPUT_SPEED = 1.0;
	private static final double INTAKE_SPEED = -0.5;
	private Talon intakeShooterTalon = new Talon(PortConstants.INTAKE_SHOOTER_TALON);
	public void ballIntake () {
	
		intakeShooterTalon.set(INTAKE_SPEED);
	}
	public void ballShoot ()  {
		intakeShooterTalon.set(OUTPUT_SPEED);
	}
	public void intakeShooterManual (double speed) {
		intakeShooterTalon.set(speed);
	}
}
