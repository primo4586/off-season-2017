package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.OI;
import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveByJoystick extends Command {
	OI oi;
	Driver driver;
	double speed, rotation;

	public DriveByJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		oi = Robot.oi;
		driver = Robot.driver;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (SmartDashboard.getBoolean("use gyro"))
			driveWithGyro();
		else
			driveWithoutGyro();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	private void driveWithoutGyro() {
		speed = oi.driverJoystick.getRawAxis(1);
		rotation = oi.driverJoystick.getRawAxis(4);
		if (Math.abs(speed) > 0.1)
			speed *= SmartDashboard.getNumber("maxDrivingSpeed") * SmartDashboard.getNumber("drivingDirection")
					+ oi.driverJoystick.getRawAxis(3) * 0.3 - oi.driverJoystick.getRawAxis(2) * 0.3;
		else
			speed = 0;
		if (Math.abs(rotation) > 0.1)
			rotation *= SmartDashboard.getNumber("maxDrivingSpeed") - oi.driverJoystick.getRawAxis(2) * 0.2;
		else
			rotation = 0;
		driver.arcadeDrive(speed, rotation);

	}

	private void driveWithGyro() {
		speed = oi.driverJoystick.getRawAxis(1);
		rotation = oi.driverJoystick.getRawAxis(4);
		if (Math.abs(-speed) < 0.1) {
			// TODO: delete note from next line
			// driver.gyroController.disable();
			 driver.resetGyro();
		}
		if (Math.abs(rotation) < 0.1 && Math.abs(speed) > 0.2) {

			speed *= (SmartDashboard.getNumber("maxDrivingSpeed") + oi.driverJoystick.getRawAxis(3) * 0.3
					- oi.driverJoystick.getRawAxis(2) * 0.3) * SmartDashboard.getNumber("drivingDirection");
			driver.setGyroControllerSetPoint(0);
			driver.gyroController.enable();
			driver.arcadeDrive(speed, driver.getPIDRotation());

		} else {
			driver.gyroController.disable();
			driver.resetGyro();
			if (Math.abs(speed) > 0.1)
				speed *= (SmartDashboard.getNumber("maxDrivingSpeed") + oi.driverJoystick.getRawAxis(3) * 0.3
						- oi.driverJoystick.getRawAxis(2) * 0.3) * SmartDashboard.getNumber("drivingDirection");
			else
				speed = 0;
			if (Math.abs(rotation) > 0.1)
				rotation *= SmartDashboard.getNumber("maxDrivingSpeed") - oi.driverJoystick.getRawAxis(2) * 0.3;
			else
				rotation = 0;

			driver.arcadeDrive(speed, rotation);

		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
