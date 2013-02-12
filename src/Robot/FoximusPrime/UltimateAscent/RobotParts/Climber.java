/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author edward
 */
public class Climber extends UltimateAscentRobotPart{
   
    Jaguar armMotor;
    
    public Climber(UltimateAscentRobot robot){
        super(robot);
        
        armMotor = new Jaguar(4);
    }
    
    public void updateTeleop(){
        if(robot.getSensors().getRightJoystick().getRawButton(9))
            armMotor.set(robot.getSensors().getRightJoystick().getY());
    }
}
