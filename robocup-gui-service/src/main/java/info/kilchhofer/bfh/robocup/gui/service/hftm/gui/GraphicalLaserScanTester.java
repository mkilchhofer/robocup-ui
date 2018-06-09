package info.kilchhofer.bfh.robocup.gui.service.hftm.gui;

import laser.datahandling.coord.CoordScanData;
import laser.datahandling.lineExtraction.ExtractedLine;
import laser.gui.layers.LayerArea;
import laser.gui.model.ILaserScanFascadeListener;
import laser.gui.model.LaserScanException;
import laser.gui.model.LaserScanFascade;
import laser.gui.operator.OperatorGroup;
import laser.gui.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Bühlmann
 */
public class GraphicalLaserScanTester extends JFrame implements ILaserScanFascadeListener, ActionListener
{

    // modell
    public  LaserScanFascade laserscanner;

    // gui elements
    private  LayerArea layerArea;

    private OperatorGroup manager;
    private OperatorGroup laserhandling;
    private OperatorGroup lineExtraction;
    private OperatorGroup maphandling;

    private JSplitPane horizontalSplit;
    
    private int zoomStep;
    private double zoomFactor;

    // action commands
    private final static String ACTION_SETTINGS = "settings";
    private final static String ACTION_CONNECT = "connect";
    private final static String ACTION_DISCONNECT = "disconnect";

    private final static String ACTION_RUN_SINGLE = "run single";
    private final static String ACTION_START = "start";
    private final static String ACTION_STOP = "stop";

    private final static String ACTION_EXTRACT_LINES = "extract lines";

    private final static String ACTION_ZOOM_PLUS = "zoom +";
    private final static String ACTION_ZOOM_MINUS = "zoom -";

    public GraphicalLaserScanTester() throws Exception
    {
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        laserscanner = new LaserScanFascade();
        laserscanner.addListener(this);
        setTitle("Laserscanner Manager");
        setSize(800, 500);

        // init gui objects
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        layerArea = new LayerArea();

        JPanel leftSplitPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // init operator groups
        manager = new OperatorGroup("Manager");
        manager.addButton(ACTION_SETTINGS, this);
        manager.addButton(ACTION_CONNECT, this);
        manager.addButton(ACTION_DISCONNECT, this);
        menuPanel.add(manager);

        laserhandling = new OperatorGroup("laserhandling");
        laserhandling.addButton(ACTION_RUN_SINGLE, this);
        laserhandling.addButton(ACTION_START, this);
        laserhandling.addButton(ACTION_STOP, this);
        menuPanel.add(laserhandling);

        lineExtraction = new OperatorGroup("line extraction");
        lineExtraction.addButton(ACTION_EXTRACT_LINES, this);
        menuPanel.add(lineExtraction);

        maphandling = new OperatorGroup("map handling");
        maphandling.addButton(ACTION_ZOOM_PLUS, this);
        maphandling.addButton(ACTION_ZOOM_MINUS, this);
        menuPanel.add(maphandling);

        leftSplitPanel.add(menuPanel, BorderLayout.PAGE_START);

        horizontalSplit.setLeftComponent(leftSplitPanel);
        horizontalSplit.setRightComponent(layerArea);

        add(horizontalSplit);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        // init other objects
        new Settings(laserscanner, true);
        zoomStep = 200;
        zoomFactor = 0.2;
        laserConnectionChanged(false);
    }

    @Override
    public void newMeasurementData(List<CoordScanData> datas)
    {
        layerArea.getMeasurementDataLayer().setScandataSet(datas);
        layerArea.repaint();
    }

    @Override
    public void laserConnectionChanged(boolean isConnected)
    {
        manager.setIsEnabledButton(ACTION_CONNECT, !isConnected);
        manager.setIsEnabledButton(ACTION_DISCONNECT, isConnected);
        
        laserhandling.setIsEnabledAllButtons(isConnected);
    }

    @Override
    public void newExtractedStraightsData(List<ExtractedLine> datas)
    {
        layerArea.getStraightLayer().setStraights(datas);
        layerArea.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case ACTION_SETTINGS:
                new Settings(laserscanner, !laserscanner.isConnected());
                break;

            case ACTION_CONNECT:
                try
                {
                    laserscanner.connect();
                }
                catch (LaserScanException ex)
                {
                    JOptionPane.showMessageDialog(null, "Can not connect", "Connection error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(GraphicalLaserScanTester.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case ACTION_DISCONNECT:
                laserscanner.disconnect();
                break;

            case ACTION_RUN_SINGLE:
                try
                {
                    laserscanner.runSingle();
                }
                catch (LaserScanException ex)
                {
                    Logger.getLogger(GraphicalLaserScanTester.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
                
            case ACTION_START:
                laserscanner.startCont();
                break;
                
            case ACTION_STOP:
                laserscanner.stopCont();
                break;
                
            case ACTION_EXTRACT_LINES:
                laserscanner.calculateStraights();
                break;
                
            case ACTION_ZOOM_PLUS:
                layerArea.getGUIReference().setScale((int)(((float)layerArea.getGUIReference().getGuiScale())*2));
                layerArea.repaint();
                break;
                
            case ACTION_ZOOM_MINUS:
                layerArea.getGUIReference().setScale((int)(((float)layerArea.getGUIReference().getGuiScale())/2));
                layerArea.repaint();
                break;
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        new GraphicalLaserScanTester();
    }
}
