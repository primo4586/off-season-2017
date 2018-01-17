package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class AutoDrive extends Command {

	private NetworkTable nt;
	private Driver driver;
	private double distance;
	private double rotation;
	
    public AutoDrive(double distance, double rotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.driver = Robot.driver;
    	this.distance = distance;
    	this.rotation = rotation;
    	this.nt = Robot.nt;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.driver.resetGyro();
    	this.distance = nt.getNumber("distance") - this.distance;
    	this.driver.SetVisionSetPoint(this.distance);
    	this.driver.setGyroControllerSetPoint(this.rotation);
    	this.driver.visionPIDEnable();
    	this.driver.gyroPIDEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.driver.SetVisionSetPoint(this.distance);
    	this.driver.setGyroControllerSetPoint(this.rotation);
    	driver.arcadeDrive(driver.getPIDSpeed(), driver.getPIDRotation());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (driver.getbLMotor() == 0 && this.driver.getbRMotor() == 0
        		&& (this.distance == nt.getNumber("distance"))) 
        		&& (this.driver.getGyroAngle()  == this.rotation);
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	driver.visionPIDDisable();
    	driver.gyroPIDDisable();
    	driver.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driver.visionPIDDisable();
    	driver.gyroPIDDisable();
    	driver.stopMotors();
    }
}
