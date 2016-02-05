package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Relay;

public class LEDController {
	
	Relay lights;
	
	boolean redLightOn;
	boolean blueLightOn;
	
	public LEDController () {
    	lights = new Relay (PortConstants.RELAY_PORT);
    	redLightOn = true;
    	blueLightOn = true;
	}
	
	private void setLightStatus () {
		if (redLightOn && blueLightOn) {
			lights.set (Relay.Value.kOn);
		} else if (blueLightOn) {
			lights.set(Relay.Value.kReverse);
		} else if (redLightOn) {
			lights.set(Relay.Value.kForward);
		} else {
			lights.set(Relay.Value.kOff);
		}
	}
	
	public void setRed (boolean bool) {
		redLightOn = bool;
		setLightStatus();
	}
	
	public void setBlue (boolean bool) {
		blueLightOn = bool;
		setLightStatus();
	}
	
	
}
