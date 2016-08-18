package org.usfirst.frc.team4499.robot.commands.Autonomous;

import org.usfirst.frc.team4499.robot.AutoChooser;
import org.usfirst.frc.team4499.robot.RobotMap;
import org.usfirst.frc.team4499.robot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Ball_Auto extends CommandGroup {
    
    public  Ball_Auto(CommandGroup defense,AutoChooser.position pos) {
    	//set intake in, pinchers up and to high gear
    	addSequential(new PistonSet(RobotMap.shifters,RobotMap.lowGear,0));
    	addSequential(new PistonSet(RobotMap.intakePiston,RobotMap.intakeIn,0));
    	addSequential(defense);
    	addSequential(new Wait(.1));
    	addSequential(new Navigate(pos));
    	addSequential(new AutoTarget());
    	addSequential(new PistonSet(RobotMap.intakePiston,RobotMap.intakeOut,1));
    	addSequential(new AutoTarget());
    	addSequential(new Fire());
    }
}
