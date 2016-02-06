
package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

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
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		// ui display
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, center);
		ui.displayArm(arm);

		// ball sensor lights
		if (center.isBallInSensor()) {
			LED.setBlue(false);
		} else {
			LED.setBlue(true);
		}

		// Alarm button (driver)
		if (oi.driver.buttonPressed(Controller.LEFT_BUMPER)) {
			LED.turnAlarmOn();
		}

		// Drive (driver)
		drive.setSpeed(oi.driver.getLeftY(), oi.driver.getRightY());

		// Raise/lower hook (driver)
		if (oi.driver.buttonPressed(Controller.LEFT_TRIGGER)) {
			hook.lowerHook();
		} else if (oi.driver.buttonPressed(Controller.LEFT_BUMPER)) {
			hook.raiseHook();
		} else {
			hook.setHookSpeed(0);
		}

		// Climb/descend (driver)
		if (oi.driver.buttonPressed(Controller.DOWN_A_ABXY)) {
			hook.climbTower();
		} else if (oi.driver.buttonPressed(Controller.RIGHT_B_ABXY)) {
			hook.descendTower();
		} else {
			hook.setClimbSpeed(0);
		}

		// Ball centering/Shoot (operator)
		if (oi.operator.buttonPressed(Controller.START_BUTTON)) {
			center.readyShot();
		} else {
			center.ballCenter();;
		}
		
		// Shoulder arm movement (operator)
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

		// Flywheel speed presets (operator)
		if (oi.operator.buttonPressed(Controller.LEFT_X_ABXY)) {
			intakeShooter.ballShoot1();
		} else if (oi.operator.buttonPressed(Controller.UP_Y_ABXY)) {
			intakeShooter.ballShoot2();
		} else if (oi.operator.buttonPressed(Controller.RIGHT_B_ABXY)) {
			intakeShooter.ballIntake();
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
