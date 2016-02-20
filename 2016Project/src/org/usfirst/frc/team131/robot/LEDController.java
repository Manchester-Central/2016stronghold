 package org.usfirst.frc.team131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.Relay;

/**
 * controls the LED light system
 * @author Charles
 *
 */
public class LEDController {
	
	enum LightState { RED, GREEN, BLUE, OFF };
	
	Relay rgLights;
	Relay bLights;
	
	LightState lightState;
	
	/**
	 * the contructor, defines relay port and initializes the lights on
	 */
	public LEDController () {
    	rgLights = new Relay (PortConstants.RG_LED_RELAY_PORT);
    	bLights = new Relay (PortConstants.B_LED_RELAY_PORT);
    	lightState = LightState.OFF;
	}
	
	/**
	 * set the status for the LED's
	 */
	private void setLightStatus () {
		if (lightState == LightState.BLUE) {
			rgLights.set(Relay.Value.kOff);
			bLights.set(Relay.Value.kForward);
		} else if (lightState == LightState.RED) {
			rgLights.set(Relay.Value.kForward);
			bLights.set(Relay.Value.kOff);
		} else if (lightState == LightState.GREEN) {
			rgLights.set(Relay.Value.kReverse);
			bLights.set(Relay.Value.kOff);
		} else {
			rgLights.set(Relay.Value.kOff);
			bLights.set(Relay.Value.kOff);
		}
	}
	
	/**
	 * sets the red light
	 * @param bool (true for on, false for off)
	 */
	public void setRed () {
		lightState = LightState.RED;
		setLightStatus ();
	}
	
	/**
	 * sets the blue light
	 * @param bool (true for on, false for off)
	 */
	public void setBlue () {
		lightState = LightState.BLUE;
		setLightStatus ();
	}
	
	public void setGreen () {
		lightState = LightState.GREEN;
		setLightStatus ();
	}
	
	public void setOff () {
		lightState = LightState.OFF;
		setLightStatus ();
	}
	
	/**
	 * turns on the red flashing alarm 
	 */
	public void turnAlarmOn () {
		Date date = new Date ();
		if ((date.getTime() % 500) < 250) {
			setRed ();
		} else {
			setOff ();
		}
	}
}
