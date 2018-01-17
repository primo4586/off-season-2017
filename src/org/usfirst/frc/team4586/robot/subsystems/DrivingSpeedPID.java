package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivingSpeedPID implements PIDOutput {
	
	private double speed;

	public DrivingSpeedPID() {
		speed = 0;
	}

	public double getSpeed() {
		return speed;
	}

	public void updateSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		speed = output*SmartDashboard.getNumber("Auto max speed");
	}

}
