package info.kilchhofer.bfh.robocup.gui.service.hftm.gui;

import info.kilchhofer.bfh.robocup.gui.service.binding.Line;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.layers.LayerArea;
import info.kilchhofer.bfh.robocup.gui.service.hftm.gui.operator.OperatorGroup;
import info.kilchhofer.bfh.robocup.gui.service.binding.CartesianPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Simon Bühlmann
 */
public class GraphicalLaserScanTester extends JFrame implements ActionListener
{
    private static final Logger LOGGER = LogManager.getLogger(GraphicalLaserScanTester.class);

    // gui elements
    private LayerArea layerArea;

    private OperatorGroup laserhandling;
    private OperatorGroup maphandling;

    private JSplitPane horizontalSplit;

    private int scale;

    // action commands
    private final static String ACTION_RUN_SINGLE = "run single";
    private final static String ACTION_START = "start";
    private final static String ACTION_STOP = "stop";
    private final static String ACTION_ZOOM_PLUS = "zoom +";
    private final static String ACTION_ZOOM_MINUS = "zoom -";

    public GraphicalLaserScanTester() throws Exception
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Laserscanner Manager");
        setSize(800, 500);

        // init gui objects
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        layerArea = new LayerArea();

        JPanel leftSplitPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        laserhandling = new OperatorGroup("laserhandling");
        laserhandling.addButton(ACTION_RUN_SINGLE, this);
        laserhandling.addButton(ACTION_START, this);
        laserhandling.addButton(ACTION_STOP, this);

        laserhandling.setIsEnabledButton(ACTION_RUN_SINGLE, false);
        laserhandling.setIsEnabledButton(ACTION_START, false);
        laserhandling.setIsEnabledButton(ACTION_STOP, false);
        menuPanel.add(laserhandling);


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
    }

    public void newMeasurementData(List<CartesianPoint> cartesianPoints)
    {
        layerArea.getMeasurementDataLayer().setScandataSet(cartesianPoints);
        layerArea.repaint();
    }

    public void newExtractedStraightsData(List<Line> lines)
    {
        layerArea.getStraightLayer().setStraights(lines);
        layerArea.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case ACTION_ZOOM_PLUS:
                scale =  layerArea.getGUIReference().getGuiScale() * 2;
                layerArea.getGUIReference().setScale(scale);
                layerArea.repaint();
                break;
                
            case ACTION_ZOOM_MINUS:
                scale = (int)(((float)layerArea.getGUIReference().getGuiScale())/2);
                layerArea.getGUIReference().setScale(scale);
                layerArea.repaint();
                break;
        }

        if (scale < 100 ){
            maphandling.setIsEnabledButton(ACTION_ZOOM_MINUS, false);
            maphandling.setIsEnabledButton(ACTION_ZOOM_PLUS, true);
        } else if (scale > 30000) {
            maphandling.setIsEnabledButton(ACTION_ZOOM_MINUS, true);
            maphandling.setIsEnabledButton(ACTION_ZOOM_PLUS, false);
        } else {
            maphandling.setIsEnabledButton(ACTION_ZOOM_MINUS, true);
            maphandling.setIsEnabledButton(ACTION_ZOOM_PLUS, true);
        }
    }

    public static void main(String[] args) throws Exception
    {
        new GraphicalLaserScanTester();
    }
}
