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
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author edward
 */
public class Thrower extends UltimateAscentRobotPart{
    
    private Victor wheel;
    private double rate;
    private double pk = .05;
    private DoubleSolenoid loader;
    
    public Thrower(UltimateAscentRobot robot){
        super(robot);
        loader = new DoubleSolenoid(1,2);
        wheel = new Victor(4);
        rate = 0;
    }
    public void updateAutonomous(){
        Timer tim = new Timer();
        tim.reset();
        tim.start();
        double t1 = tim.get()+1.5;
        double t2 = t1+1.5;
        wheel.set(1);
        while(robot.isAutonomous() && robot.isEnabled()){
            if(tim.get()>t1 && tim.get() < t2){
                loader.set(DoubleSolenoid.Value.kReverse);
            }                
            else if(tim.get() > t2){
                t1 = tim.get()+1.5;
                t2 = t1 + 1.5;                
                loader.set(DoubleSolenoid.Value.kForward);    
            }      
            else{
                loader.set(DoubleSolenoid.Value.kForward);                
            }
            
            robot.getWatchdog().feed();
            
            SmartDashboard.putBoolean("Watchdog", robot.getWatchdog().isAlive());
        }
        wheel.set(0);
        loader.set(DoubleSolenoid.Value.kForward);
    }
    
    public void updateTeleop(){
        
        if(!robot.getSensors().getRightJoystick().getRawButton(2) && Math.abs(robot.getSensors().getRightJoystick().getY()) > .15){
            wheel.set(robot.getSensors().getRightJoystick().getY() < 0?-robot.getSensors().getRightJoystick().getY():0);
            robot.getPnuematics().compressorOff();
        }
        else{
            robot.getPnuematics().compressorOn();
            wheel.set(0);
        }
        
        if(robot.getSensors().getRightJoystick().getY() == 0){
            controlRate();
        }
        
        
        if(!robot.getSensors().getRightJoystick().getRawButton(2) &&  robot.getSensors().getRightJoystick().getTrigger())
            loader.set(DoubleSolenoid.Value.kReverse);
        else
            loader.set(DoubleSolenoid.Value.kForward);
        
        SmartDashboard.putNumber("SPEED", 100*Math.abs(wheel.getSpeed())); 
    }
    
    private void controlRate(){
        wheel.set(wheel.getSpeed()+((rate-robot.getSensors().getFrontEncoder().getRate())*pk));
    }
    
    public void setRate(double nrate){
        rate = nrate;
    }
}
