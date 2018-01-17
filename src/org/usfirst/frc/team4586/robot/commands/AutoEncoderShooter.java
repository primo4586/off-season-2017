package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoEncoderShooter extends Command {

	Driver driver;
	double distance;
	boolean backwards;
	
    public AutoEncoderShooter(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driver = Robot.driver;
    	backwards = false;
    	this.distance = distance;
    	if(distance < 0)
    		backwards = true;
    	this.distance = Math.abs(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driver.resetEncoders();
    	driver.setGyroControllerSetPoint(0);
    	driver.gyroPIDEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(backwards){
    			driver.arcadeDrive(-0.5, driver.getPIDRotaion());
    		} else {
    			driver.arcadeDrive(0.5, driver.getPIDRotaion());
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(distance) <= driver.getDistanceLeft();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driver.stopMotors();
    	driver.gyroPIDDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driver.stopMotors();
    	driver.gyroPIDDisable();
    }
}
