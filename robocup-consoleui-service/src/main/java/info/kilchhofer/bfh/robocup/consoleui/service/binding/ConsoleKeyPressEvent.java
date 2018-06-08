package info.kilchhofer.bfh.robocup.consoleui.service.binding;

import ch.quantasy.mqtt.gateway.client.message.AnEvent;
import ch.quantasy.mqtt.gateway.client.message.annotations.StringForm;
import ch.quantasy.mqtt.gateway.client.message.annotations.StringSize;

public class ConsoleKeyPressEvent extends AnEvent{
    @StringForm
    @StringSize (min=1, max=1)
    public Character character;

    public ConsoleKeyPressEvent(Character character){
        this.character = character;
    }

    public ConsoleKeyPressEvent() {
    }
}
