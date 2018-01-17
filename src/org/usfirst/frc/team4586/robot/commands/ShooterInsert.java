package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterInsert extends Command {

	Shooter shoot;
	
    public ShooterInsert() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shoot = Robot.shooter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(Math.abs(shoot.getSpeed()-SmartDashboard.getNumber("Shooter desired speed"))<5)
    		shoot.setLoadShooterSpeed(-SmartDashboard.getNumber("loadShooterSpeed"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shoot.setLoadShooterSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shoot.setLoadShooterSpeed(0);
    }
    
}
