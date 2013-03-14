/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author edward
 */
public class Thrower extends UltimateAscentRobotPart{
    
    private Victor wheel;
    private double rate;
    private double pk = .00005;
    private DoubleSolenoid loader;
    private double smax = 0;
    
    public Thrower(UltimateAscentRobot robot){
        super(robot);
        loader = new DoubleSolenoid(1,2);
        wheel = new Victor(3);
        rate = 0;
        SmartDashboard.putNumber("PK", pk);
    }
    
    public void updateTeleop(){
        
        pk = SmartDashboard.getNumber("pk", pk);
        
        if(!robot.getSensors().getRightJoystick().getRawButton(9))
            wheel.set(robot.getSensors().getRightJoystick().getY() < 0?-robot.getSensors().getRightJoystick().getY():0);
        
        if(robot.getSensors().getRightJoystick().getY() < .05){
            controlRate();
        }
        if(robot.getSensors().getRightJoystick().getRawButton(5))
            rate = robot.getSensors().getRightJoystick().getY() < 0?-robot.getSensors().getRightJoystick().getY()*20000:0;
        
        if(robot.getSensors().getRightJoystick().getTrigger())
            loader.set(DoubleSolenoid.Value.kReverse);
        else
            loader.set(DoubleSolenoid.Value.kForward);
        
        if(robot.getSensors().getRightJoystick().getRawButton(3))
            controlRate();
        
        if(robot.getSensors().getRightJoystick().getRawButton(6))
            rate = 0;
        if(robot.getSensors().getRightJoystick().getRawButton(7))
            rate = 5000;
        if(robot.getSensors().getRightJoystick().getRawButton(8))
            rate = 10000;
        if(robot.getSensors().getRightJoystick().getRawButton(9))
            rate = 15000;
        if(robot.getSensors().getRightJoystick().getRawButton(10))
            rate = 20000;
            
        double trate = robot.getSensors().getEncoder().getRate();
        if(trate>smax)
            smax = trate;
        SmartDashboard.putNumber("SPWM"  ,wheel.getSpeed());
        SmartDashboard.putNumber("SSPEED", robot.getSensors().getEncoder().getRate()); 
    }
    
    private void controlRate(){
        if(rate > 1000)
            wheel.set(wheel.getSpeed()+((rate-(robot.getSensors().getEncoder().getRate()))*pk));
    }
    
    public void setRate(double nrate){
        rate = nrate;
    }
}
