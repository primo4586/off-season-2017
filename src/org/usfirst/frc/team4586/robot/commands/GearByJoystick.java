package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.OI;
import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.GearInserter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearByJoystick extends Command {

	OI oi;
	GearInserter gearInserter;
	double speed;
	
	
    public GearByJoystick() {
        // Use requires() here to declare subsystem dependencies
// eg. requires(chassis);
    	oi = Robot.oi;
    	gearInserter = Robot.gearInserter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = oi.operatorJoystick.getRawAxis(1)*SmartDashboard.getNumber("gear max speed");
    	if(gearInserter.isInStatic())
    	{
    		if(speed>=0)
    		{
    			gearInserter.setGearMotor(speed);
    		}
    		else
    		{
    			gearInserter.setGearMotor(0);
    		}
    	}
    	else if(gearInserter.isInFloor())
    	{
    		if(speed<=0)
    		{
    			gearInserter.setGearMotor(speed);
    		}
    		else
    		{
    			
    			gearInserter.setGearMotor(0);
    		}
    	}
    	else
    	{
    		gearInserter.setGearMotor(speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
