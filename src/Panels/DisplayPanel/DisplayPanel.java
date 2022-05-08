package Panels.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Main.TestApp.*;
import static Panels.ControlPanel.ActionPanel.hitButton;
import static Panels.ControlPanel.ActionPanel.standButton;

public class DisplayPanel extends JPanel implements ActionListener {
    private JLabel label;
    public static BetPanel betPanel;
    public static OptionsPanel optionsPanel;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/10));
        setLayout(new BorderLayout());

        label = new JLabel();
        inizialize();

        betPanel = new BetPanel();

        optionsPanel = new OptionsPanel();

        this.add(BorderLayout.LINE_START, label);
        this.add(BorderLayout.CENTER, betPanel);
        this.add(BorderLayout.LINE_END, optionsPanel);
    }

    public void inizialize(){
        label.setText("Pesca una carta o rimani con quelle che hai (in realtà prima devi purntare)");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createBevelBorder(1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == standButton){
            if(whoWon() == 1){
                if(checkBlackjack())
                    label.setText("Complimenti, hai fatto BlackJack");
                else
                    label.setText("Hai vinto");
            }

            else if(whoWon() == 0)
                label.setText("Pareggio");
            else
                label.setText("Hai perso");

            optionsPanel.newGame.setEnabled(true);
        }

        if(e.getSource() == hitButton){
            if(player.isBust())
                label.setText("Hai Sballato");
        }
    }
}