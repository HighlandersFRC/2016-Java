package org.usfirst.frc.team4499.robot;

import org.usfirst.frc.team4499.robot.tools.DCMotor;

import com.kauailabs.navx.frc.AHRS;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	private static DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
	private static DoubleSolenoid.Value forward = DoubleSolenoid.Value.kForward;
	private static DoubleSolenoid.Value reverse = DoubleSolenoid.Value.kReverse;
	
	
	
	// On Board NavX
	public static AHRS navx = new AHRS(SerialPort.Port.kMXP);
    
    //Drive Motors
	public static DCMotor motorLeftOne = new DCMotor(1); //blue encoder
	public static DCMotor motorLeftTwo = new DCMotor(2); //yellow
	public static DCMotor motorRightOne = new DCMotor(3); //red
	public static DCMotor motorRightTwo = new DCMotor(4);//green encoder
	
	//Non Drive Motors
	public static DCMotor intakeMotor = new DCMotor(5); 
	public static DCMotor pincherMotor = new DCMotor(6); //Purple
	
    //uncomment for comp bot
    //public static DoubleSolenoid intakePiston = new DoubleSolenoid(0,0,1);
    //public static DoubleSolenoid shifters = new DoubleSolenoid(0,6,7);
    public static DoubleSolenoid intakePiston = new DoubleSolenoid(0,0,1);
    public static DoubleSolenoid shifters = new DoubleSolenoid(0,6,7);
    public static DoubleSolenoid catapult = new DoubleSolenoid(1,0,1);
    public static DoubleSolenoid catapultRelease = new DoubleSolenoid(1,4,5);
	
	public static AnalogInput PressureSensor = new AnalogInput(1);
	
	//Piston Mappings (This is to make it easy to change which way pistons are going)
	public static DoubleSolenoid.Value intakeIn = forward;
	public static DoubleSolenoid.Value intakeOut = reverse;
	public static DoubleSolenoid.Value catapultUp = forward;
	public static DoubleSolenoid.Value catapultDown = reverse;
	public static DoubleSolenoid.Value highGear = forward;
	public static DoubleSolenoid.Value lowGear = reverse;
	public static DoubleSolenoid.Value latchOpen = reverse;
	public static DoubleSolenoid.Value latchClosed = forward;
}
