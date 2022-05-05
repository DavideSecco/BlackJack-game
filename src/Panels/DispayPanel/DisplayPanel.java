package Panels.DispayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.TestApp.*;
import static Panels.ControlPanel.ActionPanel.hitButton;
import static Panels.ControlPanel.ActionPanel.standButton;

public class DisplayPanel extends JPanel implements ActionListener {
    private JLabel label;
    public static BetPanel betPanel;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/10));
        setLayout(new GridLayout(1,2));

        label = new JLabel("Pesca una carta o rimani con quelle che hai (in realtàa prima devi purntare)", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createBevelBorder(1));
        betPanel = new BetPanel();

        this.add(label);
        this.add(betPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == standButton){
            if(whoWon() == 1)
                label.setText("Hai vinto");
            else if(whoWon() == 0)
                label.setText("Pareggio");
            else
                label.setText("Hai perso");
        }

        if(e.getSource() == hitButton){
            if(player.isBust())
                label.setText("Hai Sballato");
        }
    }
}