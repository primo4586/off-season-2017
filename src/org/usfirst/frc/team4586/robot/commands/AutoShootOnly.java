package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShootOnly extends Command {

	private Shooter shooter;
	
    public AutoShootOnly() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooter = Robot.shooter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(SmartDashboard.getNumber("shoot time")+SmartDashboard.getNumber("time b")+SmartDashboard.getNumber("time a"));
    	shooter.setSetpoint(SmartDashboard.getNumber("Auto shooter desired speed"));
    	shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.shooter.setSetpoint(SmartDashboard.getNumber("Auto shooter desired speed"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.shooter.setSetpoint(0);
    	this.shooter.disable();
    	this.shooter.setShooterSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.shooter.setSetpoint(0);
    	this.shooter.disable();
    	this.shooter.setShooterSpeed(0);
    }
}
