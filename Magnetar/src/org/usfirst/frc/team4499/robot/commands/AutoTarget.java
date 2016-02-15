package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;
import org.usfirst.frc.team4499.robot.tools.Tegra;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTarget extends Command {
	private double kp = .0085;
	private double ki = 0;
	private double kd = 0;

	
	PID targetPosX;
	
	
    public AutoTarget() {
    	targetPosX = new PID(kp,ki,kd);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Automatic Targeting available");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(Tegra.getX() > 0){
		    	targetPosX.setSetPoint(320);
		    	targetPosX.updatePID(Tegra.getX());
		    	double power = targetPosX.getResult();
		    	RobotMap.motorLeftOne.set(power);
		    	RobotMap.motorRightOne.set(power);
		    	RobotMap.motorLeftTwo.set(power);
		    	RobotMap.motorRightTwo.set(power);
		    	System.out.println(power);
		    	if(Math.abs(320 - Tegra.getX()) < 10){
		    		System.out.println(" \n Target Locked !! \n ");
		    	}
    		}else{
    			System.out.println("No Data From Tegra");
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(320 - Tegra.getX()) < 10) && targetPosX.getResult() < .1; 
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
