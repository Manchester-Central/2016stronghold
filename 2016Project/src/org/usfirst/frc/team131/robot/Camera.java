package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {

	public void robotInit() {
		CameraServer server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
	}

}
