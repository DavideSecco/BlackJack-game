package Code.Panels.Menu;

import Code.Panels.MainFrame;
import Code.Panels.Menu.Login.LoginDialog;
import Code.TestApp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.MainFrame.*;
import static Code.Panels.MainPanel.*;
import static Code.TestApp.mainFrame;

public class MenuPanel extends JPanel implements ActionListener {
    public JButton playButton;
    public JButton selectPlayer;
    public JButton viewRules;

    public MenuPanel() {
        super();
        setPreferredSize(TestApp.menuDimension);
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
        if(e.getSource() == playButton) {
            LoginDialog loginDlg = new LoginDialog(mainFrame);
            loginDlg.setVisible(true);
            if (loginDlg.isSucceeded()) {
                mainPanel.changePanel(gamePanel);   //cambia il panel se il login è andato a buon fine
                if(TestApp.player.getAccount() < 10)
                    fichesPanel.enablePanel(false);
            }
        }
        if(e.getSource() == selectPlayer){
            try {
                selectPlayerPanel=new SelectPlayerPanel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            mainPanel.changePanel(selectPlayerPanel);
        }

        if(e.getSource() == viewRules){
            mainPanel.changePanel(rulesPanel);
        }
    }
}
