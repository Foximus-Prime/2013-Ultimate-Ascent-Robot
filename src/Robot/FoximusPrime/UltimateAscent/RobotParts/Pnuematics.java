/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author Team 3695
 */
public class Pnuematics extends UltimateAscentRobotPart{
    
        //private DigitalInput pSwitch;// = new DigitalInput(1);
        //private Relay        compressor;
    
        private Compressor   compressor;
        
        public Pnuematics(UltimateAscentRobot robot){
            super(robot);
            compressor = new Compressor(1,1);
            //compressor = new Relay(1);
            //compressor.setDirection(Relay.Direction.kForward);
            //pSwitch    = new DigitalInput(1);            
        }
        
       
        
        public void updateTeleop(){
            SmartDashboard.putBoolean("Compressing", !compressor.getPressureSwitchValue());
            //if(!pSwitch.get())
            //    compressor.set(Relay.Value.kOn);
            //else
            //    compressor.set(Relay.Value.kOff);
            
            
        }
        
        public void compressorOn(){
            compressor.start();
            
            
            
        }
        
        public void compressorOff(){
            compressor.stop();
            
            
        }    
}
