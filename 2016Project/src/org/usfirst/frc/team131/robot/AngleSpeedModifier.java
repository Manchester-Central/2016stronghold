package org.usfirst.frc.team131.robot;

public class AngleSpeedModifier {
	private final double DEAD_BAND = 10;
	private final double MIN_SPEED = 0.2;

	public double adjustSpeed(double currentAngle, double targetAngle, double wantedSpeed) {
		double speedDirection = Math.abs(wantedSpeed)/wantedSpeed;
		wantedSpeed = Math.abs(wantedSpeed);
		double angleDisplacement = Math.abs(currentAngle - targetAngle);

		if (angleDisplacement <= DEAD_BAND) {
			wantedSpeed = wantedSpeed * (angleDisplacement / DEAD_BAND);
			wantedSpeed = Math.max(MIN_SPEED, Math.abs(wantedSpeed));
		}
		wantedSpeed = wantedSpeed * speedDirection;
		if (currentAngle > targetAngle) {
			wantedSpeed = -wantedSpeed;

		}
		return (wantedSpeed);

	}

}
