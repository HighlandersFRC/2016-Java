package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4499.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// mapping of buttons for first Controller
	
	// "Old" Logitech controller
	/*
	public static Joystick controllerOne = new Joystick(0);
	public static JoystickButton shiftUp = new JoystickButton(controllerOne, 6);
	public static JoystickButton lockDrive = new JoystickButton(controllerOne,5);
	public static JoystickButton intake = new JoystickButton(controllerOne,8);
	public static JoystickButton intakeOut = new JoystickButton(controllerOne,7);
	public static JoystickButton intakeDown = new JoystickButton(controllerOne,3);
	public static JoystickButton intakeUp = new JoystickButton(controllerOne,1);
	public static JoystickButton pinchersDown = new JoystickButton(controllerOne,2);
	public static JoystickButton pinchersUp = new JoystickButton(controllerOne,4);
	public static JoystickButton fire = new JoystickButton(controllerOne,10);
	
	// Second person driving control
	public static Joystick controllerTwo = new Joystick(1);
	//public static JoystickButton fire = new JoystickButton(controllerTwo,4);
	public static JoystickButton load = new JoystickButton(controllerTwo,2);
	public static JoystickButton Target = new JoystickButton(controllerTwo,5 );
	public static JoystickButton pinchersDownTwo = new JoystickButton(controllerTwo,7 );
	public static JoystickButton pinchersUpTwo = new JoystickButton(controllerTwo,8 );
	public static JoystickButton duck = new JoystickButton(controllerTwo,6);
	
	// mapping of buttons for autonomous chooser
	public static Joystick dial = new Joystick(2);
	public static JoystickButton dialOne = new JoystickButton(dial,1);
	public static JoystickButton dialTwo = new JoystickButton(dial,2);
	public static JoystickButton dialThree = new JoystickButton(dial,3);
	public static JoystickButton dialFour = new JoystickButton(dial,4);
	public static JoystickButton dialFive = new JoystickButton(dial,5);
	public static JoystickButton switchOne = new JoystickButton(dial, 6);
	public static JoystickButton switchTwo = new JoystickButton(dial, 7);
	public static JoystickButton switchThree = new JoystickButton(dial, 8);
	*/
	
	// New Xbox One Controller
	
	public static Joystick controllerOne = new Joystick(0);
	public static JoystickButton shiftUp = new JoystickButton(controllerOne, 6);
	public static JoystickButton lockDrive = new JoystickButton(controllerOne,5);
	//public static JoystickButton intake = new JoystickButton(controllerOne,8);
	//public static JoystickButton intakeOut = new JoystickButton(controllerOne,7);
	public static JoystickButton intakeDown = new JoystickButton(controllerOne,2);
	public static JoystickButton intakeUp = new JoystickButton(controllerOne,3);
	public static JoystickButton pinchersDown = new JoystickButton(controllerOne,1);
	public static JoystickButton pinchersUp = new JoystickButton(controllerOne,4);
	public static JoystickButton fire = new JoystickButton(controllerOne,8);
	
	// Second person driving control
	public static Joystick controllerTwo = new Joystick(1);
	//public static JoystickButton fire = new JoystickButton(controllerTwo,4);
	public static JoystickButton load = new JoystickButton(controllerTwo,1);
	public static JoystickButton Target = new JoystickButton(controllerTwo,5 );
	//public static JoystickButton pinchersDownTwo = new JoystickButton(controllerTwo,7 );
	//public static JoystickButton pinchersUpTwo = new JoystickButton(controllerTwo,8 );
	public static JoystickButton duck = new JoystickButton(controllerTwo,6);
	
	// mapping of buttons for autonomous chooser
	public static Joystick dial = new Joystick(2);
	public static JoystickButton dialOne = new JoystickButton(dial,1);
	public static JoystickButton dialTwo = new JoystickButton(dial,2);
	public static JoystickButton dialThree = new JoystickButton(dial,3);
	public static JoystickButton dialFour = new JoystickButton(dial,4);
	public static JoystickButton dialFive = new JoystickButton(dial,5);
	public static JoystickButton switchOne = new JoystickButton(dial, 6);
	public static JoystickButton switchTwo = new JoystickButton(dial, 7);
	public static JoystickButton switchThree = new JoystickButton(dial, 8);
	
	
	
	
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

