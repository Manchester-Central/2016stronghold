package org.usfirst.frc.team131.robot;

public class DriverController extends Controller {

	public static final int DRIVER_CONTROLLER_PORT = 1;

	public static final int DRIVE_REVERSE = Controller.START_BUTTON;

	public static final int DECEND = Controller.LEFT_BUMPER;
	public static final int CLIMB = Controller.LEFT_TRIGGER;

	public static final int HALF_SPEED = Controller.RIGHT_BUMPER;

	public static final int BREAK = Controller.RIGHT_TRIGGER;

	// public static final int RAISE_HOOK = Controller.LEFT_BUMPER;
	// public static final int LOWER_HOOK = Controller.LEFT_TRIGGER;

	public DriverController() {
		super(DRIVER_CONTROLLER_PORT);

		// TODO Auto-generated constructor stub
	}

}
