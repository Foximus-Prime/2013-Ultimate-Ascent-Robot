/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author edward
 */
public class Climber extends UltimateAscentRobotPart{
   
    DoubleSolenoid pivot;
    Victor armMotor;
    boolean active;
    public Climber(UltimateAscentRobot robot){
        super(robot);
        
        armMotor = new Victor(3);
        pivot = new DoubleSolenoid(3,4);
        pivot.set(DoubleSolenoid.Value.kOff);
        active = true;
    }
    
    public void updateTeleop(){
        if(robot.getSensors().getRightJoystick().getRawButton(2) && active && Math.abs(robot.getSensors().getRightJoystick().getY())>.2)
            armMotor.set(-robot.getSensors().getRightJoystick().getY());
        else
            armMotor.set(0);
        
        
        if(robot.getSensors().getRightJoystick().getRawButton(6))
            pivot.set(DoubleSolenoid.Value.kReverse);
        if(robot.getSensors().getRightJoystick().getRawButton(7))
            pivot.set(DoubleSolenoid.Value.kForward);
        if(robot.getSensors().getRightJoystick().getRawButton(8))
            pivot.set(DoubleSolenoid.Value.kOff);
        
        if(robot.getSensors().getArmLimit().get())
            active=false;
        
        SmartDashboard.putBoolean("ArmsActive", active);
    }
    public void reactivate(){
        active = true;
    }
    public boolean runTest(){
        
        if(robot.getSensors().getRightJoystick().getRawButton(2) && Math.abs(robot.getSensors().getRightJoystick().getY())>.2)
            armMotor.set(-robot.getSensors().getRightJoystick().getY());
        else
            armMotor.set(0);
        
        return true;
    }
    
}
