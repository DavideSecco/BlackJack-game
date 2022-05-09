package Code.Panels.Game.DisplayPanel;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Code.Panels.Game.ControlPanel.ActionPanel.hitButton;
import static Code.Panels.Game.ControlPanel.ActionPanel.standButton;
import static Code.Panels.Game.ControlPanel.FichesPanel.confirm;

public class DisplayPanel extends JPanel implements ActionListener {
    private JLabel label;

    public static JPanel labelPanel;
    public static BetPanel betPanel;
    public static OptionsPanel optionsPanel;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(TestApp.gameDimension.width, TestApp.gameDimension.height/10));
        setLayout(new BorderLayout());

        labelPanel = new JPanel();
        labelPanel.setPreferredSize(new Dimension(TestApp.gameDimension.width/4, TestApp.gameDimension.height/10));
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
            if(TestApp.whoWon() == 1){
                if(TestApp.checkBlackjack())
                    label.setText("Complimenti, hai fatto BlackJack");
                else
                    label.setText("Hai vinto");
            }

            else if(TestApp.whoWon() == 0)
                label.setText("Pareggio");
            else
                label.setText("Hai perso");
        }

        if(e.getSource() == hitButton){
            if(TestApp.player.isBust()){
                optionsPanel.newGame.setEnabled(true);
                label.setText("Hai Sballato");
            }
        }

        if(e.getSource() == confirm){
            label.setText("Fai la tua giocata");
        }
    }
}