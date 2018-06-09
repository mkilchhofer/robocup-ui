package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import laser.datahandling.lineExtraction.ExtractedLine;
import laser.datahandling.lineExtraction.Straight;
import laser.datahandling.lineExtraction.Vector;
import laser.gui.references.GUIReference;
import laser.references.AbsoluteReferencePoint;

import java.awt.*;
import java.util.List;

/**
 *
 * @author sdb
 */
public class LineExtractionLayer implements ILayer
{

    // objects
    private List<ExtractedLine> straights;
    private GUIReference guiReference;

    private Straight tempStraight; // TEMP!!!!!!

    public LineExtractionLayer(GUIReference guiReference)
    {
        this.guiReference = guiReference;
        this.tempStraight = new Straight(new Vector(-200, 10, 0), new Vector(300, 500, 0));
    }

    @Override
    public void draw(Graphics g)
    {
        if (this.straights != null)
        {
            for (ExtractedLine straight : this.straights)
            {
                this.drawStraight(straight, g);
            }
        }
    }

    // getter & setter
    public void setStraights(List<ExtractedLine> straights)
    {
        this.straights = straights;
    }

    // private methods
    private void drawStraight(ExtractedLine straight, Graphics g)
    {
        Point a = this.guiReference.calculatePointInGUI(
                AbsoluteReferencePoint.getInstance(),
                straight.getStartReflection().getPoint());

        Point b = this.guiReference.calculatePointInGUI(
                AbsoluteReferencePoint.getInstance(),
                straight.getEndReflection().getPoint()
        );

        g.setColor(Color.red);
        g.drawLine(a.x, a.y, b.x, b.y);
    }

}
