package info.kilchhofer.bfh.robocup.gui.service.binding;

import ch.quantasy.mqtt.gateway.client.message.AnIntent;
import ch.quantasy.mqtt.gateway.client.message.annotations.NonNull;
import ch.quantasy.mqtt.gateway.client.message.annotations.Nullable;
import ch.quantasy.mqtt.gateway.client.message.annotations.StringForm;

import java.util.List;

public class GuiIntent extends AnIntent {
    @NonNull
    @StringForm
    public String id;
    @NonNull
    public List<CartesianPoint> positions;

    @Nullable
    public List<Line> lines;


    public GuiIntent(String id, List<CartesianPoint> positions) {
        this.positions = positions;
    }

    public GuiIntent(List<Line> lines) {
        this.lines = lines;
    }

    public GuiIntent() {
    }
}
