package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Test extends CommandGroup{
	public void test (DriveBase drive) {
		drive.setSpeed(0.2, 0.2);
	}
}
