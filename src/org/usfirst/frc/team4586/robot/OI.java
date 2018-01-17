package org.usfirst.frc.team4586.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4586.robot.commands.AutoAlignByVision;
import org.usfirst.frc.team4586.robot.commands.AutoDriveByTime;
import org.usfirst.frc.team4586.robot.commands.AutoShootBytime;
import org.usfirst.frc.team4586.robot.commands.AutoShotByVision;
import org.usfirst.frc.team4586.robot.commands.CalibrateGyro;
import org.usfirst.frc.team4586.robot.commands.DecreaseGearMaxSpeed;
import org.usfirst.frc.team4586.robot.commands.ExampleCommand;
import org.usfirst.frc.team4586.robot.commands.GearToDynamic;
import org.usfirst.frc.team4586.robot.commands.GearToFeeder;
import org.usfirst.frc.team4586.robot.commands.GearToFloor;
import org.usfirst.frc.team4586.robot.commands.GearToStatic;
import org.usfirst.frc.team4586.robot.commands.IncreaseGearMaxSpeed;
import org.usfirst.frc.team4586.robot.commands.Shoot;
import org.usfirst.frc.team4586.robot.commands.ShooterInsert;
import org.usfirst.frc.team4586.robot.commands.SwitchDirection;
import org.usfirst.frc.team4586.robot.commands.relayStart;
import org.usfirst.frc.team4586.robot.subsystems.Shooter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driverJoystick;
	public Joystick operatorJoystick;
	
	// driving
	public JoystickButton invertButton;// driver
	public JoystickButton calibrateButton;// operator
	
	// shooter
	public JoystickButton shoot;// operator
	public JoystickButton ballInsert;// operator
	
	// gears
	public JoystickButton staticGear;// driver
	public JoystickButton dynamicGear;// driver
	public JoystickButton floorGear;// driver
	public JoystickButton feederGear;// driver
	public JoystickButton gearsIncreaseSpeed;// operator
	public JoystickButton gearsDecreaseSpeed;// operator
	public JoystickButton autoAlign;
	public JoystickButton ledOpen;
	
	public JoystickButton checkAuto;
//	public JoystickButton checkAutoVision;
	public JoystickButton alignByVision;
	
	public OI()
	{
		// driver stick
		driverJoystick=new Joystick(0);
		invertButton = new JoystickButton(driverJoystick, 4);
		ledOpen = new JoystickButton(driverJoystick, 6);
		autoAlign = new JoystickButton(driverJoystick, 1);
		// operator stick
		operatorJoystick = new Joystick(1);
		calibrateButton = new JoystickButton(driverJoystick, 3);
		shoot = new JoystickButton(operatorJoystick, 1);
		ballInsert = new JoystickButton(operatorJoystick, 2);
		gearsIncreaseSpeed = new JoystickButton(operatorJoystick, 8);
		floorGear = new JoystickButton(operatorJoystick, 3);
		staticGear = new JoystickButton(operatorJoystick, 5);
		gearsDecreaseSpeed = new JoystickButton(operatorJoystick, 9);
		feederGear = new JoystickButton(operatorJoystick, 6);
		dynamicGear = new JoystickButton(operatorJoystick, 4);
		
		invertButton.whenPressed(new SwitchDirection());
		calibrateButton.whenPressed(new CalibrateGyro());
		shoot.whileHeld(new Shoot());
		ballInsert.whileHeld(new ShooterInsert());
		staticGear.whenPressed(new GearToStatic());
		floorGear.whenPressed(new GearToFloor());
		dynamicGear.whenPressed(new GearToDynamic());
		feederGear.whenPressed(new GearToFeeder());
		gearsIncreaseSpeed.whenPressed(new IncreaseGearMaxSpeed());
		gearsDecreaseSpeed.whenPressed(new DecreaseGearMaxSpeed());
		autoAlign.whenPressed(new AutoAlignByVision(0));
		ledOpen.toggleWhenPressed(new relayStart());
				
		JoystickButton checkAutoVision = new JoystickButton(driverJoystick, 3);
		checkAuto = new JoystickButton(driverJoystick, 2);
	}
	
}

