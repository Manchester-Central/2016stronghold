package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * This class intakes and shoots game pieces
 * 
 * @author Charles
 *
 */
public class IntakeShooter {

	private double DEAD_BAND = 2.0;
	private double RATE_TO_POWER = 0.01; // NEED TO CHANGE
	private double MAX_CORRECTION = 0.1;

	private Encoder shooterEncoder = new Encoder(PortConstants.SHOOTER_ENCODER_PORT_A, PortConstants.SHOOTER_ENCODER_PORT_B);

	private static final double SHOOTER_RANGE = 5.0;
	private double targetSpeed = 0;

	private static final double OUTPUT_SPEED_1 = 1.0;
	private static final double OUTPUT_SPEED_2 = 0.5;
	private static final double INTAKE_SPEED = -0.5;
	private Talon shooterTalon1 = new Talon(PortConstants.SHOOTER_TALON_PORT_1);
	private Talon shooterTalon2 = new Talon(PortConstants.SHOOTER_TALON_PORT_2);

	/**
	 * This function gets the speed of the shooter
	 * 
	 * @return (speed of shooter)
	 */
	public double getSpeed() {
		return shooterEncoder.getRate();
	}

	/**
	 * This function sets the speed for intaking the ball
	 */
	public void ballIntake() {
		targetSpeed = INTAKE_SPEED;
		// intakeShooterTalon.set(INTAKE_SPEED);
	}

	/**
	 *This function sets one of the speeds for shooting the ball 
	 */
	public void ballShoot1() {
		targetSpeed = OUTPUT_SPEED_1;
		// intakeShooterTalon.set(OUTPUT_SPEED_1);
	}
	
	/**
	 * This function sets the another speed for shooting the ball
	 */
	public void ballShoot2() {
		targetSpeed = OUTPUT_SPEED_2;
		// intakeShooterTalon.set(OUTPUT_SPEED_2);
	}

	/**
	 * This function stops the flywheel
	 */
	public void flywheelStop() {
		targetSpeed = 0;
	}

	/**
	 * This function sets the speed of the flywheel to the current target speed
	 */
	public void updateFlywheelSpeed() {
		shooterTalon1.set(targetSpeed);
		shooterTalon2.set(targetSpeed);
	}

	/**
	 * This function sets the intake shooter speed manually
	 * @param speed (the speed that is manually set)
	 */
	public void intakeShooterManual(double speed) {
		shooterTalon1.set(speed);
		shooterTalon2.set(speed);
	}

	/**
	 * This function checks that the motors are going at the wanted speed
	 * @param targetSpeed (the wanted speed)
	 * @return (whether or not it is going at the wanted speed)
	 */
	public boolean checkShooterSpeed(double targetSpeed) {
		if (targetSpeed > shooterEncoder.getRate() - SHOOTER_RANGE
				&& targetSpeed < shooterEncoder.getRate() + SHOOTER_RANGE) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function gradually puts the shooter up to speed
	 * @param wantedSpeed (the wanted speed)
	 */
	public void shooterUpToSpeed(float wantedSpeed) {
		targetSpeed = wantedSpeed;
		if (shooterEncoder.getRate() >= wantedSpeed - DEAD_BAND
				&& shooterEncoder.getRate() <= wantedSpeed + DEAD_BAND) { 
			shooterTalon1.set(shooterTalon1.get());
			shooterTalon2.set(shooterTalon2.get());
			// If shooter rate is acceptable
		} else if (shooterEncoder.getRate() > wantedSpeed + DEAD_BAND) { 
			// If  shooter rate is too high
			double correction = (wantedSpeed - shooterEncoder.getRate()) * RATE_TO_POWER;
			if (correction < -MAX_CORRECTION) {
				correction = -MAX_CORRECTION;
			}
			shooterTalon1.set(correction + shooterTalon1.get());
			shooterTalon2.set(correction + shooterTalon2.get());
		} else if (shooterEncoder.getRate() < wantedSpeed - DEAD_BAND) { 
			// If shooter rate is too low
			double correction = (wantedSpeed - shooterEncoder.getRate()) * RATE_TO_POWER;
			if (correction > MAX_CORRECTION) {
				correction = MAX_CORRECTION;
			}
			shooterTalon1.set(correction + shooterTalon1.get());
			shooterTalon2.set(correction + shooterTalon2.get());
		}
	}

}
