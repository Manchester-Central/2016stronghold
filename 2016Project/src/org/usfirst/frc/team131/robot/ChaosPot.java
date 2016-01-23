package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class ChaosPot {
	//pot = new AnalogPotentiometer(0, 360, 30);
	AnalogInput ai = new AnalogInput(1);
	AnalogPotentiometer pot = new AnalogPotentiometer(ai, 360, 30);
	
	public double getAngle(){
		return pot.get() * 360;
	}
	
    
	//public AnalogChannel pot = new Potentiometer();
	
}
