package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootByEncoder extends CommandGroup {

    public AutoShootByEncoder() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new AutoEncoderShooter(565),5); // pass auto line
    	System.out.println("a");
    	addSequential(new AutoEncoderShooter(-50),5); // goes to control square
    	System.out.println("b");
    	addParallel(new Shoot(),5); // automatic shoots
    	addSequential(new ShooterInsert(),5);
    	System.out.println("c");
    }
}
