package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class ChaosSpeedController implements SpeedController {

	private Victor firstVictor;
	private Victor secondVictor;
	private Victor thirdVictor;
	private boolean isInverted = false;
	private boolean isDisabled = false;
	private double power = 0;

	public ChaosSpeedController(int port1, int port2, int port3) {
		firstVictor = new Victor(port1);
		secondVictor = new Victor(port2);
		thirdVictor = new Victor(port3);

	}

	@Override
	public void pidWrite(double output) {
		firstVictor.pidWrite(output);
		secondVictor.pidWrite(output);
		thirdVictor.pidWrite(output);

	}

	@Override
	public double get() {

		return power;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		this.set(speed);

	}

	@Override
	public void set(double speed) {
		power = speed;

		firstVictor.set(speed);
		secondVictor.set(speed);
		thirdVictor.set(speed);

	}

	@Override
	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;
		firstVictor.setInverted(isInverted);
		secondVictor.setInverted(isInverted);
		thirdVictor.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return isInverted;
	}

	@Override
	public void disable() {
		this.set(0);
		isDisabled = true;
		
		firstVictor.disable();
		secondVictor.disable();
		thirdVictor.disable();

	}

}
