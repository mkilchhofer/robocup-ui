
package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.references;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * Describes, how the GUI-Elements is positioned to the absolute null point and the diameter on the GUI.
 * @author simon.buehlmann
 */
public class GUIReference
{
    private static final Logger LOGGER = LogManager.getLogger(GUIReference.class);
    private int xPos, yPos;
    private int scaleFactor;
    private int guiWidth, guiHeight;
    
    /**
     * 
     * @param xPos Distance in pixels from the left top corner of the gui to the reference
     * @param yPos Distance in pixels from the left top corner of the gui to the reference
     * @param scale scale factor in 1000 / x
     * @param width width from gui in pixels
     * @param height height from gui in pixels
     */
    public GUIReference(int xPos, int yPos, int scale, int width, int height)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.scaleFactor = scale;
        this.guiWidth = width;
        this.guiHeight = height;
    }
    
    public Point calculatePointInGUI(Point point)
    {
        int tempX = calculateMillimeterToPixel(point.x);
        int tempY = calculateMillimeterToPixel(0 - point.y);
        LOGGER.trace("this.xPos: {}, this.yPos: {}", this.xPos, this.yPos);
        LOGGER.trace("tempX: {}, tempY: {}", tempX, tempY);
        return new Point(this.xPos + tempX, this.yPos + tempY);
    }
    
    public int calculateMillimeterToPixel(int mm)
    {
        float realValue = (((float)(mm * this.scaleFactor)) / ((float)1000));
        
        return (int)realValue;
    }
    
    public int calculatePixelToMillimeter(int px)
    {
        float realValue =(((float)(px * 1000)) / ((float)this.scaleFactor));
        
        return (int)realValue;
    }
    
    // getter
    public int getGuiScale()
    {
        return scaleFactor;
    }
    
    public int getGuiWidth()
    {
        return guiHeight;
    }
    
    public int getGuiLength()
    {
        return guiWidth;
    }
    
    // setter
    public void setXPos(int xPos)
    {
        this.xPos = xPos;
    }

    public void setYPos(int yPos)
    {
        this.yPos = yPos;
    }

    public void setScale(int scale)
    {
        LOGGER.trace("ScaleFactor: {}", this.scaleFactor);
        this.scaleFactor = scale;
    }

    public void setGUIWidth(int guiWidth)
    {
        this.guiWidth = guiWidth;
    }

    public void setGUIHeight(int guiHeight)
    {
        this.guiHeight = guiHeight;
    }
    
    public static void main(String[] args)
    {
        GUIReference ref = new GUIReference(10, 10, 1100, 100, 100);
        
        int calculatePixelToMillimeter = ref.calculatePixelToMillimeter(200);
        System.out.println(calculatePixelToMillimeter);
        
        int calculateMillimeterToPixel = ref.calculateMillimeterToPixel(calculatePixelToMillimeter);
        System.out.println(calculateMillimeterToPixel);
    }
}
