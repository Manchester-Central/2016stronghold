package org.usfirst.frc.team131.robot;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.DigitalInput;

public class ShooterSensor {
	DigitalInput input;
	LinkedList<Long> ticks = new LinkedList<Long>();
	boolean previousTick = false;
	private double rate = 0;
	
	public ShooterSensor(int port) {
		input = new DigitalInput(port);
		(new TickUpdater()).start();
	}
	
	/**
	 * Rotations Per Minute
	 * @return
	 */
	public double getRate () {
		return rate;
	}

	private class TickUpdater extends Thread {
		@Override
		public void run() {
			while (isAlive()) {
				boolean currentTick = input.get();
				if (currentTick && !previousTick) {
					ticks.add(System.currentTimeMillis());
					if (ticks.size() > 5) {
						ticks.removeFirst();
					}
				}
				if (ticks.size() == 0) {
					rate = 0;
				} else {
					double duration = (double) (System.currentTimeMillis() - ticks.getFirst());
					duration = (duration / 1000L) / 60L; //convert to minutes
					if (duration == 0 ) {
						rate = 0;
					} else {
						rate = ticks.size() / duration;
					}
				}
				previousTick = currentTick;
			}
		}
	}
}
