package org.usfirst.frc.team4586.robot.subsystems;

import org.usfirst.frc.team4586.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DrivingVisionPID implements PIDSource {

	NetworkTable nt;
	boolean toShoot;
	
	public DrivingVisionPID() {
		nt = Robot.nt;
		
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
		if(toShoot){
			return nt.getNumber("distanceShoot");
		}
		return nt.getNumber("distanceGear");
	}

}
