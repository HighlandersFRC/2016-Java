package org.usfirst.frc.team4499.robot;

import org.usfirst.frc.team4499.robot.tools.DCMotor;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static DCMotor motorLeftOne = new DCMotor(1); //blue
	public static DCMotor motorLeftTwo = new DCMotor(2); //yellow
	public static DCMotor motorRightOne = new DCMotor(3); //red
	public static DCMotor motorRightTwo = new DCMotor(4); //green
	
	public static DoubleSolenoid wings = new DoubleSolenoid(1,6,7);
	public static DoubleSolenoid shifters = new DoubleSolenoid(0,0,1);
	
	public static DoubleSolenoid.Value wingsDown = DoubleSolenoid.Value.kReverse;
	public static DoubleSolenoid.Value wingsUp = DoubleSolenoid.Value.kForward;
	
}
