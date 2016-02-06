package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class IntakeShooter {
	private Encoder shooterEncoder = new Encoder(PortConstants.SHOOTER_ENCODER_1, PortConstants.SHOOTER_ENCODER_2);
	
	private static final double SHOOTER_RANGE = 5.0;
	private double targetSpeed = 0;
	
	private static final double OUTPUT_SPEED_1 = 1.0; 
	private static final double OUTPUT_SPEED_2 = 2.0;
	private static final double INTAKE_SPEED = -0.5;
	private Talon intakeShooterTalon = new Talon(PortConstants.INTAKE_SHOOTER_TALON);
	
	public double getSpeed () {
		return shooterEncoder.getRate();
	}
	
	public void ballIntake () {
		targetSpeed = INTAKE_SPEED;
		//intakeShooterTalon.set(INTAKE_SPEED);
	}
	public void ballShoot1 ()  {
		targetSpeed = OUTPUT_SPEED_1;
		//intakeShooterTalon.set(OUTPUT_SPEED_1);
	}
	public void ballShoot2 (){
		targetSpeed = OUTPUT_SPEED_2;
		//intakeShooterTalon.set(OUTPUT_SPEED_2);
	}
	public void flywheelStop (){
		targetSpeed = 0;
	}
	public void updateFlywheelSpeed(){
		intakeShooterTalon.set(targetSpeed);
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
