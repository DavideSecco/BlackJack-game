package Code.Panels.Game.DisplayPanel;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Code.Panels.Game.ControlPanel.ActionPanel.hitButton;
import static Code.Panels.Game.ControlPanel.ActionPanel.standButton;
import static Code.Panels.Game.ControlPanel.FichesPanel.confirm;
import static Code.Panels.Game.DisplayPanel.DisplayPanel.optionsPanel;
import static Code.TestApp.player;

public class LabelPanel extends JPanel implements ActionListener {
    private JLabel name;
    private JLabel message;

    public LabelPanel(){
        setPreferredSize(new Dimension(TestApp.gameDimension.width/4, TestApp.gameDimension.height/10));

        name = new JLabel(player.getName());
        add(name);

        message = new JLabel();
        add(message);

        inizialize();
    }

    public void inizialize(){
        message.setText("Punta");
        message.setHorizontalAlignment(JLabel.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == standButton){
            optionsPanel.newGame.setEnabled(true);
            if(TestApp.whoWon() == 1){
                if(TestApp.checkBlackjack())
                    message.setText("Complimenti, hai fatto BlackJack");
                else
                    message.setText("Hai vinto");
            }

            else if(TestApp.whoWon() == 0)
                message.setText("Pareggio");
            else
                message.setText("Hai perso");
        }

        if(e.getSource() == hitButton){
            if(player.isBust()){
                optionsPanel.newGame.setEnabled(true);
                message.setText("Hai Sballato");
            }
        }

        if(e.getSource() == confirm){
            message.setText("Fai la tua giocata");
        }
    }
}
