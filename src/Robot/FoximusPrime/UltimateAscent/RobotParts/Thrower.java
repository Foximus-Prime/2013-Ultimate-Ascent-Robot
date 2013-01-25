/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author edward
 */
public class Thrower extends UltimateAscentRobotPart{
    
    private Victor frontWheel;
    private Victor backWheel;
    private double rate;
    private double pk = .05;
    
    public Thrower(UltimateAscentRobot robot){
        super(robot);
        frontWheel = new Victor(3);
        backWheel  = new Victor(4);
        rate = 0;
    }
    
    public void updateTeleop(){
        frontWheel.set(robot.getSensors().getRightJoystick().getY() > 0?robot.getSensors().getRightJoystick().getY():0);
        backWheel.set(robot.getSensors().getRightJoystick().getY() > 0?robot.getSensors().getRightJoystick().getY():0);
        
        if(robot.getSensors().getRightJoystick().getY() == 0){
            controlRate();
        }
        
        if(robot.getSensors().getRightJoystick().getRawButton(9))
            rate = 0;
    }
    
    private void controlRate(){
        frontWheel.set(frontWheel.getSpeed()+((rate-robot.getSensors().getFrontEncoder().getRate())*pk));
    }
    
    public void setRate(double nrate){
        rate = nrate;
    }
}
