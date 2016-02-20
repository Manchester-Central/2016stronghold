package org.usfirst.frc.team131.robot;

public class OperatorController extends Controller {
	
public static final int OPERATOR_CONTROLLER_PORT = 2;
	
	public static final int STOP_SHOULDER = Controller.SELECT_BUTTON;
	
	public static final DPadDirection ANGLE_135 = Controller.DPadDirection.UP;
	public static final DPadDirection ANGLE_90 = Controller.DPadDirection.LEFT;
	public static final DPadDirection ANGLE_180 = Controller.DPadDirection.RIGHT;
	public static final DPadDirection ANGLE_0 = Controller.DPadDirection.DOWN;
	
	public static final int HALF_SHOT = Controller.UP_Y_ABXY;
	public static final int FULL_SHOT = Controller.LEFT_X_ABXY;
	
	public static final int INTAKE = Controller.RIGHT_B_ABXY;
	public static final int STOP_FLYWHEEL = Controller.DOWN_A_ABXY;
	public static final int CENTER_BALL = Controller.LEFT_TRIGGER;
	public static final int CENTER_OUTPUT = Controller.LEFT_BUMPER;
	
	public static final int ARM_UP = Controller.RIGHT_BUMPER;
	public static final int ARM_DOWN = Controller.RIGHT_TRIGGER;

	
	
	public OperatorController() {
		super(OPERATOR_CONTROLLER_PORT);
		
		
				// TODO Auto-generated constructor stub
	}
}
