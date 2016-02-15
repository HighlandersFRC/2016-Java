package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NavXDriveForward extends Command {
	private double speed;
	private double time;
	private double startAngle;
	private double kp = 0.03;
	private double ki = 0.0001;
	private double kd = 0;
	private PID orientation; 
	private double startTime;
	private boolean across = false;
    public NavXDriveForward(double speed, double time) {
    	this.time = time;
    	this.speed = speed;
    	orientation = new PID(kp,ki,kd);
    	orientation.setMaxOutput(.25);
    	orientation.setMinOutput(-.25);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = RobotMap.navx.getAngle();
    	orientation.setSetPoint(startAngle);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	orientation.updatePID(RobotMap.navx.getAngle());
    	RobotMap.motorLeftOne.set(-orientation.getResult() - speed);
    	RobotMap.motorLeftTwo.set(-orientation.getResult() - speed);
    	
    	RobotMap.motorRightOne.set(-orientation.getResult() + speed);
    	RobotMap.motorRightTwo.set(-orientation.getResult() + speed);
    	
    	if(RobotMap.navx.getWorldLinearAccelZ() < -.25 &&!across){
    		startTime = Timer.getFPGATimestamp();
    		time = 2;
    		across = true;
    		//speed = .2;

    	}
    	//System.out.println(orientation.getResult());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return startTime + time <= Timer.getFPGATimestamp();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.motorLeftOne.set(0);
    	RobotMap.motorLeftTwo.set(0);
    	
    	RobotMap.motorRightOne.set(0);
    	RobotMap.motorRightTwo.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
