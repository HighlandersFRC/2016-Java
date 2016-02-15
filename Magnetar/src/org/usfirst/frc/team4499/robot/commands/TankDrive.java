package org.usfirst.frc.team4499.robot.commands;
import org.usfirst.frc.team4499.robot.*;
import org.usfirst.frc.team4499.robot.tools.DCMotor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;



public class TankDrive extends Command{
	double safety;
	Value pistonValue;
	
	DCMotor backLeft;
	DCMotor frontLeft;
	DCMotor backRight;
	DCMotor frontRight;
	DoubleSolenoid shifter;
	
	public TankDrive(DCMotor bl, DCMotor fl, DCMotor br, DCMotor fr){
		backLeft = bl;
		frontLeft = fl;
		backRight = br;
		frontRight = fr;
		shifter = null;
	}
	
	public TankDrive(DCMotor bl, DCMotor fl, DCMotor br, DCMotor fr, DoubleSolenoid s){
		backLeft = bl;
		frontLeft = fl;
		backRight = br;
		frontRight = fr;
		shifter = s;
	}
	
	
	@Override
	protected void initialize() {
		// TODO Auto-
		//generated method stub
		pistonValue = DoubleSolenoid.Value.kOff;
	}
	@Override
	protected void execute() {
    	if(shifter != null){
	    	if(OI.shiftUp.get()){
	    		pistonValue = DoubleSolenoid.Value.kForward;
	    	}
	    	if(OI.shiftDown.get()){
	    		pistonValue = DoubleSolenoid.Value.kReverse;
	    	}
	    	RobotMap.shifters.set(pistonValue);
    	}
		
    	RobotMap.motorLeftOne.set(OI.controllerOne.getRawAxis(1));
    	RobotMap.motorLeftTwo.set(OI.controllerOne.getRawAxis(1));
    	
    	RobotMap.motorRightOne.set(-OI.controllerOne.getRawAxis(3));
    	RobotMap.motorRightTwo.set(-OI.controllerOne.getRawAxis(3));
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		RobotMap.motorLeftOne.set(0);
    	RobotMap.motorLeftTwo.set(0);
    	RobotMap.motorRightOne.set(0);
    	RobotMap.motorRightTwo.set(0);
		
		
	}

	@Override
	protected void interrupted() {
		RobotMap.motorLeftOne.set(0);
    	RobotMap.motorLeftTwo.set(0);
    	RobotMap.motorRightOne.set(0);
    	RobotMap.motorRightTwo.set(0);
		
	}
	

}
