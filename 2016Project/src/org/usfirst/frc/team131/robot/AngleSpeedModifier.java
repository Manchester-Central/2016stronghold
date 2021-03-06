package org.usfirst.frc.team131.robot;

/**
 * This class modifies the angle speed of the arm
 * @author Charles
 *
 */
public class AngleSpeedModifier {
	private final double DEAD_BAND = 0.5; //needs testing calibration
	private final double MIN_SPEED = 0.30;
	private final double PROPORTIONAL_GAIN = 0.04;
	
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

		if (angleDisplacement >= DEAD_BAND) {//was  <= Deadband (DK 8:43 PM 3/3/2016)
			wantedSpeed = Math.min(wantedSpeed, (angleDisplacement * PROPORTIONAL_GAIN));
			wantedSpeed = Math.max(MIN_SPEED, Math.abs(wantedSpeed));
		}else{
			wantedSpeed = 0;
		}
		wantedSpeed = wantedSpeed * speedDirection;
		if (currentAngle > targetAngle) {
			wantedSpeed = -wantedSpeed;

		}
		return (wantedSpeed);

	}

}
