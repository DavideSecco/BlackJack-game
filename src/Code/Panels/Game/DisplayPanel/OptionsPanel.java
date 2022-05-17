package Code.Panels.Game.DisplayPanel;

import Code.Panels.Game.GamePanel;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.Game.GamePanel.controlPanel;
import static Code.Panels.Game.GamePanel.displayPanel;
import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.gamePanel;
import static Code.Panels.MainPanel.menuPanel;
import static Code.TestApp.*;

public class OptionsPanel extends JPanel implements ActionListener{
    public static JButton menu;
    public static JButton newGame;

    private List<ActionListener> actionListener;

    public OptionsPanel() {
        super();
        setLayout(new GridLayout(1,2));

        actionListener = new ArrayList<ActionListener>();

        menu = new JButton("Menu principale (Logout)");
        newGame = new JButton("Gioca ancora");

        menu.addActionListener(this);
        newGame.addActionListener(this);

        initialize();

        add(newGame);
        add(menu);
    }

    public void initialize(){
        newGame.setEnabled(false);
        menu.setEnabled(true);
    }

    public void addActionListener(ActionListener actionListener) {
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame || e.getSource() == menu){
            player.clear();
            dealer.clear();
            cardsDeck.inizialize();

            displayPanel.initialize();
            controlPanel.initialize();

            if(e.getSource() == menu)
                mainPanel.changePanel(menuPanel);
        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }
}
