package Panels;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton hitButton;
    private JButton standButton;

    public ControlPanel(){
        super();
        setPreferredSize(new Dimension(1280, 60));
        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");

        JPanel p1 = new JPanel(new GridLayout(1, 3));
        p1.add(hitButton);
        p1.add(standButton);

        this.add(p1);
    }
}
