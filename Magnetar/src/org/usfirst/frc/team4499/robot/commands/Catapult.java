package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Catapult extends Command {
	private DoubleSolenoid rightPiston;
	private DoubleSolenoid leftPiston;
	private DoubleSolenoid release;
	
	private static Value off = DoubleSolenoid.Value.kOff;
	private static Value forward = DoubleSolenoid.Value.kForward;
	private static Value reverse = DoubleSolenoid.Value.kReverse;
	

    public Catapult( DoubleSolenoid right, DoubleSolenoid left, DoubleSolenoid release) {
        this.rightPiston = right;
        this.leftPiston = left;
        this.release = release;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rightPiston.set(off);
    	leftPiston.set(off);
    	release.set(off);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.load.get()){
    		leftPiston.set(reverse);
    		rightPiston.set(reverse);
    	}
    	else if(OI.prime.get()){
    		leftPiston.set(forward);
    		rightPiston.set(forward);
    	}
    	if(OI.fire.get()){
    		release.set(reverse);
    	}
    	else if(OI.release.get()){
    		release.set(reverse);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
   
    
}
