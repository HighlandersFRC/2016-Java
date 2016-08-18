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
	private double kp = .01;
	private double ki = 0;
	private double kd = 0;

	private double kpY = 0.01;
	private double kiY;
	private double kdY;
	PID targetPosX;
	PID targetPosY;
	
	
    public Target() {
    	targetPosX = new PID(kp,ki,kd);
    	targetPosY = new PID(kpY,kiY,kdY);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Automatic Targeting available");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.Target.get()){
    		if(Tegra.getX() > 0){
    			RobotMap.intakePiston.set(RobotMap.intakeOut);
    			Pinchers.setPoint = -500;
		    	targetPosX.setSetPoint(320);
		    	targetPosX.updatePID(Tegra.getX());
		    	targetPosY.setSetPoint(180);
		    	targetPosY.updatePID(Tegra.getY());
		    	double power = targetPosX.getResult();
		    	if(power > 0.1){
		    		power = 0.3;
		    	}
		    	else if(power < -0.1){
		    		power = -0.3;
		    	}
		    	double yPower = targetPosY.getResult();
		    	if(yPower > 0.1){
		    		yPower = 0.3;
		    	}
		    	else if(yPower < -0.1){
		    		yPower = -0.3;
		    	}
		    	RobotMap.motorLeftOne.set( power + yPower);
		    	RobotMap.motorRightOne.set(power -yPower);
		    	RobotMap.motorLeftTwo.set(power +yPower);
		    	RobotMap.motorRightTwo.set(power - yPower);
		    	//System.out.println(power);
		    	//if(Math.abs(320 - Tegra.getX()) < 10){
		    	//	System.out.println(" \n Target Locked !! \n ");
		    	//}
    		}else{
    			System.out.println("No Data From Tegra");
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
