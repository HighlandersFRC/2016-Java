package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pinchers extends Command {
	private PID pinchers;
	private double kp = 0.0005;
	private double ki ;//= 0.00001;
	private double kd;
	public static double setPoint;
    public Pinchers() {
    	pinchers = new PID(kp,ki,kd);
    	pinchers.setMaxOutput(.25);
    	pinchers.setMinOutput(-.25);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	pinchers.updatePID(RobotMap.pincherMotor.getEncPosition());
    	double power = 0;
    	if(pinchers.getResult() > 0){
    		power = 0.3;
    	}
    	else if(pinchers.getResult() < 0){
    		power = -.3;
    	
    	}
    	else{
    		power = 0;
    	}
    	RobotMap.pincherMotor.set(-power);
    	RobotMap.pincherMotor.set(-pinchers.getResult());
    	//System.out.println(RobotMap.pincherMotor.getEncPosition());
    	
    	pinchers.setSetPoint(setPoint);
    	
    	
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
