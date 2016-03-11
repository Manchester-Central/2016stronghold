package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

/**
 * This class centers the ball in the intake
 * @author Charles
 *
 */
public class ShooterTrigger {
	
	private DigitalInput holdingSensor = new DigitalInput(PortConstants.HOLDING_SENSOR_PORT);
	
	private static final double OUTPUT_SPEED = 1.0;
	private static final double INTAKE_SPEED = -1.0;
	private Talon centeringTalon = new Talon(PortConstants.SHOOTER_TRIGGER_TALON_PORT);
	
	
	public ShooterTrigger() {
		centeringTalon.setInverted(true);
	
	}
	/**
	 * This function checks if the ball is in the sensor
	 * @return (if the ball is in the sensor) (true/false)
	 */
	public boolean isBallInSensor () {
		return !holdingSensor.get();
	}
	
	/**
	 * This function centers the ball
	 */
	public void ballCenter () {
	
		centeringTalon.set(INTAKE_SPEED);
	}
	
	/**
	 * This function reverses the ball
	 */
	public void ballReversal ()  {
		centeringTalon.set(OUTPUT_SPEED);
	}
	
	/**
	 * This function manually centers/reverses the ball
	 * @param speed (joystick input)
	 */
	public void ballCenterManual (double speed) {
		centeringTalon.set(speed);
	}
	
	/**
	 * This function fires if the ball is centered
	 */
	public void readyShot() {
		if (holdingSensor.get() == false){
			centeringTalon.set(OUTPUT_SPEED);
		}
		else {
			centeringTalon.set(0);
		}
	}
}
