package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class IntakeShooter {
	private Encoder shooterEncoder = new Encoder(PortConstants.SHOOTER_ENCODER_1, PortConstants.SHOOTER_ENCODER_2);
	
	private static final double SHOOTER_RANGE = 5.0;
	
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
	public boolean checkShooterSpeed (double targetSpeed){
		if (targetSpeed > shooterEncoder.getRate()- SHOOTER_RANGE && targetSpeed < shooterEncoder.getRate()+ SHOOTER_RANGE){
			return true;
		}
		else {
			return false;
		}
	}
}
