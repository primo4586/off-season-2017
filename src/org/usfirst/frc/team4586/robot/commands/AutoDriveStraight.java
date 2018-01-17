package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveStraight extends CommandGroup {

    public AutoDriveStraight() {
    	addParallel(new GearToFeeder());
    	System.out.println("a");
    	addSequential(new AutoDriveByTime(SmartDashboard.getNumber("Auto max speed")));
    	System.out.println("c");
    }
}
