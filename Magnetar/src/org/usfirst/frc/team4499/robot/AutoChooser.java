package org.usfirst.frc.team4499.robot;

import org.usfirst.frc.team4499.robot.commands.Autonomous.*;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoChooser {
	public static enum position{NONE,LEFT,LEFTMID,CENTER,RIGHTMID,RIGHT};
	public static CommandGroup getDefense(){
		CommandGroup defense;
		
		if(OI.dialOne.get()){
			// ChevalDeFrise Defense
    		defense = new ChevalDeFrise();
    		System.out.println("ChevalDeFrise Chosen");
    	}
    	else if(OI.dialTwo.get()){
    		// Portcullis Defense
    		defense = new Portcullis();
    		System.out.println("PortCullis Chosen");
    	}
    	else if(OI.dialThree.get()){
    		// D_Defense
    		defense = new D_Defense();
    		System.out.println("D Defense Chosen");
    	}
    	else if(OI.dialFour.get()){
    		// B_Defense
    		defense = new B_Defense();
    		System.out.println("B_Defense Chosen");
    	}
    	else if(OI.dialFive.get()){
    		//Low Bar
    		defense = new LowBar();
    		System.out.println("Low Bar Chosen");
    	}
    	else{
    		//NoAuto
    		defense = new NoAuto();
    		System.out.println(" No Autonomous Chosen");
    	}
		return defense;
	}
	public static position getPosition(){
		if(OI.switchTwo.get()){
			if(OI.switchOne.get()){
				return position.LEFTMID;
			}else if(OI.switchThree.get()){
				return position.RIGHTMID;
			}
			else{
				return position.CENTER;
			}
		}
		else if(OI.switchOne.get()){
			return position.LEFT;
		}
		else if(OI.switchThree.get()){
			return position.RIGHT;
		}
		else{
			return position.NONE;
		}
	}
	public static CommandGroup getAuto(){
		position place = getPosition();
		CommandGroup defense = getDefense();
		if(place != position.NONE){
			return new Ball_Auto(defense,place);
		}
		return defense;
	}
	}
