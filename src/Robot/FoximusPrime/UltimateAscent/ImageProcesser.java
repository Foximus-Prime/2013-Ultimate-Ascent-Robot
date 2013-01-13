/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author edward
 */
public class ImageProcesser extends UltimateAscentRobotPart {
    AxisCamera camera;       
    
    public ImageProcesser(UltimateAscentRobot robot){
        super(robot);
        camera = AxisCamera.getInstance();    
        camera.writeCompression(40);
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        camera.writeExposureControl(AxisCamera.ExposureT.automatic);
        camera.writeRotation(AxisCamera.RotationT.k0);
    }
}
