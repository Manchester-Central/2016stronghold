package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This class is the wrapper for the digital optical sensor
 * @author Charles
 *
 */
public class AlignmentSensor {
	DigitalInput opticalSensor;

	
	/**
	 * This is the constructor
	 * @param channel (Represents port number)
	 */
	public AlignmentSensor(int channel) {
		opticalSensor = new DigitalInput(channel);

	}
	
	/**
	 * This function returns the sensor state (boolean)
	 * @return (the state)
	 */
	public boolean getState() {
		return opticalSensor.get();

	}
}
