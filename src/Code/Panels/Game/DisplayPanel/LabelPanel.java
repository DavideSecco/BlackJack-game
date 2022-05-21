package Code.Panels.Game.DisplayPanel;

import Code.Gameplay;
import Code.Panels.Menu.MenuPanel;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Code.Panels.Game.ControlPanel.ActionPanel.hitButton;
import static Code.Panels.Game.ControlPanel.ActionPanel.standButton;
import static Code.Panels.Game.ControlPanel.FichesPanel.confirm;
import static Code.Panels.Game.DisplayPanel.DisplayPanel.optionsPanel;
import static Code.Panels.MainFrame.gameDimension;
import static Code.Panels.Menu.MenuPanel.loginDlg;
import static Code.TestApp.player;

public class LabelPanel extends JPanel implements ActionListener {
    private JLabel name;
    private JLabel message;

    public LabelPanel(){
        super();
        setPreferredSize(new Dimension(gameDimension.width/4, gameDimension.height/10));

        name = new JLabel(player.getName());
        add(name);

        message = new JLabel();
        add(message);

        initialize();
    }

    public void initialize(){
        name.setText(player.getName() + ": ");
        message.setText("Punta");
        message.setHorizontalAlignment(JLabel.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == standButton){
            optionsPanel.newGame.setEnabled(true);
            if(Gameplay.whoWon() == 1){
                if(player.hasBlackJack())
                    message.setText("Complimenti, hai fatto BlackJack");
                else
                    message.setText("Hai vinto");
            }

            else if(Gameplay.whoWon() == 0)
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

        if(e.getSource() == loginDlg.btnLogin){
            name.setText(player.getName());
            System.out.println("HO sentito che devo cambiare il player");
        }
    }
}
