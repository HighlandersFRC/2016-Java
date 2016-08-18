
package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.io.IOException;

import org.usfirst.frc.team4499.robot.*;

import org.usfirst.frc.team4499.robot.commands.*;
import org.usfirst.frc.team4499.robot.commands.Autonomous.*;
import org.usfirst.frc.team4499.robot.tools.Arduino;
import org.usfirst.frc.team4499.robot.tools.CMDGroup;
import org.usfirst.frc.team4499.robot.tools.Tegra;

public class Robot extends IterativeRobot {
	int print = 0;
	TankDrive drive;
	Tegra tegra;
	Catapult catapult;
	Intake intake;
	Target autoTarget;
	double supplyVoltage = 4.53;
	CMDGroup autoGroup = new CMDGroup();
	CMDGroup teleopGroup = new CMDGroup();
	Arduino arduino = new Arduino();
	boolean stallDown = false;
	boolean stallUp = false;
	//Pinchers pinchers;
    public void robotInit() { 
        drive = new TankDrive(
        		RobotMap.motorLeftTwo,
				RobotMap.motorLeftOne, 
				RobotMap.motorRightTwo, 
				RobotMap.motorRightOne,
				RobotMap.shifters);
        catapult = new Catapult(
    			RobotMap.catapult, RobotMap.catapultRelease, RobotMap.intakePiston);
        intake = new Intake(RobotMap.intakePiston,RobotMap.intakeMotor);
        autoTarget = new Target();
        //pinchers = new Pinchers();
        try {
			tegra = new Tegra();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RobotMap.navx.zeroYaw();
        RobotMap.pincherMotor.zeroEncoder();
        RobotMap.pincherMotor.setStallCurrent(53);
        RobotMap.pincherMotor.setPercentStall(.08);
        teleopGroup.add(drive);
        teleopGroup.add(catapult);
        teleopGroup.add(intake);
        teleopGroup.add(autoTarget);
        arduino.write(1);
    }
    
    public void autonomousInit(){
    	RobotMap.navx.zeroYaw();
    	CommandGroup autonomous = AutoChooser.getAuto();
    	autonomous.start();
    	autoGroup.add(autonomous);
    }

    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    public void teleopInit(){
    	autoGroup.cancleAll();
    	System.out.println("Teleop Started");
    	teleopGroup.startAll();
    	//pinchers.start();
    }


    public void teleopPeriodic() {
	    while (isOperatorControl() && isEnabled()) {
	    	float vibration = Math.abs(RobotMap.navx.getWorldLinearAccelZ())/2;
	    	if(OI.dialOne.get()){
				// ChevalDeFrise Defense
	    		arduino.write(2);
	    	}
	    	else if(OI.dialTwo.get()){
	    		// Portcullis Defense
	    		arduino.write(3);
	    	}
	    	else if(OI.dialThree.get()){
	    		// D_Defense
	    		arduino.write(4);
	    	}
	    	else if(OI.dialFour.get()){
	    		// B_Defense
	    		arduino.write(5);
	    	}
	    	else if(OI.dialFive.get()){
	    		//Low Bar
	    		arduino.write(6);
	    	}
	    	else{
	    		//NoAuto
	    		arduino.write(7);
	    	}
	    	if(vibration < .5){
	    		vibration = 0;
	    	}
	    	OI.controllerOne.setRumble(Joystick.RumbleType.kLeftRumble,vibration);
	    	OI.controllerOne.setRumble(Joystick.RumbleType.kRightRumble,vibration);
	    	RobotMap.pincherMotor.updateStall();
	    	System.out.println(RobotMap.pincherMotor.checkStall() +" "+ stallUp +" "+ stallDown+" "+RobotMap.pincherMotor.getOutputCurrent());
		    	if(OI.pinchersUp.get() || OI.pinchersUpTwo.get()){
		    		if(!stallUp){
		    		//System.out.println("Going UP");
		    		RobotMap.pincherMotor.set(-.5);
		    		}
		    		if(RobotMap.pincherMotor.checkStall()){
		    			stallDown = false;
		    			stallUp = true;
		    			RobotMap.pincherMotor.set(0);
		    		}
		    		stallDown=false;
		    	}else if(OI.pinchersDown.get()|| OI.pinchersDownTwo.get() || OI.duck.get()){
		    		if(!stallDown){
		    		RobotMap.pincherMotor.set(.35);
		    		}
		    		if(RobotMap.pincherMotor.checkStall()){
		    			stallUp = false;
		    			stallDown = true;
		    			RobotMap.pincherMotor.set(0);
		    		}
		    		stallUp=false;
		    	}else{
//		    		if(!stallUp){
//		    			//sRobotMap.pincherMotor.set(-.15);
//		    		}
//		    		if(RobotMap.pincherMotor.checkStall()){
//		    			stallDown = false;
//		    			stallUp = true;
//		    			RobotMap.pincherMotor.set(0);
//		    		}
//		    		stallDown = false;
		    		
		    	}
	    	
	    	//distance.update();
	    	//System.out.println(distance.getDistance());
	    	double pressure = (250*(RobotMap.PressureSensor.getAverageVoltage() / supplyVoltage))-25;
	    	//System.out.println(pressure);
	    	SmartDashboard.putNumber("Pressure", pressure);
	    	SmartDashboard.putNumber("Pressure1", pressure);
	    	SmartDashboard.putNumber("Pressure2", pressure);
	    	Scheduler.getInstance().run();
	    	if(print > 100){
	    		
	    		print = 0;
	    	}
	    	print++;
	    	Timer.delay(0.005);
	    	
	    }  
	    
    }
    public void disabledInit(){
    	System.out.println("Disabled Started");
    	Arduino.write(0);
    	teleopGroup.cancleAll();
    	autoGroup.cancleAll();
    	
    }
       // System.out.println("Test");
    
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
