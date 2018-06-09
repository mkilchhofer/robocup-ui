package info.kilchhofer.bfh.robocup.gui.service.binding;

import ch.quantasy.mqtt.gateway.client.contract.AyamlServiceContract;
import ch.quantasy.mqtt.gateway.client.message.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static info.kilchhofer.bfh.robocup.common.Constants.ROBOCUP_ROOT_CONTEXT;

public class GuiServiceContract extends AyamlServiceContract {
    private static final Logger LOGGER = LogManager.getLogger(GuiServiceContract.class);

    public GuiServiceContract(String instanceID) {
        super(ROBOCUP_ROOT_CONTEXT, "Gui", instanceID);
    }

    @Override
    public void setMessageTopics(Map<String, Class<? extends Message>> messageTopicMap) {
        messageTopicMap.put(INTENT, GuiIntent.class);
    }
}
