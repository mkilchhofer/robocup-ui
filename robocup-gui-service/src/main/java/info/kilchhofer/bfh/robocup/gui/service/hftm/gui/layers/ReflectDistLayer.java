package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers;

import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers.helpers.RefPointView;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.references.GUIReference;
import info.kilchhofer.bfh.robocup.gui.service.binding.CartesianPoint;

import java.awt.*;
import java.util.List;

/**
 * @author Simon Bühlmann
 */
public class ReflectDistLayer {

    // Constants
    private final int DIAMETER_REFERENCE_POINT = 10;

    // objects
    private List<CartesianPoint> cartesianPoints;
    private GUIReference guiReference;

    // Constructor
    public ReflectDistLayer(GUIReference guiReference) {
        this.guiReference = guiReference;
    }

    public void setScandataSet(List<CartesianPoint> scandatas) {
        this.cartesianPoints = scandatas;
    }

    public void drawMeasuredResult(Graphics g) {
        RefPointView.drawAbsoluteReferencePoint(guiReference, g);

        if (this.cartesianPoints != null) {
            for (CartesianPoint cartesianPoint : cartesianPoints) {
                this.drawReflectionPoint(cartesianPoint, g, guiReference);
            }
        }
    }

    protected Color getColorOfReflectionPoint(CartesianPoint scandata) {
        return Color.blue;
    }

    // private methods
    private void drawReflectionPoint(CartesianPoint cartesianPoint, Graphics g, GUIReference guiReference) {
        Point newPoint = guiReference.calculatePointInGUI(cartesianPoint.getPoint());

        g.setColor(this.getColorOfReflectionPoint(cartesianPoint));
        g.fillOval(newPoint.x - DIAMETER_REFERENCE_POINT / 2, newPoint.y - DIAMETER_REFERENCE_POINT / 2, DIAMETER_REFERENCE_POINT, DIAMETER_REFERENCE_POINT);
    }
}
