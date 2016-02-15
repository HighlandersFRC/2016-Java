package org.usfirst.frc.team4499.robot.commands;

import org.usfirst.frc.team4499.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Fire extends CommandGroup {
	private Value off = DoubleSolenoid.Value.kOff;
	private Value forward = DoubleSolenoid.Value.kForward;
	private Value reverse = DoubleSolenoid.Value.kReverse;
    public  Fire() {
    	addSequential(new PistonSet(RobotMap.catapult, reverse, 0));
    	addSequential(new PistonSet(RobotMap.intakePiston,forward, .2));
    	addSequential(new PistonSet(RobotMap.catapultRelease, forward, .1));
    }
}
