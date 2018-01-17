package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveByTime extends Command {
	double speed;
	Driver driver;
	double rotation;
	double time;
	
	public AutoDriveByTime(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		driver = Robot.driver;
		this.speed = speed;
		rotation = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//driver.drive(speed, rotation);
		driver.resetGyro();
		if(speed < 0) {
			speed = -SmartDashboard.getNumber("Auto max speed");
			time = SmartDashboard.getNumber("time b");
		} else {
			speed = SmartDashboard.getNumber("Auto max speed");
			time = SmartDashboard.getNumber("time a");
		}
		setTimeout(time);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Math.abs(driver.getGyroAngle()) > 1) {
			rotation = -driver.getGyroAngle() * SmartDashboard.getNumber("Kp");
		} else {
			rotation = 0;
		}
		driver.arcadeDrive(speed, rotation);
		Timer.delay(0.04);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		driver.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		driver.arcadeDrive(0, 0);
	}
}
