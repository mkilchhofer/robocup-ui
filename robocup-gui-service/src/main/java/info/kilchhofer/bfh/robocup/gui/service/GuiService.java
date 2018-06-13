package info.kilchhofer.bfh.robocup.gui.service;

import ch.quantasy.mqtt.gateway.client.GatewayClient;
import info.kilchhofer.bfh.robocup.common.LoggerSettings;
import info.kilchhofer.bfh.robocup.gui.service.binding.GuiIntent;
import info.kilchhofer.bfh.robocup.gui.service.binding.GuiServiceContract;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.GraphicalLaserScanTester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

public class GuiService {
    private final GatewayClient<GuiServiceContract> gatewayClient;
    private static final Logger LOGGER = LogManager.getLogger(GuiService.class);

    public GuiService(URI mqttURI, String mqttClientName, String instanceName) throws Exception {
        this.gatewayClient = new GatewayClient<GuiServiceContract>(mqttURI, mqttClientName, new GuiServiceContract(instanceName));

        GraphicalLaserScanTester gui = new GraphicalLaserScanTester();

        this.gatewayClient.connect();
        LOGGER.info("Instance CANONICAL_TOPIC topic: '{}'", gatewayClient.getContract().CANONICAL_TOPIC);
        this.gatewayClient.subscribe(gatewayClient.getContract().INTENT + "/#", (topic, payload) -> {

            for(GuiIntent intent : gatewayClient.toMessageSet(payload, GuiIntent.class)){
                LOGGER.debug("Received Intent: {}", intent);
                if(intent.positions != null){
                    gui.newMeasurementData(intent.positions);
                }

                if(intent.lines != null){
                    gui.newExtractedStraightsData(intent.lines);
                }

                if(intent.loggerSettings != null){
                    intent.loggerSettings.configure();
                }
            }
        });
    }
}
