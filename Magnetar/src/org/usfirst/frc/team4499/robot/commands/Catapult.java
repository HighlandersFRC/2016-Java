package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Catapult extends Command {
	private DoubleSolenoid catapult;
	private DoubleSolenoid release;
	private DoubleSolenoid intake;
	
	private static Value off = DoubleSolenoid.Value.kOff;
	private static Value forward = DoubleSolenoid.Value.kForward;
	private static Value reverse = DoubleSolenoid.Value.kReverse;
	
	private AutoLoad load = new AutoLoad();
	private Fire fire = new Fire();
    public Catapult( DoubleSolenoid catapult, DoubleSolenoid release, DoubleSolenoid intake) {
        this.catapult = catapult;
        this.release = release;
        this.intake = intake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	catapult.set(off);
    	release.set(off);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.load.get()){
    		//catapult.set(reverse);
    		//intake.set(forward);
    		fire.cancel();
    		load.start();
    		
    	}
    	else if(OI.prime.get()){
    		catapult.set(reverse);
    		fire.cancel();
    		load.cancel();
    	}
    	if(OI.fire.get()){
    		fire.start();
    		
    	}
    	else if(OI.release.get()){
    		release.set(reverse);
    		fire.cancel();
    		load.cancel();
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
