package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLoad extends CommandGroup {
    public  AutoLoad() {
    	addSequential(new PistonSet(RobotMap.catapultRelease,RobotMap.latchOpen,.1));
    	addSequential(new PistonSet(RobotMap.intakePiston,RobotMap.intakeOut,.2));
    	addSequential(new PistonSet(RobotMap.catapult,RobotMap.catapultDown,2));
    	addSequential(new PistonSet(RobotMap.catapultRelease,RobotMap.latchClosed,.2));
    	addSequential(new PistonSet(RobotMap.catapult,RobotMap.catapultUp,2));
    	
    	
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
