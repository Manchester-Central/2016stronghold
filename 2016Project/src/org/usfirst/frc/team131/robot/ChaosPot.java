package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * This class is the wrapper for the potentiometer
 * @author Charles
 *
 */
public class ChaosPot {
	//pot = new AnalogPotentiometer(0, 360, 30);
	AnalogInput ai = new AnalogInput(PortConstants.ARM_POT);
	AnalogPotentiometer pot = new AnalogPotentiometer(ai, 3600.0, -632-910); //3600.0 , -19996
	
	
	/**
	 * This function gets the angle of the arm
	 * @return (arm angle)
	 */
	public double getAngle(){
		return -pot.get() * (90.0 / 404.0); //212
	}
	
	public double getRawAngle () {
		return pot.get();
	}
	
    
	//public AnalogChannel pot = new Potentiometer();
	
}
