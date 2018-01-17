package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearInserter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor gearMotor;
	DigitalInput staticGearSwitch;
	DigitalInput floorGearSwitch;
	DigitalInput dynamicGearSwitch;
	DigitalInput feederGearSwitch;
	
	public GearInserter(Victor gearMotor, DigitalInput staticGearSwitch, DigitalInput floorGearSwitch, DigitalInput dynamicGearSwitch, DigitalInput feederGearSwitch) 
	{
		this.gearMotor = gearMotor;
		this.staticGearSwitch = staticGearSwitch;
		this.floorGearSwitch = floorGearSwitch;
		this.dynamicGearSwitch = dynamicGearSwitch;
		this.feederGearSwitch = feederGearSwitch;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public double getGearMotor() {
		return gearMotor.get();
	}

	public void setGearMotor(double speed) {
		this.gearMotor.set(speed);
	}

	public boolean isInStatic() {
		return !staticGearSwitch.get();
	}
	
	public boolean isInFeeder() {
		return !feederGearSwitch.get();
	}

	public boolean isInFloor() {
		return !floorGearSwitch.get();
	}

	public boolean isInDynamic() {
		return !dynamicGearSwitch.get();
	}
}

