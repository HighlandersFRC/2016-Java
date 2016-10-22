package org.usfirst.frc.team4499.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4499.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick controllerOne = new Joystick(0);
	
	public static JoystickButton wingbutton = new JoystickButton(controllerOne, 2);
	public static JoystickButton safteybutton = new JoystickButton(controllerOne,8);
	
	
	
	
}

