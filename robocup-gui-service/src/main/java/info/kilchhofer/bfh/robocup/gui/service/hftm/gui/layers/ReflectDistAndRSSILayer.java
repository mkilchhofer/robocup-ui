
package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import info.kilchhofer.bfh.robocup.gui.service.binding.CartesianPoint;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers.helpers.ColorCalc;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.references.GUIReference;
import org.apache.logging.log4j.LogManager;

import java.awt.*;

/**
 *
 * @author sdb
 */
public class ReflectDistAndRSSILayer extends ReflectDistLayer
{
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ReflectDistAndRSSILayer.class);
    private ColorCalc colorBar;
    
    public ReflectDistAndRSSILayer(GUIReference guiReference, ColorCalc colorBar)
    {
        super(guiReference);
        
        this.colorBar = colorBar;
    }

    @Override
    protected Color getColorOfReflectionPoint(CartesianPoint scandata)
    {
        try
        {
            return this.colorBar.calculateColor(scandata.getReflection());
        }
        catch (Exception ex)
        {
            LOGGER.error("Exception during getColorOfReflectionPoint occurred:", ex);
            return Color.black;
        }
    }
}
