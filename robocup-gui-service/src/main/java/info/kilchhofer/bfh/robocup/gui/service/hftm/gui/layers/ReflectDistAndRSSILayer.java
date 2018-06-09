
package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import laser.datahandling.coord.CoordScanData;
import laser.gui.layers.helpers.ColorCalc;
import laser.gui.references.GUIReference;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class ReflectDistAndRSSILayer extends ReflectDistLayer
{
    private ColorCalc colorBar;
    
    public ReflectDistAndRSSILayer(GUIReference guiReference, ColorCalc colorBar)
    {
        super(guiReference);
        
        this.colorBar = colorBar;
    }

    @Override
    protected Color getColorOfReflectionPoint(CoordScanData scandata)
    {
        try
        {
            return this.colorBar.calculateColor(scandata.getReflection());
        }
        catch (Exception ex)
        {
            Logger.getLogger(ReflectDistAndRSSILayer.class.getName()).log(Level.SEVERE, null, ex);
            return Color.black;
        }
    }
}
