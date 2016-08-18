package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;

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
	private Fire fire = new Fire();
	private double loadTime = 0;
	private double loadWait = .5;
	private boolean canLoad = true;
	private boolean loaded = false;
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
    		AutoLoad load = new AutoLoad();
    		load.start();
    	}
    	if(OI.fire.get()){
    		Fire fire = new Fire();
    		fire.start();
    		
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
