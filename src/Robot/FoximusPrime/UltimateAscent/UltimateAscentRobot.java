/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package Robot.FoximusPrime.UltimateAscent;



import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also updateTeleop the manifest file in the resource
 * directory.
 */
public class UltimateAscentRobot extends IterativeRobot {
    
    private Climber        climber;
    private DashboardComm  dashboardComm;
    private Drive          drive;
    private ImageProcesser imageProcesser;
    private Pickup         pickup;
    private Sensors        sensors;   
    private Thrower        thrower;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        climber        = new Climber(this);
        dashboardComm  = new DashboardComm(this);
        drive          = new Drive(this);
        imageProcesser = new ImageProcesser(this);   
        pickup         = new Pickup(this);
        sensors        = new Sensors(this);
        thrower        = new Thrower(this);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        climber.updateAutonomous();
        dashboardComm.updateAutonomous();
        drive.updateAutonomous();
        imageProcesser.updateAutonomous();
        pickup.updateAutonomous();
        sensors.updateAutonomous();
        thrower.updateAutonomous();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        climber.updateTeleop();
        dashboardComm.updateTeleop();
        drive.updateTeleop();
        imageProcesser.updateTeleop();
        pickup.updateTeleop();
        sensors.updateTeleop();
        thrower.updateTeleop();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        climber.runTest();
        dashboardComm.runTest();
        drive.runTest();
        imageProcesser.runTest();
        pickup.runTest();
        sensors.runTest();
        thrower.runTest();
    }
    
    public Climber getClimber(){
        return climber;
    }
    public DashboardComm getDashboardComm(){
        return dashboardComm;
    }
    public Drive getDrive(){
        return drive;
    }
    public ImageProcesser getImageProcesser(){
        return imageProcesser;
    }
    public Pickup getPickup(){
        return pickup;
    }
    public Sensors getSensors(){
        return sensors;
    }
    public Thrower getThrower(){
        return thrower;
    }
}
