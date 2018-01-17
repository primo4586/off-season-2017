package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Driver extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Jaguar bRMotor;
	private Jaguar fRMotor;
	private Jaguar bLMotor;
	private Jaguar fLMotor;
	private Counter driveEncoderLeft;
	private int distanceLeft;
	private Counter driveEncoderRight;
	private int distanceRight;
	private AnalogGyro gyro;
	private RobotDrive robotDrive;
	public PIDController gyroController;
	private DrivingGyroPID gyroSource;
	private DrivingRotationPID rotation;
	private PIDController visionController;
	private DrivingVisionPID visionSource;
	private DrivingSpeedPID speed;

	public Driver(Jaguar bRMotor, Jaguar fRMotor, Jaguar bLMotor, Jaguar fLMotor, Counter driveEncoderLeft,
			Counter driveEncoderRight, AnalogGyro gyro) {
		this.bRMotor = bRMotor;
		this.fRMotor = fRMotor;
		this.bLMotor = bLMotor;
		this.fLMotor = fLMotor;
		this.distanceLeft = 0;
		this.distanceRight = 0;
		this.driveEncoderLeft = driveEncoderLeft;
		this.driveEncoderRight = driveEncoderRight;
		this.driveEncoderRight.setDistancePerPulse(0.15);
		this.driveEncoderLeft.setDistancePerPulse(0.15);

		this.gyro = gyro;

		this.robotDrive = new RobotDrive(this.fLMotor, this.bLMotor, this.fRMotor, this.bRMotor);
		this.robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		this.robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		this.robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
		this.robotDrive.setInvertedMotor(MotorType.kRearRight, true);

		this.rotation = new DrivingRotationPID();
		this.speed = new DrivingSpeedPID();
		this.gyroSource = new DrivingGyroPID(this.gyro);
		this.visionSource = new DrivingVisionPID();
		// TODO: fix pid controller
		// gyroController = new PIDController(SmartDashboard.getNumber("gyro
		// p"),SmartDashboard.getNumber("gyro i"),SmartDashboard.getNumber("gyro
		// d"), gyroSource, rotation);
		this.gyroController = new PIDController(0, 0, 0, gyroSource, rotation);
		this.gyroController.setAbsoluteTolerance(1);

		this.visionController = new PIDController(0, 0, 0, visionSource, speed);
		this.gyroController.setAbsoluteTolerance(5);
		gyroController.startLiveWindowMode();
		LiveWindow.run();

	}

	public double getPIDRotation() {
		return rotation.getRotation();
	}

	public void setGyroControllerSetPoint(double setPoint) {
		this.gyroController.setSetpoint(setPoint);
	}

	public double getDistanceLeft() {
		return driveEncoderLeft.getDistance();
	}

	public void resetDistanceLeft() {
		this.distanceLeft = 0;
		this.driveEncoderLeft.reset();
	}

	public double getDistanceRight() {
		return driveEncoderRight.getDistance();
	}

	public void resetDistanceRight() {
		this.distanceRight = 0;
		this.driveEncoderRight.reset();
	}

	public void distanceRightEncoder() {
		if (this.bRMotor.get() < 0) {
			this.distanceRight -= this.driveEncoderRight.getDistance();
		} else {
			this.distanceRight += this.driveEncoderRight.getDistance();
		}
		this.driveEncoderRight.reset();
	}

	public void distanceLeftEncoder() {
		if (this.bLMotor.get() < 0) {
			this.distanceLeft -= this.driveEncoderLeft.getDistance();
		} else {
			this.distanceLeft += this.driveEncoderLeft.getDistance();
		}
		this.driveEncoderLeft.reset();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void resetEncoders()
	{
		this.driveEncoderLeft.reset();
		this.driveEncoderRight.reset();
	}
	
	public double getbRMotor() {
		return bRMotor.get();
	}

	public void setbRMotor(double speed) {
		this.bRMotor.set(-speed);
	}

	public double getfRMotor() {
		return fRMotor.get();
	}

	public void setfRMotor(double speed) {
		this.fRMotor.set(-speed);
	}

	public double getbLMotor() {
		return bLMotor.get();
	}

	public void setbLMotor(double speed) {
		this.bLMotor.set(speed);
	}

	public double getfLMotor() {
		return fLMotor.get();
	}

	public void setfLMotor(double speed) {
		this.fLMotor.set(speed);
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public void calibrateGyro() {
		this.gyro.calibrate();
	}

	public void resetGyro() {
		this.gyro.reset();
	}

	public void setRight(double speed) {
		this.bRMotor.set(speed);
		this.fRMotor.set(speed);
	}

	public void setLeft(double speed) {
		this.bLMotor.set(speed);
		this.fLMotor.set(speed);
	}

	public void stopMotors() {
		this.bLMotor.set(0);
		this.fLMotor.set(0);
		this.bRMotor.set(0);
		this.fRMotor.set(0);
	}

	public void arcadeDrive(double speed, double rotation) {
		this.robotDrive.arcadeDrive(speed, rotation);
	}

	public void drive(double speed, double curve) {
		this.robotDrive.drive(speed, curve);
	}
	
	public void SetVisionSetPoint(double setPoint) {
		this.visionController.setSetpoint(setPoint);
	}

	public void visionPIDEnable() {
		this.visionController.enable();
	}

	public void visionPIDDisable() {
		this.visionController.disable();
	}

	public double getPIDSpeed() {
		return this.speed.getSpeed();
	}

	public void gyroPIDEnable() {
		this.gyroController.enable();
	}

	public void gyroPIDDisable() {
		this.gyroController.disable();
	}
	
	public double getPIDRotaion() {
		return this.rotation.getRotation();
	}
}
