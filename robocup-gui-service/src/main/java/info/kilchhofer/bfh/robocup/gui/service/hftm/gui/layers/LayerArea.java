
package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import laser.gui.layers.helpers.ColorCalc;
import laser.gui.references.GUIReference;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Simon Bühlmann
 */
public class LayerArea extends JPanel
{
    private GUIReference guiReference;
    
    // layers
    private ReflectDistLayer measurementDataLayer;
    private ReflectDistAndRSSILayer distanceAndRSSILayer;
    private MapLayer mapLayer;
    private ColorCalc colorBar;
    private LineExtractionLayer straightLayer;
    
    public LayerArea()
    {
        this.setSize(500, 500);
        this.setBackground(Color.gray);
        this.setLayout(null);
        this.setVisible(true);
        
        this.guiReference = new GUIReference(250, 250, 1000, 500, 500);
        this.measurementDataLayer = new ReflectDistLayer(this.guiReference);
        this.mapLayer = new MapLayer(this.guiReference);
        this.colorBar = new ColorCalc();
        this.distanceAndRSSILayer = new ReflectDistAndRSSILayer(guiReference, colorBar);
        this.straightLayer = new LineExtractionLayer(guiReference);
    }
    
    // getter
    public ReflectDistLayer getMeasurementDataLayer()
    {
        //return measurementDataLayer; // TEMP !!!!
        return this.distanceAndRSSILayer;
    }
    
    public LineExtractionLayer getStraightLayer()
    {
        return this.straightLayer;
    }
    
    public GUIReference getGUIReference()
    {
        return this.guiReference;
    }
    
    @Override
    public void paint(Graphics g)
    {
        this.guiReference.setGUIHeight(this.getSize().height);
        this.guiReference.setGUIWidth(this.getSize().width);
        this.guiReference.setXPos(this.getSize().width/2);
        this.guiReference.setYPos(this.getSize().height/2);
        
        super.paint(g);
        //this.measurementDataLayer.drawMeasuredResult(g); // TEMP !!!!
        this.distanceAndRSSILayer.drawMeasuredResult(g);
        this.mapLayer.draw(g);
        //this.colorBar.draw(g); // TEMP !!!!
        this.straightLayer.draw(g);
    }
}
