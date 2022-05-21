package Code.Panels.Menu.Dialog;

import Code.MyDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.gamePanel;
import static Code.TestApp.*;

/**
 * ho visto da google come creare correttamente un jdialog per login:
 * https://www.zentut.com/java-swing/simple-login-dialog/
 */

public class LoginDialog extends AbstractDialog implements ActionListener, KeyListener {
    private JButton register;

    public static RegisterDialog registerDlg;

    public LoginDialog(Frame parent) {
        super(parent);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        register = new JButton("Registrati");
        register.addActionListener(this);

        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        bp.add(new JLabel("oppure"));
        bp.add(register);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnLogin){
            if(loginCheck()) {
                gamePanel.initialize();
                mainPanel.changePanel(gamePanel);
            }
        }

        if(e.getSource() == btnCancel)
            dispose();

        if(e.getSource() == register){
            registerDlg = new RegisterDialog(mainFrame);
            registerDlg.setVisible(true);
            dispose();
        }
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

   @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 10){
            if(loginCheck()) {
                gamePanel.initialize();
                mainPanel.changePanel(gamePanel);
            }
        }
    }

    public boolean loginCheck(){
        try {
            if (MyDB.authenticate(getUsername(), getPassword())) {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Bentornato " + getUsername() + "!",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                System.out.println("Il giocatore attuale è: " + player.getName());
                return true;
            } else {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Username o password errati",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);

                // azzera username and password
                tfUsername.setText("");
                pfPassword.setText("");
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent ke) {}
}