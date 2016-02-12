
package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.CameraServer;
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
	final String customAuto = "My Auto";
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

	/**
	 * This function is run when the robot is initially booted up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
		
		oi = new OI();
		drive = new DriveBase();

		arm = new ShoulderArm();
		center = new BallCenterMechanism();
		intakeShooter = new IntakeShooter();
		hook = new ScalingHook();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);

		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, center);
		ui.displayArm(arm);

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
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			if (drive.getRightDistanceInInches() <= 24 && drive.getleftDistanceInInches() <= 24) {
				drive.setSpeed(0.2, 0.2);
			} else {
				drive.setSpeed(0, 0);
			}
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
			LED.setBlue(false);
		} else {
			LED.setBlue(true);
		}

		// Alarm Button (driver)
		if (oi.driver.buttonPressed(Controller.LEFT_BUMPER)) {
			LED.turnAlarmOn();
		}

		// Drive (driver)
		drive.setSpeed(oi.driver.getLeftY(), oi.driver.getRightY());

		// Raise/Lower Hook (driver)
		if (oi.driver.buttonPressed(Controller.LEFT_TRIGGER)) {
			hook.lowerHook();
		} else if (oi.driver.buttonPressed(Controller.LEFT_BUMPER)) {
			hook.raiseHook();
		} else {
			hook.setHookSpeed(0);
		}

		// Climb/Descend Tower (driver)
		if (oi.driver.buttonPressed(Controller.DOWN_A_ABXY)) {
			hook.climbTower();
		} else if (oi.driver.buttonPressed(Controller.RIGHT_B_ABXY)) {
			hook.descendTower();
		} else {
			hook.setClimbSpeed(0);
		}

		// Ball Centering/Shoot (operator)
		if (oi.operator.buttonPressed(Controller.START_BUTTON)) {
			center.readyShot();
		} else {
			center.ballCenter();
			;
		}

		// Shoulder Arm Movement (operator)
		if (oi.operator.buttonPressed(Controller.SELECT_BUTTON)) {
			arm.stopShoulderArm();
		} else if (oi.operator.buttonPressed(Controller.RIGHT_BUMPER)) {
			arm.shoulderManualAngle(true);
		} else if (oi.operator.buttonPressed(Controller.RIGHT_TRIGGER)) {
			arm.shoulderManualAngle(false);
		} else {
			arm.presetAngle(oi.operator.getDPad());
		}
		arm.moveToAngle();

		// Flywheel Speed Presets (operator)
		if (oi.operator.buttonPressed(Controller.LEFT_X_ABXY)) {
			intakeShooter.ballShoot1();
		} else if (oi.operator.buttonPressed(Controller.UP_Y_ABXY)) {
			intakeShooter.ballShoot2();
		} else if (oi.operator.buttonPressed(Controller.RIGHT_B_ABXY)) {
			intakeShooter.ballIntake();
		} else if (oi.operator.buttonPressed(Controller.DOWN_A_ABXY)) {
			intakeShooter.flywheelStop();
		}

		intakeShooter.updateFlywheelSpeed();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
