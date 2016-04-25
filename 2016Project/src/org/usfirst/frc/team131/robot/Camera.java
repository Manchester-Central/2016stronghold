package org.usfirst.frc.team131.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;

public class Camera {
//	int session;
//	Image frame;
//	Image dest;
//
//	public Camera() {
//		CameraServer.getInstance().setQuality(15);
//		CameraServer.getInstance().setSize(2);
//		/*
//		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		dest = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		*/
//		//frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
//		// the camera name (ex "cam0") can be found through the roborio web
//		// interface
//		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//		NIVision.IMAQdxConfigureGrab(session);
//		
//	}
//
//	public void Start() {
//		NIVision.IMAQdxStartAcquisition(session);
//	}
//
//	
//	public void Free(){
//		frame.free();
//		
//	}
//	
//	public void Capture(boolean flipImage) {
//		//frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		dest = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		
//		try {
//			NIVision.IMAQdxGrab(session, frame, 0);
//			// NIVision.imaqDrawShapeOnImage(frame, frame, rect,
//			// DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
//			// Image source = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
//			// this.camera.getImage(source);
//			if (flipImage) {
//				
//				NIVision.imaqFlip(dest, frame, FlipAxis.CENTER_AXIS);
//
//				CameraServer.getInstance().setImage(dest);
//				//dest.free();
//
//			} else {
//				CameraServer.getInstance().setImage(frame);
//			}
//
//			/** robot code here! **/
//			//Timer.delay(0.005); // wait for a motor update time
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public void Shutdown() {
//		NIVision.IMAQdxStopAcquisition(session);
//	}

}
