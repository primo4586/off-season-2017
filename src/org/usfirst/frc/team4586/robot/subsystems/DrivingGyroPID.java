package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DrivingGyroPID implements PIDSource {

	public AnalogGyro drivingGyro;

	public DrivingGyroPID(AnalogGyro gyro) {
		// TODO Auto-generated constructor stub
		this.drivingGyro = gyro;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return drivingGyro.getAngle();
	}

}
