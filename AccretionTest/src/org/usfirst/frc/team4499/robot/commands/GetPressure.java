package org.usfirst.frc.team4499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4499.robot.RobotMap;

public class GetPressure extends Command{

	float supplyVoltage;
	
	float pressure = (250 * (RobotMap.PressureSensor.getAverageBits() / supplyVoltage)) - 25;
	int iterations;
	
	public float GetPressure() {
		return pressure;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		iterations++;
		if (iterations % 10 == 0) {
			iterations = 0;
			pressure = (250 * (RobotMap.PressureSensor.getAverageBits() / supplyVoltage)) - 25;
	  	}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
