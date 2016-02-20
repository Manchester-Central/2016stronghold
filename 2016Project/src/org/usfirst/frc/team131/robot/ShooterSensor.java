package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class ShooterSensor {
	DigitalInput input;
public ShooterSensor(int port) {
	input = new DigitalInput(port);
}
 
}
