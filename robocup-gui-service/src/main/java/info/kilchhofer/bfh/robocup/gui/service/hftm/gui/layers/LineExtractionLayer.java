package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.references.GUIReference;
import info.kilchhofer.bfh.robocup.gui.service.binding.Line;

import java.awt.*;
import java.util.List;

/**
 *
 * @author sdb
 * refactored mkilchhofer
 */
public class LineExtractionLayer implements ILayer
{

    // objects
    private List<Line> lines;
    private GUIReference guiReference;

    public LineExtractionLayer(GUIReference guiReference)
    {
        this.guiReference = guiReference;
    }

    @Override
    public void draw(Graphics g)
    {
        if (this.lines != null)
        {
            for (Line line : this.lines)
            {
                this.drawStraight(line.getStartPoint(), line.getStopPoint(), g);
            }
        }
    }

    // getter & setter
    public void setStraights(List<Line> lines)
    {
        this.lines = lines;
    }

    // private methods
    private void drawStraight(Point startPoint, Point endPoint, Graphics g)
    {
        Point a = this.guiReference.calculatePointInGUI(startPoint);
        Point b = this.guiReference.calculatePointInGUI(endPoint);

        g.setColor(Color.red);
        g.drawLine(a.x, a.y, b.x, b.y);
    }
}
