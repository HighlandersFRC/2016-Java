package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.DCMotor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {
	private DoubleSolenoid intakePiston;
	private DCMotor intakeWheels;
	private double power = 1;
    public Intake(DoubleSolenoid intakePiston, DCMotor intakeMotor) {
    	this.intakePiston = intakePiston;
    	this.intakeWheels = intakeMotor;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.intakeUp.get()){
    		intakePiston.set(RobotMap.intakeIn);
    	}else if(OI.intakeDown.get()){
    		intakePiston.set(RobotMap.intakeOut);
    	}
    	else if(OI.intake.get()){
    		intakeWheels.set(power);
    	}
    	else if(OI.intakeOut.get()){
    		intakeWheels.set(-power);
    	}
    	else{
    		intakeWheels.set(0);
    	}
    	if(OI.duck.get()){
    		intakePiston.set(RobotMap.intakeOut);
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
