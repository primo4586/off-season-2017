
package org.usfirst.frc.team4586.robot;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4586.robot.commands.AutoDriveStraight;
import org.usfirst.frc.team4586.robot.commands.AutoGear;
import org.usfirst.frc.team4586.robot.commands.AutoShootByVision;
import org.usfirst.frc.team4586.robot.commands.AutoShootBytime;
import org.usfirst.frc.team4586.robot.commands.DriveByJoystick;
import org.usfirst.frc.team4586.robot.commands.ExampleCommand;
import org.usfirst.frc.team4586.robot.commands.GearByJoystick;
import org.usfirst.frc.team4586.robot.subsystems.Driver;
import org.usfirst.frc.team4586.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4586.robot.subsystems.GearInserter;
import org.usfirst.frc.team4586.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static Driver driver;
	public static Shooter shooter;
	public static GearInserter gearInserter;
	public static OI oi;
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static double current; // variable for the total current
	public static double d1current; // variable for 1st motor current, pdp 0 
	public static double d2current; // variable for 2nd motor current, pdp 3
	public static double d3current; // variable for 3rd motor current, pdp 12
	public static double d4current; // variable for 4th motor current, pdp 15
	public static NetworkTable nt;
//	CameraServer server;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
			nt =  NetworkTable.getTable("imgProc");	
			RobotMap.init();
			driver = new Driver(RobotMap.bRMotor, RobotMap.fRMotor, RobotMap.bLMotor, RobotMap.fLMotor, RobotMap.driveEncoderLeft, RobotMap.driveEncoderRight, RobotMap.gyro);
			driver.calibrateGyro();
			shooter = new Shooter(RobotMap.shooter, RobotMap.insertBall, RobotMap.shooterEncoder);
			gearInserter = new GearInserter(RobotMap.gearMotor, RobotMap.staticGearSwitch, RobotMap.floorGearSwitch, RobotMap.dynamicGearSwitch, RobotMap.feederGearSwitch);
			oi = new OI();
			smartDashboardInit();
//			chooser.addDefault("Vision shoot Auto", new AutoShootByVision());
			chooser.addObject("Time shoot Auto", new AutoShootBytime());
			chooser.addObject("Auto gear", new AutoGear());
			chooser.addDefault("Time drive Auto", new AutoDriveStraight());
			SmartDashboard.putData("Auto mode", chooser);
			
			
			//CameraServer server = CameraServer.getInstance();
			//server.startAutomaticCapture();
			
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(1280, 720);
		}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		smartDashboardPeriodic();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new DriveByJoystick());
		Scheduler.getInstance().add(new GearByJoystick());
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (oi.checkAuto.get()) {
			Scheduler.getInstance().add(new AutoShootBytime());
		}
//		if (oi.checkAutoVision.get()) {
//			Scheduler.getInstance().add(new AutoShootByVision());
//		}
		smartDashboardPeriodic();
		current = pdp.getTotalCurrent();
		d1current = pdp.getCurrent(0);
		d2current = pdp.getCurrent(3);
		d3current = pdp.getCurrent(12);
		d4current = pdp.getCurrent(15);
			
		//driver.distanceLeftEncoder();
		//driver.distanceRightEncoder();
		
		//System.out.println("dist right " + driver.getDistanceRight());
		//System.out.println("dist left " + driver.getDistanceLeft());
		//System.out.println("rate "+ shooter.getSpeed());
		Scheduler.getInstance().run();
		
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void smartDashboardInit() {
		SmartDashboard.putNumber("maxDrivingSpeed",0.8);
		SmartDashboard.putNumber("drivingDirection",1);
		SmartDashboard.putBoolean("use gyro", false);
		SmartDashboard.putNumber("loadShooterSpeed", 0.1);
		SmartDashboard.putBoolean("is in static", gearInserter.isInStatic());
		SmartDashboard.putBoolean("is in dynamic", gearInserter.isInDynamic());
		SmartDashboard.putBoolean("is on floor", gearInserter.isInFloor());
		SmartDashboard.putBoolean("is on feeder", gearInserter.isInFeeder());
		SmartDashboard.putBoolean("is in holder", RobotMap.gearInHolderSwitch.get());
		SmartDashboard.putNumber("gear max speed", 0.65);
		SmartDashboard.putNumber("gyro Angle", driver.getGyroAngle());
		//SmartDashboard.putNumber("gyro p", 0);
		//SmartDashboard.putNumber("gyro i", 0);
		//SmartDashboard.putNumber("gyro d", 0);
		SmartDashboard.putNumber("Shooter rate", 0);
		SmartDashboard.putNumber("Shooter desired speed",31.0);
		SmartDashboard.putNumber("Auto shooter desired speed",32.0);
		SmartDashboard.putData("shooter PID controller", shooter.getPIDController());
		//SmartDashboard.putBoolean("shoot digi", RobotMap.shoot.get());
		SmartDashboard.putNumber("shooter speed", 1);
		SmartDashboard.putNumber("shoot dist", shooter.getEncoderShooter());
		SmartDashboard.putNumber("counter", 0);
		SmartDashboard.putNumber("Auto max speed", -0.6);
		SmartDashboard.putNumber("time", 10);
		SmartDashboard.putNumber("time a", 7);
		SmartDashboard.putNumber("time b", 2);
		SmartDashboard.putNumber("shoot time", 5);
		SmartDashboard.putNumber("Kp", 0.2);
		SmartDashboard.putNumber("KpVision", 0.065);
		SmartDashboard.putNumber("KpVisionDrive", 0.15);
	}
	
	public void smartDashboardPeriodic()
	{
		SmartDashboard.putBoolean("is in static", gearInserter.isInStatic());
		SmartDashboard.putBoolean("is in dynamic", gearInserter.isInDynamic());
		SmartDashboard.putBoolean("is on floor", gearInserter.isInFloor());
		SmartDashboard.putBoolean("is on feeder", gearInserter.isInFeeder());
		SmartDashboard.putBoolean("is in holder", RobotMap.gearInHolderSwitch.get());
		SmartDashboard.putNumber("gyro Angle", driver.getGyroAngle());
		SmartDashboard.putNumber("total current", pdp.getTotalCurrent());
		SmartDashboard.putNumber("1 current", pdp.getCurrent(0));
		SmartDashboard.putNumber("2 current", pdp.getCurrent(3));
		SmartDashboard.putNumber("3 current", pdp.getCurrent(12));
		SmartDashboard.putNumber("4 current", pdp.getCurrent(15));
		//System.out.println("Encoder" + shooter.getSpeed());
		SmartDashboard.putNumber("Shooter rate", shooter.getSpeed());
		//SmartDashboard.putBoolean("shoot digi", RobotMap.moveR.get());
		SmartDashboard.putNumber("shoot dist", shooter.getEncoderShooter());
		SmartDashboard.putNumber("counter", shooter.getEncoderCount());
	}
	
}
