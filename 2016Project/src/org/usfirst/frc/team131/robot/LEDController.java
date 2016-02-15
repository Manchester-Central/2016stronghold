 package org.usfirst.frc.team131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.Relay;

/**
 * controls the LED light system
 * @author Charles
 *
 */
public class LEDController {
	
	Relay lights;
	
	boolean redLightOn;
	boolean blueLightOn;
	
	/**
	 * the contructor, defines relay port and initializes the lights on
	 */
	public LEDController () {
    	lights = new Relay (PortConstants.LED_RELAY_PORT);
    	redLightOn = true;
    	blueLightOn = true;
	}
	
	/**
	 * set the status for the LED's
	 */
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
	
	/**
	 * sets the red light
	 * @param bool (true for on, false for off)
	 */
	public void setRed (boolean bool) {
		redLightOn = bool;
		setLightStatus();
	}
	
	/**
	 * sets the blue light
	 * @param bool (true for on, false for off)
	 */
	public void setBlue (boolean bool) {
		blueLightOn = bool;
		setLightStatus();
	}
	
	/**
	 * turns on the red flashing alarm 
	 */
	public void turnAlarmOn () {
		Date date = new Date ();
		if ((date.getTime() % 500) < 250) {
			setRed (true);
		} else {
			setRed (false);
		}
	}
}
