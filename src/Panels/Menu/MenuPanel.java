package Panels.Menu;

import Panels.Game.GameFrame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Main.TestApp.menuDimension;
import static Main.TestApp.menuFrame;

public class MenuPanel extends JPanel implements ActionListener {
    public JButton playButton;
    public JButton selectPlayer;
    public JButton viewRules;

    public GameFrame gameFrame;

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
            menuFrame.delete();
            try {
                gameFrame = new GameFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource() == selectPlayer){

        }

        if(e.getSource() == viewRules){

        }
    }
}
