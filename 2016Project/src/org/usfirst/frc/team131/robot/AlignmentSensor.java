package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class AlignmentSensor {
	DigitalInput opticalSensor;

	public AlignmentSensor(int channel) {
		opticalSensor = new DigitalInput(channel);

	}

	public boolean getState() {
		return opticalSensor.get();

	}
}
