package org.usfirst.frc.team4499.robot.commands.Autonomous;

import org.usfirst.frc.team4499.robot.AutoChooser;
import org.usfirst.frc.team4499.robot.commands.NavXDriveForward;
import org.usfirst.frc.team4499.robot.commands.Turn;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Navigate extends CommandGroup {
    
    public  Navigate(AutoChooser.position position) {
    	if(position == AutoChooser.position.LEFT){
    		addSequential(new Turn(-120,true));
    		addSequential(new Wait(.1));
    		addSequential(new NavXDriveForward(.6,1));
    		addSequential(new Wait(.1));
    		
    	}
    	else if(position == AutoChooser.position.LEFTMID){
    		addSequential(new Turn(60,true));
    		addSequential(new Wait(.1));
    		addSequential(new NavXDriveForward(.7,1.25));
    		addSequential(new Wait(.1));
    		addSequential(new Turn(0,true));
    		addSequential(new NavXDriveForward(.7,.75));
    	}
    	else if(position == AutoChooser.position.CENTER){
    		addSequential(new Turn(30,true));
    		addSequential(new NavXDriveForward(.5,1));
    		addSequential(new Turn(0,true));
    	}
    	else if(position == AutoChooser.position.RIGHTMID){
    		addSequential(new Turn(-30,true));
    		addSequential(new NavXDriveForward(.5,1));
    		addSequential(new Turn(0,true));
    		
    	}
    	else if(position == AutoChooser.position.RIGHT){
    		addSequential(new NavXDriveForward(.5,1));
    		addSequential(new Wait(.1));
    		addSequential(new Turn(-60,true));
    		addSequential(new NavXDriveForward(.4,1.2));
    	}
    }
}
