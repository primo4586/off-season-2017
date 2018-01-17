package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGear extends CommandGroup {

    public AutoGear() {
        addSequential(new AutoAlignByVision(1));
        addSequential(new GearToFloor());
        addSequential(new AutoAlignByVision(-1));
    }
}
