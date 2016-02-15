package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.tools.PID;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	private double kI = 0;
	private double kP = 0.03;
	private double kD = 0;
	private double target = 0;
	private int whichWay = 0;
	Preferences prefs;
	PID orientation = new PID(kP,kI,kD);
	private boolean absolute = false;
	public Turn (double degrees, boolean absolute){
		orientation.setMaxOutput(.4);
		orientation.setMinOutput(-.4);
		orientation.setContinuous(true);
		while(degrees > 360){
			degrees = degrees - 360;
			
		}
		while(degrees < -360){
			degrees = degrees + 360;
		}
		whichWay = (int)(degrees /Math.abs(degrees)); // sets which way to neg or pos 1
		System.out.println("Target set to: " +  degrees);
		if(!absolute){
			target = degrees + RobotMap.navx.getYaw(); 
		}
		else{
			target = degrees;
		}
		
	}
	
	@Override
	protected void initialize() {	
		//RobotMap.navx.zeroYaw();
		orientation.setSetPoint(target);
	}
	
	
	

	@Override
	protected void execute() {

		orientation.updatePID(adjustScale(RobotMap.navx.getYaw()));
    	
    	RobotMap.motorLeftOne.set(-orientation.getResult());
    	RobotMap.motorLeftTwo.set(-orientation.getResult());
    	
    	RobotMap.motorRightOne.set(-orientation.getResult());
    	RobotMap.motorRightTwo.set(-orientation.getResult());
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(target - adjustScale(RobotMap.navx.getYaw())) < 4;
	}
	
	@Override
	protected void end() {
	}
	
	private double adjustScale(double input){
		if(whichWay == 1){
			if(input < 0){
				return 360 - Math.abs(input);
			}
		}
		if(whichWay == -1){
			if(input > 0 ){
				return -1 * (360 - Math.abs(input));
			}
		}
		return input;
	}
	
}
