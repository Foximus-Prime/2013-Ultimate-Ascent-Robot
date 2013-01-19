/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author edward
 */
public class Sensors extends UltimateAscentRobotPart{
    
    private Joystick leftStick;
    private Joystick rightStick;
    
    private Encoder  throwerEncoder;
    private Gyro     gyro;
    
    public Sensors(UltimateAscentRobot robot){
        super(robot);
        leftStick      = new Joystick(1);
        rightStick     = new Joystick(2);
        throwerEncoder = new Encoder(1,2); //digital sources 1 and 2
        gyro           = new Gyro(1);      //analog channel 1
        gyro.setSensitivity(.007);
        gyro.reset();
    }
    public void updateTeleop(){
        if(robot.getSensors().getLeftJoystick().getRawButton(8)) {
            gyro.reset();
        }
    }
    public Joystick getLeftJoystick(){
        return leftStick;
    }
    public Joystick getRightJoystick(){
        return rightStick;
    }
    public Encoder getThrowerEncoder(){
        return throwerEncoder;
    }
    public Gyro getGyro(){
        return gyro;
    }
}
