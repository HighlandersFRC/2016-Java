package org.usfirst.frc.team4499.robot.commands.Autonomous;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.AutoTarget;
import org.usfirst.frc.team4499.robot.commands.NavXDriveForward;
import org.usfirst.frc.team4499.robot.commands.PistonSet;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class D_Defense extends CommandGroup {
    
    public  D_Defense() {
    	addSequential(new PistonSet(RobotMap.shifters,RobotMap.lowGear,0));
    	addSequential(new NavXDriveForward(.7,3));
    }
}
