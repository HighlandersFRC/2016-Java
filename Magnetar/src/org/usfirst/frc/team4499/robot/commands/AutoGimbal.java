package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.OI;
import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoGimbal extends Command {
	double cameraYawValue;
	double cameraPitchValue;
	@Override
	protected void initialize() {
		cameraYawValue = .5;
		cameraPitchValue = .25;
	}

	@Override
	protected void execute() {
		
    	RobotMap.cameraPitch.set(cameraPitchValue);
    	RobotMap.cameraYaw.set(cameraYawValue);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	public void setYawValue(double degrees){
		cameraYawValue = degrees;// / 180.0;
	}
	public void setPitchValue(double degrees){
		cameraPitchValue = degrees;// / 180.0;
	}
	public double getYawValue(){
		return cameraYawValue ;//* 180;
	}
	public double getPitchValue(){
		return cameraPitchValue;// * 180;
	}

}
