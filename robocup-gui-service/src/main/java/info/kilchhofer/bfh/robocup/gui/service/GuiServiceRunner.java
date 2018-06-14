package info.kilchhofer.bfh.robocup.gui.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.UnknownHostException;

public class GuiServiceRunner {
    private static final Logger LOGGER = LogManager.getLogger(GuiServiceRunner.class);
    private static String computerName;

    static {
        LOGGER.info("Headless: " + java.awt.GraphicsEnvironment.isHeadless());
        try {
            computerName = java.net.InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            LOGGER.error("undefined hostname", ex);
            computerName = "undefined";
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Loglevel= " + LOGGER.getLevel());
        URI mqttURI = URI.create("tcp://127.0.0.1:1883");
        if (args.length > 0) {
            mqttURI = URI.create(args[0]);
        } else {
            LOGGER.info("Per default, 'tcp://127.0.0.1:1883' is chosen. You can provide another address as first argument i.e.: tcp://iot.eclipse.org:1883");
        }
        LOGGER.info(mqttURI + " will be used as broker address.");

        Long timeStamp = System.currentTimeMillis();
        String instanceName = (timeStamp % 10000) + "@" + computerName;

        new GuiService(mqttURI, "gui-" + instanceName, "gui-" + instanceName);
    }
}
