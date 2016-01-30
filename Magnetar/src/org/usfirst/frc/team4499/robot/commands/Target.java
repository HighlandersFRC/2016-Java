package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;
import org.usfirst.frc.team4499.robot.tools.Tegra;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Target extends Command {
	private double kp = .009;
	private double ki = 0;
	private double kd = 0;
	
	PID targetPosX;
	
	
    public Target() {
    	targetPosX = new PID(kp,ki,kd);
    	targetPosX.setMaxOutput(.2);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Automatic Targeting available");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.Target.get()){
    		if(Tegra.getX() > 0){
		    	targetPosX.setSetPoint(320);
		    	targetPosX.updatePID(Tegra.getX());
		    	
		    	RobotMap.motorLeftOne.set(targetPosX.getResult());
		    	RobotMap.motorRightOne.set(targetPosX.getResult());
		    	RobotMap.motorLeftTwo.set(targetPosX.getResult());
		    	RobotMap.motorRightTwo.set(targetPosX.getResult());
		    	System.out.println(targetPosX.getResult());
		    	if(Math.abs(320 - Tegra.getX()) < 10){
		    		System.out.println(" /n Target Locked !! /n ");
		    	}
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
