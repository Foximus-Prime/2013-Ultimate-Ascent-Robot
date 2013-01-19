/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.FoximusPrime.UltimateAscent.RobotParts;

import Robot.FoximusPrime.UltimateAscent.UltimateAscentRobot;
import com.sun.squawk.util.SquawkVector;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
/**
 *
 * @author edward
 */
public class ImageProcesser extends UltimateAscentRobotPart {
    AxisCamera camera;       
    
    ParticleAnalysisReport highGoal;
    ParticleAnalysisReport leftMiddleGoal;
    ParticleAnalysisReport rightMiddleGoal;
    ParticleAnalysisReport lowGoal;
    ParticleAnalysisReport leftSeparator;
    ParticleAnalysisReport rightSeparator;
    ParticleAnalysisReport post;
    ParticleAnalysisReport bar;    

    double HIGHGOALRATIO = 3.1;
    double MIDDLEGOALRATIO = 2.1379310344827586206896551724138;
    double LOWGOALRATIO = 1.15625;
    double SEPARATORRATIO = 8;
    double POSTRATIO = 13.33333333;
    double BARRATIO = 2.66666666;

    double ERRORACCEPT = .05;
    
    public ImageProcesser(UltimateAscentRobot robot){
        super(robot);
        camera = AxisCamera.getInstance("10.36.95.11");    
        camera.writeCompression(40);
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        camera.writeExposureControl(AxisCamera.ExposureT.automatic);
        camera.writeRotation(AxisCamera.RotationT.k0);
        
        highGoal = null;
        leftMiddleGoal = null;
        rightMiddleGoal = null;
        lowGoal = null;
        leftSeparator = null;
        rightSeparator = null;
        post = null;
        bar = null;
    }
    
    public void findFieldFeatures(){        
        try {
            highGoal = null;
            leftMiddleGoal = null;
            rightMiddleGoal = null;
            lowGoal = null;
            leftSeparator = null;
            rightSeparator = null;
            post = null;
            bar = null;
            
            ColorImage img = camera.getImage();
            BinaryImage thresholdImg = img.thresholdHSV(0, 255, 0, 255, 235, 255);
            ParticleAnalysisReport[] blobs = thresholdImg.getOrderedParticleAnalysisReports();
            
            SquawkVector highCandidates = new SquawkVector();
            SquawkVector middleCandidates = new SquawkVector();
            SquawkVector lowCandidates = new SquawkVector();
            SquawkVector separatorCandidates = new SquawkVector();
            SquawkVector postCandidates = new SquawkVector();
            SquawkVector barCandidates = new SquawkVector();
            
            for(int i = 0; i < blobs.length; i++){
                if(isCandidate(blobs[i], HIGHGOALRATIO, ERRORACCEPT)) {
                    highCandidates.addElement(blobs[i]);
                }
                else if(isCandidate(blobs[i], MIDDLEGOALRATIO, ERRORACCEPT)) {
                    middleCandidates.addElement(blobs[i]);
                }
                else if(isCandidate(blobs[i], LOWGOALRATIO, ERRORACCEPT)) {
                    lowCandidates.addElement(blobs[i]);
                }
                else if(isCandidate(blobs[i], SEPARATORRATIO, ERRORACCEPT)) {
                    separatorCandidates.addElement(blobs[i]);
                }
                else if(isCandidate(blobs[i], POSTRATIO, ERRORACCEPT)) {
                    postCandidates.addElement(blobs[i]);
                }
                else if(isCandidate(blobs[i], BARRATIO, ERRORACCEPT)) {
                    barCandidates.addElement(blobs[i]);
                }
            }
                        
        } catch (AxisCameraException ex) {
        } catch (NIVisionException ex) { }
    }
    
    private boolean isCandidate(ParticleAnalysisReport blob, double ratio, double error){
        return blob.boundingRectWidth/blob.boundingRectHeight < ratio*(1+error) 
                && blob.boundingRectWidth/blob.boundingRectHeight > ratio*(1-error);
    }
    private void sortReports(SquawkVector blobs, double ratio){
        for(int i = 0; i < blobs.size(); i++){
            int best = i;
            for(int j = i + 1; j < blobs.size(); j++){
               if(Math.abs(ratio-((ParticleAnalysisReport)blobs.elementAt(j)).boundingRectWidth / ((ParticleAnalysisReport)blobs.elementAt(j)).boundingRectHeight) 
                < Math.abs(ratio-((ParticleAnalysisReport)blobs.elementAt(best)).boundingRectWidth / ((ParticleAnalysisReport)blobs.elementAt(best)).boundingRectHeight)) {
                    best = j;
                }
            }
            Object tmp = blobs.elementAt(i);
            blobs.setElementAt(blobs.elementAt(best), i);
            blobs.setElementAt(tmp, best);
        }
    }
}
