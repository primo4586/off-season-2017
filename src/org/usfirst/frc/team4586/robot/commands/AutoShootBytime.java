package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShootBytime extends CommandGroup {

    public AutoShootBytime() {
    	addParallel(new GearToFeeder());
    	System.out.println("a");
    	addParallel(new AutoShootOnly());
    	System.out.println("b");
    	addSequential(new AutoDriveByTime(1));
    	System.out.println("c");
    	addSequential(new AutoDriveByTime(-1));
    	System.out.println("d");
    	addSequential(new AutoShooterInsert());
    	System.out.println("e");
    }
}
