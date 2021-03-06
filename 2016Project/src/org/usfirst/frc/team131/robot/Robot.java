
package org.usfirst.frc.team131.robot;

import java.util.Calendar;
import java.util.HashMap;

import org.usfirst.frc.team131.robot.Controller.DPadDirection;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
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
	final String lowBarLowShotAuto = "Low Bar Low Shot";
	final String lowBarHighShotAuto = "Low Bar High Shot";
	final String forwardAuto = "Forward Auto";
	final String spyAuto = "Spy Auto";
	final String sadf = "Spinning fedora";
	final String testedForward = "testedForward";
	final String newForward = "newForward";
	String autoSelected;
	//HashMap<String, Long> timer = new HashMap<String, Long>();

	// boolean isManualMode;
	// boolean isReverseButtonPressed;

	AutoController autoController;
	SendableChooser chooser;
	OI oi;
	DriveBase drive;
	ScalingHook hook;
	IntakeShooter intakeShooter;
	ShooterTrigger shooterTrigger;
	ChaosPot chaosPot;
	ShoulderArm arm;
	ChaosDashboard ui;
	Command test;
	LEDController LED;
//	Camera cam;
	
	AnalogGyro gyro = new AnalogGyro(PortConstants.GYRO);
	DigitalInput frontLeftOpticalSensor = new DigitalInput(PortConstants.FL_OPTICAL_SENSOR_PORT);
	DigitalInput frontRightOpticalSensor = new DigitalInput(PortConstants.FR_OPTICAL_SENSOR_PORT);

	int numberOfCallings; // use for debug
	int updateCycles = 0;
	int shootState = 0;

	/**
	 * This function is run when the robot is initially booted up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		autoController = new AutoController();

		// isReverseButtonPressed = false;

		// server = CameraServer.getInstance();
		// server.setQuality(50);
		// /*
		// * the camera name (ex "cam0") can be found through the roborio web
		// * interface
		// */
		// server.startAutomaticCapture("cam0");

		oi = new OI();
		drive = new DriveBase();

		arm = new ShoulderArm();
		shooterTrigger = new ShooterTrigger();
		intakeShooter = new IntakeShooter();
		hook = new ScalingHook();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Low Bar Low Shot Auto", lowBarLowShotAuto);
		chooser.addObject("Low Bar High Shot Auto", lowBarHighShotAuto);
		chooser.addObject("Forward Autonomous", forwardAuto);
		chooser.addObject("Auto Spy", spyAuto);
		//chooser.addObject("dank fedora flips & tricks", sadf);
		chooser.addObject("testedForward", testedForward);
		chooser.addObject("newForward", newForward);
		SmartDashboard.putData("Auto choices", chooser);

		ui = new ChaosDashboard();
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, shooterTrigger);
		ui.displayArm(arm);

		// isManualMode = true;

//		cam = new Camera();
//		cam.Start();
        		
		
//		if (updateCycles % 9 == 0) {
//			cam.Capture(arm.getAngle() < 0);
//			updateCycles = 0;
//		}
		
		drive.breakSpike.setDirection(Relay.Direction.kForward);

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
		drive.resetEncoders();
		SmartDashboard.putString("Debug", "autonomousInit entry ");
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		SmartDashboard.putString("Debug", "autonomousInit exit ");
		numberOfCallings = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
//		SmartDashboard.putNumber("left tagasdaga", drive.getLeftDistanceInInches());
//		SmartDashboard.putNumber("rightt tagasdaga", drive.getRightDistanceInInches());
//		SmartDashboard.putNumber("testsdgh", (Math.abs(drive.getRightDistanceInInches()) + Math.abs(drive.getLeftDistanceInInches())) / 2);
		
		drive.breakSpike.setDirection(Relay.Direction.kForward);
		SmartDashboard.putNumber("encoder spins", drive.getLeftRotationalDistance());
		SmartDashboard.putString("Debug", "autonomousPeriodic entry ");
		// ui display
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, shooterTrigger);
		ui.displayArm(arm);
		ui.diplayDrive(drive);

		numberOfCallings++;

		if (chooser.getSelected() == forwardAuto) {
			autoController.driveFowardAuto(drive);
		} else if (chooser.getSelected() == lowBarLowShotAuto) {
			autoController.lowBarLowShotAuto(arm, drive, intakeShooter, shooterTrigger);
		} else if (chooser.getSelected() == spyAuto) {
			autoController.spyAuto(arm, drive, intakeShooter, shooterTrigger, frontLeftOpticalSensor,
					frontRightOpticalSensor);
		} else if (chooser.getSelected() == lowBarHighShotAuto) {
			autoController.lowBarHighShotAuto(arm, drive, intakeShooter, shooterTrigger);
		} else if (chooser.getSelected() == sadf) {
			autoController.turningTest(drive);
		} else if (chooser.getSelected() == newForward){
			autoController.driveHighShoot(drive, arm, intakeShooter, shooterTrigger);
		} else if (chooser.getSelected() == testedForward) {
			autoController.testedDriveForwardAuto(drive);
		} else {
			autoController.doNothingAuto(drive);
		}

		
//		if (updateCycles % 9 == 0) {
//			cam.Capture(arm.getAngle() < 0);
//			updateCycles = 0;
//		}

		// switch (autoSelected) {
		// case backwardAuto:
		// autoController.autoStateBackward(arm, drive);
		// break;
		// case forwardAuto:
		// autoController.autoStateForward(arm, drive);
		// break;
		// case spyAuto:
		// autoController.autoStateSpy(arm, drive, intakeShooter,
		// shooterTrigger, frontLeftOpticalSensor,
		// frontRightOpticalSensor);
		// break;
		// case defaultAuto:
		// default:
		// break;

		// }
		// SmartDashboard.putString("Debug", "autonomousPeriodic exit ");
		SmartDashboard.putString("autoSelection", autoSelected);
		SmartDashboard.putNumber("number of auto loops", numberOfCallings);
	}

	@Override
	public void disabledPeriodic() {
		updateCycles++;
		SmartDashboard.putString("Debug", "disabledPeriodic entry ");
		// TODO Auto-generated method stub
		super.disabledPeriodic();
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, shooterTrigger);
		ui.displayArm(arm);
		ui.diplayDrive(drive);

		
//		if (updateCycles % 9 == 0) {
//			cam.Capture(arm.getAngle() < 0);
//			updateCycles = 0;
//		}
		
		SmartDashboard.putString("Debug", "disabledPeriodic exit ");
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		//drive.setSafetyEnabled(false);
		
		//timer.put("periodic start", (long) Calendar.MILLISECOND);
		if (!oi.driver.buttonPressed(DriverController.BREAK)) {
			drive.breakSpike.setDirection(Relay.Direction.kForward);
		}
		//timer.put("spike logic passed", (long) Calendar.MILLISECOND);

		updateCycles++;
		
		/*
		if (updateCycles % 9 == 0) {
			
			cam.Capture(arm.getAngle() < 0);
			updateCycles = 0;
		}else if(updateCycles ==0){
			//cam.Free();
		}*/
		
		//timer.put("camera logic passed", (long) Calendar.MILLISECOND);

		// UI Display
		ui.displayArmPositions();
		ui.diplayShooter(intakeShooter, shooterTrigger);
		ui.displayArm(arm);
		ui.diplayDrive(drive);
		//timer.put("ui logic passed", (long) Calendar.MILLISECOND);

		/*
		 * // Ball Sensor Lights if (shooterTrigger.isBallInSensor()) { //
		 * LED.setBlue(false); } else { // LED.setBlue(true); }
		 */

		/*
		 * // Alarm Button (driver) if
		 * (oi.driver.buttonPressed(DriverController.FLASH_RED)) { //
		 * LED.turnAlarmOn(); }
		 */

		// Drive (driver)
		if (oi.driver.buttonPressed(DriverController.HALF_SPEED)) {
			drive.setSpeed(oi.driver.getLeftY() * 0.7, oi.driver.getRightY() * 0.7);
		} else {
			drive.setSpeed(oi.driver.getLeftY(), oi.driver.getRightY());
		}
		//timer.put("arm control logic passed", (long) Calendar.MILLISECOND);

		// Reverse drive direction
		// if (oi.driver.buttonPressed(DriverController.DRIVE_REVERSE) &&
		// !isReverseButtonPressed) {
		// isReverseButtonPressed = true;
		// drive.reverseDirection();
		// } else if (!oi.driver.buttonPressed(DriverController.DRIVE_REVERSE))
		// {
		// isReverseButtonPressed = false;
		// }

		// Climb/Descend Tower (driver)
		if (oi.driver.buttonPressed(DriverController.CLIMB)) {
			hook.climbTower();
		} else if (oi.driver.buttonPressed(DriverController.DECEND)) {
			hook.descendTower();
		} else {
			hook.setClimbSpeed(0);
		}
		//timer.put("climbing hook logic passsed", (long) Calendar.MILLISECOND);

		// // Set to Manual Mode
		// if (oi.operator.buttonPressed(OperatorController.AUTO_MODE)) {
		// isManualMode = false;
		// }
		// // Set to Auto Mode
		// if (oi.operator.buttonPressed(OperatorController.MANUAL_MODE)) {
		// isManualMode = true;
		// }

		// Shoulder Arm Movement (operator)

		if (oi.operator.getDPad() != DPadDirection.NONE) {
			arm.presetAngle(oi.operator.getDPad());
			arm.moveToAngle();
		} else {
			arm.setShoulderSpeed(oi.operator.getLeftY() * 0.6);
		}
		//timer.put("DPad logic passed", (long) Calendar.MILLISECOND);

		if (oi.operator.buttonPressed(OperatorController.AUTO_INTAKE)) {
			if (updateCycles % 9 == 0) {
//				cam.Capture(arm.getAngle() < 0);
				updateCycles = 0;
			}
			if (!shooterTrigger.isBallInSensor()) {
				intakeShooter.intakeShooterManual(0.7);
				shooterTrigger.ballCenter();
			} else {
				shooterTrigger.ballCenter();

				intakeShooter.intakeShooterManual(0.0);
			}

			//timer.put("auto intake logic passed", (long) Calendar.MILLISECOND);

		} else {
//			cam.Free();
			shootState = 0;
			// Ball Centering/Shoot (operator)
			if (oi.operator.buttonPressed(OperatorController.TRIGGER_OUT)) {
				// center.readyShot();
				shooterTrigger.ballCenterManual(1.0);
			} else if (oi.operator.buttonPressed(OperatorController.TRIGGER_IN)) {
				shooterTrigger.ballCenter();
			} else {
				shooterTrigger.ballCenterManual(0.0);
			}

			//timer.put("centering logic passed", (long) Calendar.MILLISECOND);

			// Flywheel Speed Presets (operator)
			if (oi.operator.buttonPressed(OperatorController.FULL_SHOT)) {
				intakeShooter.intakeShooterManual(-1.0);

				if (updateCycles % 9 == 0) {
					
//					cam.Capture(arm.getAngle() < 0);
					updateCycles = 0;
				}else if(updateCycles ==0){
					//cam.Free();
				}
				
			} else if (oi.operator.buttonPressed(OperatorController.HALF_SHOT)) {
				intakeShooter.intakeShooterManual(-0.3);
//				cam.Free();
			} else {
				intakeShooter.intakeShooterManual(oi.operator.getRightY());
	//			cam.Free();
			}
			//timer.put("flywheel logic passed", (long) Calendar.MILLISECOND);
		}
		
//		if(oi.driver.equals(DriverController.RIGHT_TRIGGER)){
//
//			if (updateCycles % 9 == 0) {
//				
//				cam.Capture(arm.getAngle() < 0);
//				updateCycles = 0;
//			}else if(updateCycles ==0){
//				//cam.Free();
//			}
//			
//			else{
//				cam.Free();
//			}
		}

		//timer.put("teleop end", (long) Calendar.MILLISECOND);

		//SmartDashboard.putData("logic timing", (Sendable) timer);

		//timer.clear();
		
		
	//}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		//(o.O)
	}

}
