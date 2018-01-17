package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShootByVision extends CommandGroup {

    public AutoShootByVision() {
    	addParallel(new GearToFeeder());
    	System.out.println("a");
    	addParallel(new AutoShootOnly());
    	System.out.println("b");
    	addSequential(new AutoDriveByTime(SmartDashboard.getNumber("Auto max speed")));
    	System.out.println("c");
    	addSequential(new AutoDriveByTime(-SmartDashboard.getNumber("Auto max speed")));
    	System.out.println("d");
    	addSequential(new AutoAlignByVision(0));
    	System.out.println("e");
    	addSequential(new AutoShooterInsert());
    	System.out.println("f");
    }
}
