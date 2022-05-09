package Panels.Game.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.TestApp.*;
import static Panels.Game.ControlPanel.ActionPanel.hitButton;
import static Panels.Game.ControlPanel.ActionPanel.standButton;
import static Panels.Game.ControlPanel.FichesPanel.confirm;

public class DisplayPanel extends JPanel implements ActionListener {
    private JLabel label;

    public static JPanel labelPanel;
    public static BetPanel betPanel;
    public static OptionsPanel optionsPanel;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(gameDimension.width, gameDimension.height/10));
        setLayout(new BorderLayout());

        labelPanel = new JPanel();
        labelPanel.setPreferredSize(new Dimension(gameDimension.width/4, gameDimension.height/10));
        label = new JLabel();
        inizialize();

        labelPanel.add(label);

        betPanel = new BetPanel();

        optionsPanel = new OptionsPanel();

        this.add(BorderLayout.LINE_START, labelPanel);
        this.add(BorderLayout.CENTER, betPanel);
        this.add(BorderLayout.LINE_END, optionsPanel);
    }

    public void inizialize(){
        label.setText("Punta");
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == standButton){
            optionsPanel.newGame.setEnabled(true);
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
        }

        if(e.getSource() == hitButton){
            if(player.isBust()){
                optionsPanel.newGame.setEnabled(true);
                label.setText("Hai Sballato");
            }
        }

        if(e.getSource() == confirm){
            label.setText("Fai la tua giocata");
        }
    }
}