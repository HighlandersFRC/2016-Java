
package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc.team4499.robot.*;

import org.usfirst.frc.team4499.robot.commands.*;
import org.usfirst.frc.team4499.robot.tools.Arduino;

public class Robot extends IterativeRobot {
	TankDrive drive;
	Gimbal cameraMount;
	Camera camera;
	Value pistonValue;
	int print = 0; 
	Lidar distance;
	GetPressure pressure;
	
	
	
	double supplyVoltage = 4.53; //default 5, VO was 1.54
	
	double returnPressure;
	double returnVoltage;
	int tail = 15;
	double[] prevValues = new double[tail];
	double[] prevVoltage = new double[tail];
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {   
    	
    	for(int i = 0; i < tail; i++) {
        	prevValues[i] = ( 250 * (RobotMap.PressureSensor.getAverageVoltage() / supplyVoltage) - 25 );
        	prevVoltage[i] = (RobotMap.PressureSensor.getAverageVoltage());
        }
        drive = new TankDrive(
        		RobotMap.motorLeftTwo,
				RobotMap.motorLeftOne, 
				RobotMap.motorRightTwo, 
				RobotMap.motorRightOne,
				RobotMap.shifters);
        cameraMount = new Gimbal();
        camera = new Camera(50, "cam0");
        pistonValue = DoubleSolenoid.Value.kOff;
        distance = new Lidar(I2C.Port.kMXP);
        
        
        
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit(){
    	System.out.println("Started Autonomous");
    	Arduino.write(2);
    	if(OI.dialOne.get()){
    		System.out.println("Started Autonomous One");
    	}
    	else if(OI.dialTwo.get()){
    		System.out.println("Started Autonomous Two");
    		DriveForward forward = new DriveForward(20);
        	forward.start();
        	System.out.println("Autonomous Completed");
    	}
    	else if(OI.dialThree.get()){
    		System.out.println("Started Autonomous Three");
    		Turn turnaround = new Turn(-180);
    		turnaround.start();
    	}
    	else if(OI.dialFour.get()){
    		System.out.println("Started Autonomous Four");
    		TestSequence sequence = new TestSequence();
    		sequence.start();
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
    	//SmartDashboard.putNumber("Orientation",RobotMap.navx.getAngle());
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    
    public void teleopInit(){
    	System.out.println("Teleop Started");
    	Arduino.write(1);
    	drive.start();
    	cameraMount.start();
    	distance.start();
    	
    	
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
	    while (isOperatorControl() && isEnabled()) {
	    	distance.update();
	    	System.out.println(distance.getDistance());
	    	Scheduler.getInstance().run();
	    	Arduino.write(1);
	    	//SmartDashboard.putNumber("Orientation",RobotMap.navx.getAngle());
	    	
	    	
	    	
	    	double pressure = (250 * (RobotMap.PressureSensor.getAverageVoltage() / supplyVoltage)) - 25;
	    	
	    	//returnPressure
	    	float sum = 0;
	    	float sum1 = 0;
	    	for(int i = 0; i < tail; i++) {
	    		sum += prevValues[i];
	    		sum1 += prevVoltage[i];
	    	}
	    	SmartDashboard.putNumber("earlySum", sum);
	    	for(int i = tail - 1; i > 0; i--) {
	    		prevValues[i] = prevValues[i-1];
	    		prevVoltage[i] = prevVoltage[i-1];
	    	}
	    	prevValues[0] = ( 250 * (RobotMap.PressureSensor.getAverageVoltage() / supplyVoltage) - 25 );
	    	prevVoltage[0] = RobotMap.PressureSensor.getAverageVoltage();
	    	returnPressure = sum / tail;
	    	returnVoltage = sum1 / tail;
	    	
	    	SmartDashboard.putNumber("PV0", prevValues[0]);
	    	SmartDashboard.putNumber("PV1", prevValues[1]);
	    	SmartDashboard.putNumber("PV2", prevValues[2]);
	    	
	    	SmartDashboard.putNumber("SUM", sum);
	    	SmartDashboard.putNumber("TAIL", tail);
	    	SmartDashboard.putNumber("PSI", pressure);
	    	SmartDashboard.putNumber("PSI SMA", returnPressure);
	    	SmartDashboard.putNumber("Raw return", RobotMap.PressureSensor.getAverageVoltage());
	    	
	    	SmartDashboard.putNumber("Raw return SMA", returnVoltage);
	    	
	    	if(print > 100){    		
	    		print = 0;
	    	}
	    	print++; 
	    	
	    	if(OI.pistonIn.get()){
	    		pistonValue = DoubleSolenoid.Value.kForward;
	    	}
	    	if(OI.pistonOut.get()){
	    		pistonValue = DoubleSolenoid.Value.kReverse;
	    	}
	    	RobotMap.piston.set(pistonValue);
	    	
	    	distance.update();
	    	System.out.println(distance.getDistance());
	    	
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
