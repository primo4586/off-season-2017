package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoAlignByVision extends Command {
	NetworkTable nt;
	Driver driver;
	double mTargetAngle;
	double speed;
	double rotation;
	double kP;
	double halfTime;
	int inverted;
	boolean isReset;
	double timeNow;
	
	public AutoAlignByVision(int inverted) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		nt = Robot.nt;
		driver = Robot.driver;
		this.inverted = inverted;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("init");
		mTargetAngle = nt.getNumber("angle");
		SmartDashboard.putNumber("vision angle", mTargetAngle);
		driver.resetGyro();
		isReset = false;
		speed = SmartDashboard.getNumber("Auto max speed",0)*inverted;
		if (inverted == 1)
		{
			halfTime = SmartDashboard.getNumber("time a", 7)/2;
		}
		else
		{
			halfTime = SmartDashboard.getNumber("time b", 3)/2;
		}
		setTimeout(halfTime*2);
		timeNow = Timer.getMatchTime();
		halfTime = timeNow - halfTime;
		if(speed != 0)
			kP = SmartDashboard.getNumber("KpVisionDrive");
		else
			kP = SmartDashboard.getNumber("KpVision");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("exec");
		mTargetAngle = nt.getNumber("angle");
		if (Math.abs(mTargetAngle) > 1) {
			rotation = (-mTargetAngle) * kP;
		} else {
			rotation = 0;
		}
		timeNow = Timer.getMatchTime();
		//System.out.println(timeNow + " " + halfTime + " " +isReset);
		driver.arcadeDrive(speed, rotation);
		Timer.delay(0.1);
		
	}

	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println(isTimedOut() +" "+ isReset);
		return isTimedOut() && isReset;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("ended");
		driver.stopMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		driver.stopMotors();
	}
}
