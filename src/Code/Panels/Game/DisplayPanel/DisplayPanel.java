package Code.Panels.Game.DisplayPanel;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel{
    public static LabelPanel labelPanel;
    public static BetPanel betPanel;
    public static OptionsPanel optionsPanel;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(TestApp.gameDimension.width, TestApp.gameDimension.height/10));
        setLayout(new BorderLayout());

        labelPanel = new LabelPanel();
        betPanel = new BetPanel();
        optionsPanel = new OptionsPanel();

        add(BorderLayout.LINE_START, labelPanel);
        add(BorderLayout.CENTER, betPanel);
        add(BorderLayout.LINE_END, optionsPanel);
    }

    public void initialize(){
        labelPanel.initialize();
        betPanel.initialize();
        optionsPanel.initialize();
    }
}