package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.GearInserter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearToFeeder extends Command {
	GearInserter gearInserter;
	boolean passedUnder;
	
    public GearToFeeder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearInserter = Robot.gearInserter;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(9);
    	passedUnder = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = -SmartDashboard.getDouble("gear max speed");
    	if(!gearInserter.isInFeeder() || !passedUnder){
    		if(!this.passedUnder)
    		{
    			if(this.gearInserter.isInFloor()||this.gearInserter.isInDynamic()){
    				this.passedUnder=true;
    			} else {
    				gearInserter.setGearMotor(-speed);
    			}
    		} else {
				if (this.gearInserter.isInStatic()){
					this.passedUnder = false;
				} else {
					gearInserter.setGearMotor(speed);
				}
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut()||(gearInserter.isInFeeder() && passedUnder);
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
