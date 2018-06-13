package info.kilchhofer.bfh.robocup.gui.service.binding;

import ch.quantasy.mqtt.gateway.client.message.AnIntent;
import ch.quantasy.mqtt.gateway.client.message.annotations.NonNull;
import ch.quantasy.mqtt.gateway.client.message.annotations.Nullable;
import ch.quantasy.mqtt.gateway.client.message.annotations.StringForm;
import info.kilchhofer.bfh.robocup.common.LoggerSettings;

import java.util.List;

public class GuiIntent extends AnIntent {
    @NonNull
    @StringForm
    public String id;
    @NonNull
    public List<CartesianPoint> positions;

    @Nullable
    public List<Line> lines;

    @Nullable
    public LoggerSettings loggerSettings;


    public GuiIntent(String id, List<CartesianPoint> positions) {
        this.positions = positions;
    }

    public GuiIntent(List<Line> lines) {
        this.lines = lines;
    }

    public GuiIntent(LoggerSettings loggerSettings) {
        this.loggerSettings = loggerSettings;
    }

    public GuiIntent() {
    }
}
