package info.kilchhofer.bfh.robocup.consoleui.service.binding;

import ch.quantasy.mqtt.gateway.client.message.AnIntent;
import ch.quantasy.mqtt.gateway.client.message.annotations.Nullable;
import ch.quantasy.mqtt.gateway.client.message.annotations.StringForm;
import info.kilchhofer.bfh.robocup.common.LoggerSettings;

public class ConsoleIntent extends AnIntent{
    @Nullable
    public LoggerSettings loggerSettings;

    @Nullable
    @StringForm
    public String consoleMessage;

    public ConsoleIntent(String consoleMessage){
        this.consoleMessage = consoleMessage;
    }

    public ConsoleIntent(){
    }
}
