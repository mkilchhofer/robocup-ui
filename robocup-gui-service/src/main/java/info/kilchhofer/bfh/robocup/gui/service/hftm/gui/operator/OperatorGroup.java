
package info.kilchhofer.bfh.robocup.gui.service.hftm.gui.operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class OperatorGroup extends JPanel
{
    private static final Dimension BUTTON_DIM = new Dimension(200, 24);
    private List<JButton> buttons;

    public OperatorGroup(String titel)
    {
        this.buttons = new ArrayList<>();
        
        this.setLayout(new GridBagLayout());
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setBorder(BorderFactory.createTitledBorder(titel));
    }
    
    public void addButton(String actionCommand, ActionListener actionListener)
    {
        JButton button = new JButton(actionCommand);
        button.addActionListener(actionListener);
        
        this.buttons.add(button);
        
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 2, 2, 2);

        button.setPreferredSize(BUTTON_DIM);
        button.setMinimumSize(BUTTON_DIM);
        constraints.gridx = 0;
        constraints.gridy = this.buttons.size() - 1;
        this.add(button, constraints);
    }
    
    public void setIsEnabledButton(String actionCommand, boolean isEnabled)
    {
        for(JButton button : this.buttons)
        {
            if(button.getActionCommand().equals(actionCommand))
            {
                button.setEnabled(isEnabled);
            }
        }
    }
    
    public void setIsEnabledAllButtons(boolean isEnabled)
    {
        for(JButton button : this.buttons)
        {
            button.setEnabled(isEnabled);
        }
    }
}
