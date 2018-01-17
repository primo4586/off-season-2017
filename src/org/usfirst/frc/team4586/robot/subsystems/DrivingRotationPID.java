package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivingRotationPID implements PIDOutput {

	private double rotation;

	public DrivingRotationPID() {
		rotation = 0;
	}

	public double getRotation() {
		return rotation;
	}

	public void updateRotation(double rotation) {
		this.rotation = rotation;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.rotation = output;

	}

}
