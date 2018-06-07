package info.kilchhofer.bfh.robocup.consoleuiservice.helper;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

public class KeyPressHandler implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(KeyPressHandler.class);
    private IKeyPressListener keyPressCallback;
    private Terminal terminal;

    public KeyPressHandler(IKeyPressListener keyPressCallback) throws IOException {
        this.terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        this.keyPressCallback = keyPressCallback;
    }

    @Override
    public void run() {
        while (true) {
            KeyStroke key = null;
            try {
                key = terminal.pollInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (key == null) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (key.getCharacter() == 'q'){
                LOGGER.info("'q' Pressed. Exiting...");
                System.exit(0);
            } else {
                LOGGER.info("Pressed '{}'. String Length: {}", key.getCharacter(), key.getCharacter().toString().length());
                keyPressCallback.keyPressed(key.getCharacter());
            }
        }
    }
}
