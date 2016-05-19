package org.usfirst.frc.team131.robot.autonomous;

import java.util.ArrayList;
import java.util.List;

public class AutonomousStepController {
	private int currentIndex = 0;
	private List <AutonomousStep> steps;
	public AutonomousStepController(AutonomousMode mode) {
		switch (mode){
		default:
		case DO_NOTHING:
			steps = null;
			break;
		case DRIVE_FORWARD:
			steps = new ArrayList<AutonomousStep> ();
			
			break; 
		}
		
	}
	
	
	public void takeStep(){
		if (steps  != null){
			if (currentIndex < steps.size()){
				AutonomousStep currentStep = steps.get(currentIndex);
				if(currentStep.isDone()){
					currentStep.finish();
					currentIndex ++ ; 
					
				}
				else {
					currentStep.step();
				}
			}
		}
		
	}

	public enum AutonomousMode {
		DO_NOTHING,
		DRIVE_FORWARD
	
	}
}
