
package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	arm = new ShoulderArm();
    	chaosPot = new ChaosPot ();
    	center = new BallCenterMechanism();
    	intakeShooter = new IntakeShooter();
    	hook = new ScalingHook();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        oi = new OI();
        drive = new DriveBase ();
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	drive.setSpeed(oi.driver.getLeftY(),oi.driver.getRightY() );
    	if (oi.operator.buttonPressed(Controller.LEFT_TRIGGER)) {
    		hook.lowerHook();
    	} else if (oi.operator.buttonPressed(Controller.LEFT_BUMPER)) {
    		hook.raiseHook();
    	} else {
    		hook.setSpeed(oi.operator.getLeftY());
    	}
    	
    	if (oi.operator.buttonPressed(Controller.DOWN_A_ABXY)) {
    		intakeShooter.ballIntake();
    	} else if (oi.operator.buttonPressed(Controller.RIGHT_B_ABXY)) {
    		intakeShooter.ballShoot();
    	} else {
    		intakeShooter.intakeShooterManual(oi.operator.getLeftX());
    	}
    	
    	if (oi.operator.buttonPressed(Controller.LEFT_X_ABXY)) {
    		center.ballCenter();
    	} else if (oi.operator.buttonPressed(Controller.UP_Y_ABXY)) {
    		center.ballreversal();
    	}
    	if (oi.operator.buttonPressed(Controller.RIGHT_BUMPER)){
    		arm.shoulderManual(arm.UP_SPEED);
    	} else if (oi.operator.buttonPressed(Controller.RIGHT_TRIGGER)) {
    		arm.shoulderManual(arm.DOWN_SPEED);
    	} else {
    		arm.presetAngle(oi.operator.getDPad());
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
