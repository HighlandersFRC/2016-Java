
package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.io.IOException;

import org.usfirst.frc.team4499.robot.*;

import org.usfirst.frc.team4499.robot.commands.*;
import org.usfirst.frc.team4499.robot.tools.Arduino;
import org.usfirst.frc.team4499.robot.tools.Tegra;

public class Robot extends IterativeRobot {
	TankDrive drive;
	Camera camera;
	int print = 0;
	Tegra tegra;
	AutoTarget targeting;
	AutoGimbal trackCamera;
	
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {   	
        drive = new TankDrive(
        		RobotMap.motorLeftTwo,
				RobotMap.motorLeftOne, 
				RobotMap.motorRightTwo, 
				RobotMap.motorRightOne,
				RobotMap.shifters);
       // camera = new Camera(50, "cam0");
        trackCamera = new AutoGimbal();
        targeting = new AutoTarget(trackCamera);
        try {
			tegra = new Tegra();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit(){
    	System.out.println("Started Autonomous");
    	if(OI.dialOne.get()){
    		System.out.println("Started Autonomous One");
    	}
    	else if(OI.dialTwo.get()){
    		System.out.println("Started Autonomous Two");
    	}
    	else if(OI.dialThree.get()){
    		System.out.println("Started Autonomous Three");
    	}
    	else if(OI.dialFour.get()){
    		System.out.println("Started Autonomous Four");
    	}
    	else if(OI.dialFive.get()){
    		System.out.println("Started Autonomous Five");
    	}
    	else{
    		// Do Nothing
    		// Default to No auto
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	System.out.println("Teleop Started");
    	drive.start();
    	trackCamera.start();
    	targeting.start();
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
	    while (isOperatorControl() && isEnabled()) {
	    	//distance.update();
	    	//System.out.println(distance.getDistance());
	    	Scheduler.getInstance().run();
	    	if(OI.testDown.get()){
	    		RobotMap.testMotorOne.set(-.5);
	    	}
	    	else if(OI.testUp.get()){
	    		RobotMap.testMotorOne.set(.5);
	    	}
	    	else{
	    		RobotMap.testMotorOne.set(0);
	    	}
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
    	
    }
       // System.out.println("Test");
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
