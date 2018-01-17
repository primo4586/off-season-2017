package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.OI;
import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {

	private OI oi;
	private Shooter shooter;
	private double speed;

	public Shoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		shooter = Robot.shooter;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		shooter.setSetpoint(SmartDashboard.getNumber("Shooter desired speed"));
		shooter.enable();
//		speed = SmartDashboard.getNumber("shooter speed");
//		shooter.setShooterSpeed(speed);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.setSetpoint(SmartDashboard.getNumber("Shooter desired speed"));
//		shooter.setShooterSpeed(SmartDashboard.getNumber("shooter speed"));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
//		 shooter.setShooterSpeed(0);
		
		shooter.setSetpoint(0);
		shooter.disable();
		
		shooter.setShooterSpeed(0);
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		shooter.setSetpoint(0);
		shooter.disable();
		shooter.setShooterSpeed(0);
	}
}
