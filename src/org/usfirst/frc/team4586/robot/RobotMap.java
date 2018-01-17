package org.usfirst.frc.team4586.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	static Jaguar bRMotor;
	static Jaguar fRMotor;
	static Jaguar bLMotor;
	static Jaguar fLMotor;
	static Counter driveEncoderLeft;
	static Counter driveEncoderRight;
	static AnalogGyro gyro;
	
	static Jaguar shooter;
	static Jaguar insertBall;
	static Counter shooterEncoder;
	
	static DigitalInput shoot;
	static DigitalInput moveR;
	
	static Victor gearMotor;
	static DigitalInput staticGearSwitch;
	static DigitalInput floorGearSwitch;
	static DigitalInput dynamicGearSwitch;
	static DigitalInput feederGearSwitch;
	static DigitalInput gearInHolderSwitch;
	
	public static Relay ledRelay;
	public static void init()
	{
		//spare victor: 5
		bRMotor= new Jaguar(6);
		fRMotor= new Jaguar(4);
		bLMotor= new Jaguar(7);
		fLMotor= new Jaguar(3);
		
		driveEncoderRight = new Counter(7);
		//driveEncoderRight = new Counter(moveR);
		//driveEncoderLeft.setUpSource(new DigitalInput(0));
		
		driveEncoderLeft = new Counter(8);
//		driveEncoderLeft.setDownSource(new DigitalInput(8));
		//driveEncoderLeft.setUpSource(new DigitalInput(1));
		
		gyro = new AnalogGyro(0);
		
		shooter = new Jaguar(2);
		
		insertBall = new Jaguar(1);
		//TODO: changed shooter port from 5 to 9
		//TODO: 2 next lines changed to note
		shoot = new DigitalInput(9);
		shooterEncoder = new Counter(shoot);
//		shooterEncoder = new Counter(9);
				
		gearMotor = new Victor(0);
		
		staticGearSwitch = new DigitalInput(6);
		floorGearSwitch = new DigitalInput(2);
		dynamicGearSwitch = new DigitalInput(3);
		feederGearSwitch = new DigitalInput(4);
		gearInHolderSwitch = new DigitalInput(1);
		ledRelay = new Relay(0);
	}
}
