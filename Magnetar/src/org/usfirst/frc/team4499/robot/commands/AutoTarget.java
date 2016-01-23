package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.tools.PID;
import org.usfirst.frc.team4499.robot.tools.Tegra;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTarget extends Command {
	
	AutoGimbal gimbal;
    public AutoTarget(AutoGimbal gimbal) {
    	this.gimbal = gimbal;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println((Tegra.getX()) + " , " + (Tegra.getY()));
    	if(Tegra.getX() != -1 && Tegra.getY() != -1){
    	if(Tegra.getY() > 240){
    		gimbal.setPitchValue(gimbal.getPitchValue() + .05);
    		//System.out.println("Up");
    		
    	}
    	if(Tegra.getY() < 240){
    		gimbal.setPitchValue(gimbal.getPitchValue() - .05);
    		//System.out.println("Down");
    		
    	}
    	if(Tegra.getX() > 320){
    		gimbal.setYawValue(gimbal.getYawValue() + .05);
    		//System.out.println("left");
    		
    	}
    	if(960 - Tegra.getX() < 320){
    		gimbal.setYawValue(gimbal.getYawValue() - .05);
    		//System.out.println("right");
    		
    	}	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    
    protected void interrupted() {
    }
}
