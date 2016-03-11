package org.usfirst.frc.team131.robot;

/**
 * This class modifies the angle speed of the arm
 * @author Charles
 *
 */
public class AngleSpeedModifier {
	private final double DEAD_BAND = 9;
	private final double MIN_SPEED = 0.0;

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

		if (angleDisplacement <= DEAD_BAND) {//was  <= Deadband (DK 8:43 PM 3/3/2016)
			wantedSpeed = wantedSpeed * (angleDisplacement / DEAD_BAND);
			wantedSpeed = Math.max(MIN_SPEED, Math.abs(wantedSpeed));
			if (wantedSpeed < 0.1) {
				wantedSpeed = 0;
			}
		}
		wantedSpeed = wantedSpeed * speedDirection;
		if (currentAngle > targetAngle) {
			wantedSpeed = -wantedSpeed;

		}
		return (wantedSpeed);

	}

}
