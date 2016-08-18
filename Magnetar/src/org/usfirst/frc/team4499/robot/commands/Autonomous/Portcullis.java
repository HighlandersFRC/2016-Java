package org.usfirst.frc.team4499.robot.commands.Autonomous;

import org.usfirst.frc.team4499.robot.commands.NavXDriveForward;
import org.usfirst.frc.team4499.robot.commands.SetPinchers;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Portcullis extends CommandGroup {
    
    public  Portcullis() {
    	addSequential(new SetPinchers(.3,.1));
    	addSequential(new Wait(.25));
    	addSequential(new NavXDriveForward(.5,1.5));
    	addSequential(new NavXDriveForward(.3,2));
    }
}
