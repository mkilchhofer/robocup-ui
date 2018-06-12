package info.kilchhofer.bfh.robocup.gui.service.binding;

import ch.quantasy.mqtt.gateway.client.message.annotations.AValidator;
import ch.quantasy.mqtt.gateway.client.message.annotations.NonNull;
import ch.quantasy.mqtt.gateway.client.message.annotations.Range;

import java.awt.*;

public class CartesianPoint extends AValidator {
    @NonNull
    private Point point;
    @NonNull
    @Range (from = 0, to = 255)
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
