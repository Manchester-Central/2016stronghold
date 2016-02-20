package org.usfirst.frc.team131.robot;

/**
 * sets the port numbers for various components
 * @author Charles
 *
 */
public class PortConstants {
	// Digital Output
	public static final int LEFT_FRONT_VICTOR_PORT = 0;
	public static final int LEFT_MIDDLE_VICTOR_PORT = 1;
	public static final int LEFT_BACK_VICTOR_PORT = 2;
	
	public static final int RIGHT_FRONT_VICTOR_PORT = 3;
	public static final int RIGHT_MIDDLE_VICTOR_PORT = 4;
	public static final int RIGHT_BACK_VICTOR_PORT = 5;
	
	public static final int SHOULDER_TALON_PORT = 6;
	
	public static final int SHOOTER_TRIGGER_TALON_PORT = 7;
	
	public static final int SHOOTER_TALON_PORT_1 = 8;
	public static final int SHOOTER_TALON_PORT_2 = 9;
	
	public static final int CLIMBING_TALON_PORT = 10;

	// Digital Input
	public static final int LEFT_ENCODER_PORT_A = 0;
	public static final int LEFT_ENCODER_PORT_B = 1;

	public static final int RIGHT_ENCODER_PORT_A = 2;
	public static final int RIGHT_ENCODER_PORT_B = 3;
	
	public static final int SHOOTER_RATE_SENSOR_PORT = 4;
	
	public static final int HOLDING_SENSOR_PORT = 6;
	
	  
	public static final int FL_OPTICAL_SENSOR_PORT = 7;
	public static final int FR_OPTICAL_SENSOR_PORT = 8; 
	public static final int ARM_LIMIT_SWITCH_PORT = 9;
	
	
	// Relay
	// kReverse is blue, kForward is red (relay is its own port)
	public static final int LED_RELAY_PORT = 0;
	
	// Analog
	public static final int GYRO = 0;
	public static final int ARM_POT	 = 1;
	
	// Maybe for Autonomous

//  public static final int RANGEFINDER = 2;
	
}
