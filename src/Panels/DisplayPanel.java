package Panels;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JLabel label;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(1280, 60));
        JLabel label = new JLabel("Qui è dove andranno i messaggi");

        this.add(label);
    }
}
