package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PistonSet extends Command {
	private DoubleSolenoid.Value setPosition;
	private DoubleSolenoid piston;
	private double startTime;
	private double waitTime;
	private boolean positionSet = false;
    public PistonSet(DoubleSolenoid piston, DoubleSolenoid.Value setPosition, double waitTime) {
       this.piston = piston;
       this.setPosition = setPosition;
       this.startTime = Timer.getFPGATimestamp();
       this.waitTime = waitTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(piston.get() == setPosition){
    		positionSet = true;
    	}
    	piston.set(setPosition);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return startTime + waitTime <= Timer.getFPGATimestamp() || positionSet;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
