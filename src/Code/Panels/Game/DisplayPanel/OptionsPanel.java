package Code.Panels.Game.DisplayPanel;

import Code.Panels.Game.GamePanel;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OptionsPanel extends JPanel implements ActionListener{
    public JButton menu;
    public JButton newGame;

    private List<ActionListener> actionListener;

    public OptionsPanel() {
        super();
        setLayout(new GridLayout(1,2));

        actionListener = new ArrayList<ActionListener>();

        menu = new JButton("Menu principale");
        newGame = new JButton("Gioca ancora");

        menu.addActionListener(this);
        newGame.addActionListener(this);

        newGame.setEnabled(false);
        menu.setEnabled(true);

        this.add(newGame);
        this.add(menu);
    }

    public void addActionListener(ActionListener actionListener) {
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame){
            TestApp.player.clear();
            TestApp.dealer.clear();

            GamePanel.displayPanel.inizialize();
            GamePanel.controlPanel.inizialize();

            newGame.setEnabled(false);
        }
        if(e.getSource() == menu){

        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }
}