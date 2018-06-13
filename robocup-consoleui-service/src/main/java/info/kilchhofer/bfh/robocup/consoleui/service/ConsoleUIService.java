package info.kilchhofer.bfh.robocup.consoleui.service;

import ch.quantasy.mqtt.gateway.client.GatewayClient;
import ch.quantasy.mqtt.gateway.client.message.MessageReceiver;
import info.kilchhofer.bfh.robocup.consoleui.service.binding.ConsoleIntent;
import info.kilchhofer.bfh.robocup.consoleui.service.binding.ConsoleKeyPressEvent;
import info.kilchhofer.bfh.robocup.consoleui.service.binding.ConsoleUIServiceContract;
import info.kilchhofer.bfh.robocup.consoleui.service.helper.IKeyPressListener;
import info.kilchhofer.bfh.robocup.consoleui.service.helper.KeyPressHandler;
import org.apache.logging.log4j.LogManager;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.net.URI;

public class ConsoleUIService {

    private final GatewayClient<ConsoleUIServiceContract> gatewayClient;
    private KeyPressHandler keyPressHandler;
    private MessageReceiver intentReceiver;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GatewayClient.class);

    public ConsoleUIService(URI mqttURI, String mqttClientName, String instanceName) throws MqttException, IOException {

        this.gatewayClient = new GatewayClient<>(mqttURI, mqttClientName, new ConsoleUIServiceContract(instanceName));

        this.intentReceiver = new MessageReceiver() {
            @Override
            public void messageReceived(String topic, byte[] payload) throws Exception {
                for(ConsoleIntent intent : gatewayClient.toMessageSet(payload, ConsoleIntent.class)){
                    LOGGER.debug("Received Intent: {}", intent);
                    if (intent.consoleMessage != null){
                        System.out.println(intent.consoleMessage);
                    }

                    if (intent.loggerSettings != null){
                        intent.loggerSettings.configure();
                    }
                }
            }
        };
        this.gatewayClient.connect();
        LOGGER.info("Instance CANONICAL_TOPIC topic: '{}'", gatewayClient.getContract().CANONICAL_TOPIC);
        this.gatewayClient.subscribe(gatewayClient.getContract().INTENT + "/#", this.intentReceiver);

        // Console Handling
        IKeyPressListener keyPressListener = new IKeyPressListener() {
            @Override
            public void keyPressed(Character character) {
                gatewayClient.readyToPublish(gatewayClient.getContract().EVENT_KEYPRESS, new ConsoleKeyPressEvent(character));
            }
        };
        keyPressHandler = new KeyPressHandler(keyPressListener);
        Thread keyPressHandlerThread = new Thread(keyPressHandler);
        keyPressHandlerThread.start();
    }
}
