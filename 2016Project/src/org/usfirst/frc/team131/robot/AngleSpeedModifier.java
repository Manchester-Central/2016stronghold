package org.usfirst.frc.team131.robot;

/**
 * This class modifies the angle speed of the arm
 * @author Charles
 *
 */
public class AngleSpeedModifier {
	private final double DEAD_BAND = 3;
	private final double MIN_SPEED = 0.2;

	/**
	 * This function adjusts the speed of the angle
	 * @param currentAngle (current angle)
	 * @param targetAngle (target angle)
	 * @param wantedSpeed (wanted speed)
	 * @return (power that should be returned to the motor)
	 */
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
