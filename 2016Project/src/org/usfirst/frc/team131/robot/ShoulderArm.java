package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Talon;

public class ShoulderArm {
	private Talon leftShoulderTalon = new Talon(PortConstants.LEFT_SHOULDER_TALON);
	private Talon rightShoulderTalon = new Talon(PortConstants.RIGHT_SHOULDER_TALON);
	public final double DOWN_SPEED = 1.0;
	public final double UP_SPEED = -1.0;
	public void shoulderManual (double speed) {
		rightShoulderTalon.set(speed);
		leftShoulderTalon.set(speed);
	}
	public void presetAngle (Controller.DPadDirection direction) {
		switch(direction) {
		case RIGHT:
			System.out.println("right");
			break;
		case LEFT:
			System.out.println("left");
			break;
		case UP:
			System.out.println("up");
			break;
		case DOWN:
			System.out.println("down");
			break;
		default:
			System.out.println("none");
			break;
		}
	}
}
