/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author edward
 */
public class Sensors extends UltimateAscentRobotPart{
    
    private Joystick leftStick;
    private Joystick rightStick;
    
    private Encoder  encoder;
    private Gyro     gyro;
    private DigitalInput di1;
    
    public Sensors(UltimateAscentRobot robot){
        super(robot);
        leftStick      = new Joystick(1);
        rightStick     = new Joystick(2);
        encoder = new Encoder(2,3);   //digital sources 2 and 3
        gyro           = new Gyro(1);      //analog channel 1
        di1            = new DigitalInput(8);
        //encoder.reset();
        encoder.start();
        
        //gyro.setSensitivity(.007);
        //chase scared away all the prostitutes
        gyro.reset();
    }
    public void updateTeleop(){
        if(robot.getSensors().getLeftJoystick().getRawButton(8)) {
            gyro.reset();
        }
        
        //SmartDashboard.putNumber("SPEED", encoder.get());
        //SmartDashboard.putBoolean("di1", di1.get());
    }
    public Joystick getLeftJoystick(){
        return leftStick;
    }
    public Joystick getRightJoystick(){
        return rightStick;
    }
    public Encoder getEncoder(){
        return encoder;
    }
    public Gyro getGyro(){
        return gyro;
    }
}
