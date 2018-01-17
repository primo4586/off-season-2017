package org.usfirst.frc.team4586.robot.subsystems;



import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Shooter extends PIDSubsystem {

	

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Jaguar pushBallToShooter;
	public Jaguar shooter;
	public Counter encoderShooter;
	public double formerRate;

	public Shooter(Jaguar pushBallToShooter, Jaguar shooter, Counter _encoderShooter) {

		super("Shooter", 0.9, 0.0, 0.1, 0.0);
		this.pushBallToShooter = pushBallToShooter;
		this.shooter = shooter;
		
		//this.encoderShooter = new Counter(shoot);
		this.encoderShooter = _encoderShooter;
		encoderShooter.setDistancePerPulse(0.25);
		this.encoderShooter.reset();

		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		formerRate = 0;
	}

	public double getEncoderShooter() {
		return encoderShooter.getDistance();
	}
	
	public void setShooterSpeed(double Speed) {
		shooter.set(Speed);
		
	}

	public int getEncoderCount()
	{
		return encoderShooter.get(); 
	}
	
	/*
	public double getShooterSpeed() {

		return shooter.get();
	}
	*/

	public void setLoadShooterSpeed(double Speed) {
		pushBallToShooter.set(Speed);
	}

	public void stopLoadShooterMotor() {

		pushBallToShooter.set(0);
	}


	/*
	 * returns the encoder's speed
	 */
	public double getSpeed() {
		double rate = encoderShooter.getRate();
		return rate;
		
	}

	public void initDefaultCommand() {

	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
//		if (encoderShooter.getRate() > 0)
//			return encoderShooter.getRate();
//		else
//			return -encoderShooter.getRate();
		return getSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

		if (output > 0) {
			shooter.pidWrite(output);
			SmartDashboard.putNumber("shooter PID output", -output);
		} else {
			shooter.pidWrite(0);
			SmartDashboard.putNumber("shooter PID output", 0);
		}
		// System.out.println(output);
	}

}
