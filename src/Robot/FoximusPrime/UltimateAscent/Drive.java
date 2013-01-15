/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author edward
 */
public class Drive extends UltimateAscentRobotPart {
    
    private Jaguar     leftMotor;
    private Jaguar     rightMotor;
    private RobotDrive drive;
    
    public Drive(UltimateAscentRobot robot){
        super(robot);
        leftMotor  = new Jaguar(1); //PWM 1
        rightMotor = new Jaguar(2); //PWM 2
        drive      = new RobotDrive(leftMotor,rightMotor);
    }
    
    public void updateTeleop(){
        drive.arcadeDrive(robot.getSensors().getLeftJoystick());
    }
}
