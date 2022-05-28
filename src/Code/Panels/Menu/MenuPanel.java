package Code.Panels.Menu;

import Code.Panels.Menu.Dialog.LoginDialog;
import Code.TestApp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.MainFrame.*;
import static Code.Panels.MainPanel.*;
import static Code.TestApp.click;
import static Code.TestApp.mainFrame;

public class MenuPanel extends JPanel implements ActionListener {
    public JButton playButton;
    public JButton selectPlayer;
    public JButton viewRules;

    public static LoginDialog loginDlg;

    public MenuPanel() {
        super();
        setPreferredSize(menuDimension);
        setLayout(new GridLayout(3,1));

        playButton = new JButton("Play");
        selectPlayer = new JButton("View Players");
        viewRules = new JButton("Read Rules");

        playButton.addActionListener(this);
        selectPlayer.addActionListener(this);
        viewRules.addActionListener(this);

        add(playButton);
        add(selectPlayer);
        add(viewRules);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        click.play();

        if(e.getSource() == playButton) {
            loginDlg = new LoginDialog(mainFrame);
            loginDlg.setVisible(true);
        }

        if(e.getSource() == selectPlayer){
            selectPlayerPanel = new SelectPlayerPanel();
            mainPanel.changePanel(selectPlayerPanel);
        }

        if(e.getSource() == viewRules){
            mainPanel.changePanel(rulesPanel);
        }
    }
}
