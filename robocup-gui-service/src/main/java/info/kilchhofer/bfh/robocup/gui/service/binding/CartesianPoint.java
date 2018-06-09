package info.kilchhofer.bfh.robocup.gui.service.binding;

import java.awt.*;

public class CartesianPoint {
    private Point point;
    private int reflection;

    public CartesianPoint(int x, int y, int reflection) {
        this.point = new Point(x, y);
        this.reflection = reflection;
    }

    // needed for deserialization
    public CartesianPoint() {
    }

    public Point getPoint() {
        return point;
    }

    public int getReflection() {
        return reflection;
    }
}
