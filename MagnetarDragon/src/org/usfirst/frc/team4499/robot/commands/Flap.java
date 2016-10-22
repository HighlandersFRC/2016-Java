package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Flap extends CommandGroup{

	public Flap(){
		addSequential(new PistonSet(RobotMap.wings,RobotMap.wingsDown,5));
		addSequential(new PistonSet(RobotMap.wings,RobotMap.wingsUp,5));
	}
	
	
}
