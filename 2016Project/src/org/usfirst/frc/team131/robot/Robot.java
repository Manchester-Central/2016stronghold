
package org.usfirst.frc.team131.robot;

import org.usfirst.frc.team131.robot.Controller.DPadDirection;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String backwardAuto = "Backward Auto";
	final String forwardAuto = "Forward Auto";
	final String spyAuto = "Spy Auto";
	boolean isManualMode;
	AutoController autoController;
	String autoSelected;
	SendableChooser chooser;
	OI oi;
	DriveBase drive;
	ScalingHook hook;
	IntakeShooter intakeShooter;
	BallCenterMechanism center;
	ChaosPot chaosPot;
	ShoulderArm arm;
	ChaosDashboard ui;
	LEDController LED;
	CameraServer server;
	boolean isReverseButtonPressed;
	AnalogGyro gyro = new AnalogGyro (PortConstants.GYRO);
	DigitalInput frontOpticalSensorA = new DigitalInput(PortConstants.OPTICAL_SENSOR_PORT_A);
	DigitalInput frontOpticalSensorB = new DigitalInput(PortConstants.OPTICAL_SENSOR_PORT_B);
	
	/**
	 * This function is run when the robot is initially booted up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		autoController = new AutoController ();
		
		isReverseButtonPressed = false;
		
        //server = CameraServer.getInstance();
        //server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");
		
		oi = new OI();
		drive = new DriveBase();

		arm = new ShoulderArm();
		center = new BallCenterMechanism();
		intakeShooter = new IntakeShooter();
		hook = new ScalingHook();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Backward Auto", backwardAuto);
		chooser.addObject("Forward Autonomous", forwardAuto);
		SmartDashboard.putData("Auto choices", chooser);

		ui = new ChaosDashboard();
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, center);
		ui.displayArm(arm);

		isManualMode = true;
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		
		System.out.println("Auto selected: " + autoSelected);
	}
	

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

		// ui display
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, center);
		ui.displayArm(arm);

		switch (autoSelected) {
		case backwardAuto:
			autoController.autoStateBackward(arm, drive);
			break;
		case forwardAuto:
			autoController.autoStateForward(arm, drive);
			break;
		case spyAuto:
			autoController.autoStateSpy(arm, drive, intakeShooter, center, frontOpticalSensorA, frontOpticalSensorB);
			break;
		case defaultAuto:
		default:
			autoController.autoStateDefault(drive);
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		// UI Display
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, center);
		ui.displayArm(arm);

		// Ball Sensor Lights
		if (center.isBallInSensor()) {
			//LED.setBlue(false);
		} else {
			//LED.setBlue(true);
		}

		// Alarm Button (driver)
		if (oi.driver.buttonPressed(DriverController.FLASH_RED)) {
			//LED.turnAlarmOn();
		}

		// Drive (driver)
		drive.setSpeed(oi.driver.getLeftY(), oi.driver.getRightY());
		
		// Reverse drive direction
		if (oi.driver.buttonPressed(DriverController.DRIVE_REVERSE) && !isReverseButtonPressed) {
			isReverseButtonPressed = true;
			drive.reverseDirection();
		} else if (!oi.driver.buttonPressed(DriverController.DRIVE_REVERSE)) {
			isReverseButtonPressed = false;
		}


		// Climb/Descend Tower (driver)
		if (oi.driver.buttonPressed(DriverController.CLIMB)) {
			hook.climbTower();
		} else if (oi.driver.buttonPressed(DriverController.DECEND)) {
			hook.descendTower();
		} else {
			hook.setClimbSpeed(0);
		}

		if (oi.operator.buttonPressed(OperatorController.AUTO_MODE)){
			isManualMode = false;
		}
			
		if (oi.operator.buttonPressed(OperatorController.MANUAL_MODE)){
			isManualMode = true;
		}
		
		// Ball Centering/Shoot (operator)
		if (oi.operator.buttonPressed(OperatorController.CENTER_OUTPUT)) {
			//center.readyShot();
			center.ballCenterManual(0.5);
		} else if (oi.operator.buttonPressed(OperatorController.CENTER_BALL)){
	
			center.ballCenter();
			
		}
		else {
			center.ballCenterManual(0.0);
		}

		// Shoulder Arm Movement (operator)
		if (isManualMode == false){
			if (oi.operator.buttonPressed(OperatorController.STOP_SHOULDER)) {
				arm.stopShoulderArm();
			} else {
				arm.presetAngle(oi.operator.getDPad());
				arm.moveToAngle();
			}
		}
		else {
				arm.setShoulderSpeed(oi.operator.getLeftY());
			}
		
		// Flywheel Speed Presets (operator)
		if (isManualMode == false) {
			if (oi.operator.buttonPressed(OperatorController.FULL_SHOT)) {
				intakeShooter.ballShoot1();
				intakeShooter.updateFlywheelSpeed();
			} else if (oi.operator.buttonPressed(OperatorController.HALF_SHOT)) {
				intakeShooter.ballShoot2();
				intakeShooter.updateFlywheelSpeed();
			}
			else { 
				intakeShooter.intakeShooterManual(oi.operator.getRightY());
			}
		}
		else {
	
			intakeShooter.intakeShooterManual(oi.operator.getRightY());		
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
