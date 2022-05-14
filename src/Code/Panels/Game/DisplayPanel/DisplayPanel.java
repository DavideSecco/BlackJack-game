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

        this.add(BorderLayout.LINE_START, labelPanel);
        this.add(BorderLayout.CENTER, betPanel);
        this.add(BorderLayout.LINE_END, optionsPanel);
    }
}