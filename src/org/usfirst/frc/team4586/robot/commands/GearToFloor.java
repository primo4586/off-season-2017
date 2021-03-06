package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.GearInserter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearToFloor extends Command {

	GearInserter gearInserter;
	
    public GearToFloor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearInserter = Robot.gearInserter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(5);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!gearInserter.isInFloor())
    		gearInserter.setGearMotor(SmartDashboard.getNumber("gear max speed"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (isTimedOut()||gearInserter.isInFloor());
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearInserter.setGearMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	gearInserter.setGearMotor(0);
    }
}
