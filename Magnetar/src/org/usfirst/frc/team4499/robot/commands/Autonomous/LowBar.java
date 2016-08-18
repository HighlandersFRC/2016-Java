package org.usfirst.frc.team4499.robot.commands.Autonomous;

import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.NavXDriveForward;
import org.usfirst.frc.team4499.robot.commands.PistonSet;
import org.usfirst.frc.team4499.robot.commands.SetPinchers;
import org.usfirst.frc.team4499.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
    
    public  LowBar() {
    	addSequential(new PistonSet(RobotMap.shifters,RobotMap.lowGear,0));
    	addSequential(new PistonSet(RobotMap.intakePiston,RobotMap.intakeOut,0));
    	addSequential(new Wait(.25));
    	addSequential(new NavXDriveForward(-.4,3));
    	addSequential(new NavXDriveForward(-.6,3));
    	addSequential(new PistonSet(RobotMap.intakePiston,RobotMap.intakeIn,0));
    	
    	
    }
}
