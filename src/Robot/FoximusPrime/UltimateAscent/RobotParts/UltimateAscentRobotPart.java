/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;

/**
 *
 * @author edward
 */
public abstract class UltimateAscentRobotPart {
    
    protected UltimateAscentRobot robot;
    
    UltimateAscentRobotPart(UltimateAscentRobot robot){
        this.robot = robot;
    }
    
    public void updateTeleop(){};
    public void updateAutonomous(){};
    public boolean runTest(){return true;};
}
