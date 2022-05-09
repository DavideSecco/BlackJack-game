package Panels.Menu;

import Main.TestApp;
import Panels.MainFrame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.TestApp.*;
import static Panels.MainFrame.*;
import static Panels.MainPanel.*;

public class MenuPanel extends JPanel implements ActionListener {
    public JButton playButton;
    public JButton selectPlayer;
    public JButton viewRules;

    public MenuPanel() {
        super();
        setPreferredSize(menuDimension);
        setLayout(new GridLayout(3,1));

        playButton = new JButton("Play");
        selectPlayer = new JButton("Select Player");
        viewRules = new JButton("Read Rules");

        playButton.addActionListener(this);
        selectPlayer.addActionListener(this);
        viewRules.addActionListener(this);

        this.add(playButton);
        this.add(selectPlayer);
        this.add(viewRules);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Non sono riuscito a far si che si elimini il frame
        if(e.getSource() == playButton){
            System.out.println("Play a Game!");
            mainPanel.changePanel(gamePanel);
        }

        if(e.getSource() == selectPlayer){
            mainPanel.changePanel(selectPlayerPanel);
        }

        if(e.getSource() == viewRules){
            mainPanel.changePanel(rulesPanel);
        }
    }
}
