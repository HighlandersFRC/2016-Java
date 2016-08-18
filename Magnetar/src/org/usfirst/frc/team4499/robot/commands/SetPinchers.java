package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPinchers extends Command {
	private double value;
	private double startTime;
	private double time;
    public SetPinchers(double value,double time) {
    	this.value = value;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.pincherMotor.set(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return startTime + time < Timer.getFPGATimestamp();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.pincherMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
