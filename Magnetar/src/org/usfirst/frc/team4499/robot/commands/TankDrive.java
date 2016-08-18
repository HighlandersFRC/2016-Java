package org.usfirst.frc.team4499.robot.commands;
import org.usfirst.frc.team4499.robot.*;
import org.usfirst.frc.team4499.robot.tools.DCMotor;
import org.usfirst.frc.team4499.robot.tools.PID;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TankDrive extends Command{
	double safety;
	Value pistonValue;
	double coPilotPower = .4;
	DCMotor backLeft;
	DCMotor frontLeft;
	DCMotor backRight;
	DCMotor frontRight;
	DoubleSolenoid shifter;
	
	PID lockOrientation = new PID(0.025,0,0);
	boolean lockPressed = false;
	
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
		lockOrientation.setContinuous(true);
		lockOrientation.setMaxInput(360);
		lockOrientation.setMinInput(0);
	}
	@Override
	protected void execute() {
    	if(shifter != null){
	    	if(OI.shiftUp.get()){
	    		pistonValue = RobotMap.highGear;
	    	}
	    	else{
	    		pistonValue = RobotMap.lowGear;
	    	}
	    	RobotMap.shifters.set(pistonValue);
	    	
    	}
    	
    	if(OI.controllerTwo.getPOV() == 90){
    		RobotMap.motorLeftOne.set(-coPilotPower);
        	RobotMap.motorLeftTwo.set(-coPilotPower);
        	
        	RobotMap.motorRightOne.set(-coPilotPower);
        	RobotMap.motorRightTwo.set(-coPilotPower);
    	}
    	else if(OI.controllerTwo.getPOV() == 180){
    		RobotMap.motorLeftOne.set(coPilotPower);
        	RobotMap.motorLeftTwo.set(coPilotPower);
        	
        	RobotMap.motorRightOne.set(-coPilotPower);
        	RobotMap.motorRightTwo.set(-coPilotPower);
    	}
    	else if(OI.controllerTwo.getPOV() == 270){
    		RobotMap.motorLeftOne.set(coPilotPower);
        	RobotMap.motorLeftTwo.set(coPilotPower);
        	
        	RobotMap.motorRightOne.set(coPilotPower);
        	RobotMap.motorRightTwo.set(coPilotPower);
    	}
    	else if(OI.controllerTwo.getPOV() == 0){
    		RobotMap.motorLeftOne.set(-coPilotPower);
        	RobotMap.motorLeftTwo.set(-coPilotPower);
        	
        	RobotMap.motorRightOne.set(coPilotPower);
        	RobotMap.motorRightTwo.set(coPilotPower);
    	}else{
    		if(OI.lockDrive.get()){
    			if(!lockPressed){
    				lockPressed = true;
    				RobotMap.navx.zeroYaw();
    				lockOrientation.setSetPoint(0);
    			}
    			lockOrientation.updatePID(RobotMap.navx.getYaw());
    			double result = lockOrientation.getResult();
    			RobotMap.motorLeftOne.set(OI.controllerOne.getRawAxis(1) -result);
    	    	RobotMap.motorLeftTwo.set(OI.controllerOne.getRawAxis(1) -result);
    	    	
    	    	RobotMap.motorRightOne.set(-OI.controllerOne.getRawAxis(3) - result);
    	    	RobotMap.motorRightTwo.set(-OI.controllerOne.getRawAxis(3) - result);
    			
    		}else{
    			lockPressed = false;
    			RobotMap.motorLeftOne.set(OI.controllerOne.getRawAxis(1));
    	    	RobotMap.motorLeftTwo.set(OI.controllerOne.getRawAxis(1));
    	    	
    	    	RobotMap.motorRightOne.set(-OI.controllerOne.getRawAxis(3));
    	    	RobotMap.motorRightTwo.set(-OI.controllerOne.getRawAxis(3));
    		}
    		
    	}
		SmartDashboard.putBoolean("", pistonValue == DoubleSolenoid.Value.kForward);
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
