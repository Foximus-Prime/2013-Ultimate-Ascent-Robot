/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author edward
 */
public class Drive extends UltimateAscentRobotPart {
    
    private Jaguar        leftMotor;
    private Jaguar        rightMotor;
    private RobotDrive    drive;
    
    private double        KP = .05;
    private double        AUTOMAX = .3;
    
    public Drive(UltimateAscentRobot robot){
        super(robot);
        leftMotor  = new Jaguar(1); //PWM 1
        rightMotor = new Jaguar(2); //PWM 2
        drive      = new RobotDrive(leftMotor,rightMotor);
    }
    
    public void updateTeleop(){
        if (robot.getSensors().getLeftJoystick().getRawButton(3))
            adjustAim(robot.getImageProcesser().getOffCenter());
        else
            drive.arcadeDrive(robot.getSensors().getLeftJoystick().getY(), -robot.getSensors().getLeftJoystick().getX());
    }
    
    public void updateAuto(){
        double power = -robot.getSensors().getGyro().getAngle() * KP;
        drive.arcadeDrive(0, power > AUTOMAX?power:AUTOMAX);
    }
    public void adjustAim(double offsets){
    if (offsets >= 3 || offsets <= 3)
        drive.arcadeDrive(0, offsets * .05);
    }
}
