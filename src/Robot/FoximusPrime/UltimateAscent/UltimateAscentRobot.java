 /*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package Robot.FoximusPrime.UltimateAscent;



import Robot.FoximusPrime.UltimateAscent.RobotParts.Climber;
import Robot.FoximusPrime.UltimateAscent.RobotParts.DashboardComm;
import Robot.FoximusPrime.UltimateAscent.RobotParts.Drive;
import Robot.FoximusPrime.UltimateAscent.RobotParts.ImageProcesser;
import Robot.FoximusPrime.UltimateAscent.RobotParts.Pickup;
import Robot.FoximusPrime.UltimateAscent.RobotParts.Sensors;
import Robot.FoximusPrime.UltimateAscent.RobotParts.Thrower;
import Robot.FoximusPrime.UltimateAscent.RobotParts.Pnuematics;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

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
    private Pnuematics     pnuematics;
    
    private DriverStation  driverStation;
    //private Compressor     rcompressor;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit() {
        getWatchdog().setEnabled(true);
        getWatchdog().setExpiration(2);
        climber        = new Climber(this);
        dashboardComm  = new DashboardComm(this);
        drive          = new Drive(this);
        imageProcesser = new ImageProcesser(this);   
        pickup         = new Pickup(this);
        sensors        = new Sensors(this);
        thrower        = new Thrower(this);
        pnuematics     = new Pnuematics(this);
        driverStation  = DriverStation.getInstance();
        
        //rcompressor     = new Compressor(1,1);
        //rcompressor.start();
    }

    
    
        public void teleopInit(){
            pnuematics.compressorOn();         
            climber.reactivate();
            
            //rcompressor.start();
        }
        
        public void disableInit(){
            
            pnuematics.compressorOff();
            //rcompressor.stop();
        }

    public void disabledPeriodic() {
        super.disabledPeriodic();
        
        SmartDashboard.putBoolean("Watchdog", getWatchdog().isAlive());
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        while(this.isAutonomous() && this.isEnabled()){
        climber.updateAutonomous();
        dashboardComm.updateAutonomous();
        drive.updateAutonomous();
        imageProcesser.updateAutonomous();
        pickup.updateAutonomous();
        sensors.updateAutonomous();
        thrower.updateAutonomous();
        
        drive.adjustAim(-imageProcesser.getOffCenter());
        }
    }

//    public void teleopInit(){
//        
//    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        imageProcesser.getFPS();
        climber.updateTeleop();
        dashboardComm.updateTeleop();
        drive.updateTeleop();
        imageProcesser.updateTeleop();
        pickup.updateTeleop();
        sensors.updateTeleop();
        thrower.updateTeleop();
        pnuematics.updateTeleop();
        
        getWatchdog().feed();
        SmartDashboard.putNumber("Batt", driverStation.getBatteryVoltage());
        SmartDashboard.putNumber("OffCenter", imageProcesser.getOffCenter());
        SmartDashboard.putBoolean("Watchdog", getWatchdog().isAlive());
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
        SmartDashboard.putBoolean("Watchdog", getWatchdog().isAlive());
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
    public Pnuematics getPnuematics(){
        return pnuematics;
    }
}
