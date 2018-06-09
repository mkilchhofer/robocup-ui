package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import laser.datahandling.coord.CoordScanData;
import laser.gui.layers.helpers.RefPointView;
import laser.gui.references.GUIReference;
import laser.references.AbsoluteReferencePoint;

import java.awt.*;
import java.util.List;

/**
 *
 * @author Simon Bühlmann
 */
public class ReflectDistLayer
{

    // Constantes
    private final int DIAMETER_REFERENCE_POINT = 3;

    // objects
    private List<CoordScanData> scandatas;
    private GUIReference guiReference;

    // Constructor
    public ReflectDistLayer(GUIReference guiReference)
    {
        this.guiReference = guiReference;
    }

    public void setScandataSet(List<CoordScanData> scandatas)
    {
        this.scandatas = scandatas;
    }
    
    public void drawMeasuredResult(Graphics g)
    {
        RefPointView.drawAbsoluteReferencePoint(guiReference, AbsoluteReferencePoint.getInstance(), g);
        
        if (this.scandatas != null)
        {
            for (CoordScanData scandata : scandatas)
            {
                this.drawReflectionPoint(scandata, g, guiReference);
            }
        }
    }
    
    protected Color getColorOfReflectionPoint(CoordScanData scandata)
    {
        return Color.blue;
    }
    
    // private methods
    private void drawReflectionPoint(CoordScanData scandata, Graphics g, GUIReference guiReference)
    {
        Point newPoint = guiReference.calculatePointInGUI(AbsoluteReferencePoint.getInstance(), scandata.getPoint());

        g.setColor(this.getColorOfReflectionPoint(scandata));
        g.fillOval(newPoint.x - DIAMETER_REFERENCE_POINT / 2, newPoint.y - DIAMETER_REFERENCE_POINT / 2, DIAMETER_REFERENCE_POINT, DIAMETER_REFERENCE_POINT);
    }
}
