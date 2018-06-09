package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.references.GUIReference;

import java.awt.*;

/**
 * @author Simon Bühlmann
 * refactored mkilchhofer
 */
public class MapLayer implements ILayer {
    private GUIReference guiReference;

    public MapLayer(GUIReference guiReference) {
        this.guiReference = guiReference;
    }

    @Override
    public void draw(Graphics g) {
        this.drawCirclePixelDiameter(g, 200);
        this.drawCirclePixelDiameter(g, 400);
        this.drawCirclePixelDiameter(g, 600);
        this.drawCirclePixelDiameter(g, 800);
        this.drawCirclePixelDiameter(g, 1000);
    }

    // private methods

    /**
     * @param g
     * @param diameter unscaled!
     */
    private void drawCirclePixelDiameter(Graphics g, int diameter) {
        Point guiReferencePoint = guiReference.calculatePointInGUI(new Point(0, 0));

        g.setColor(Color.BLACK);
        g.drawOval(guiReferencePoint.x - diameter / 2, guiReferencePoint.y - diameter / 2, diameter, diameter);
        int radius = guiReference.calculatePixelToMillimeter(diameter / 2);

        g.drawString(radius + "mm", guiReferencePoint.x, guiReferencePoint.y - diameter / 2);
    }
}
