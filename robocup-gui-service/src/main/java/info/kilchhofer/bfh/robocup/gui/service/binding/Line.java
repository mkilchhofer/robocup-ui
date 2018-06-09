package info.kilchhofer.bfh.robocup.gui.service.binding;

import java.awt.*;

public class Line {
    private Point startPoint,stopPoint;

    public Line(Point startPoint, Point stopPoint){
        this.startPoint = startPoint;
        this.stopPoint = stopPoint;
    }

    // deserialization
    public Line(){
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getStopPoint() {
        return stopPoint;
    }
}
