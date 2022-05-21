package Code.Panels.Menu.Dialog;

import Code.MyDB;
import Code.Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.gamePanel;
import static Code.TestApp.player;

public class RegisterDialog extends AbstractDialog implements ActionListener, KeyListener {
    public JButton rgsLogin;

    private JLabel lbAccount;
    private JTextField tfAccount;

    public RegisterDialog(Frame parent) {
        super(parent);

        lbAccount = new JLabel("Account: ");
        add(lbAccount);

        tfAccount = new JTextField("1000", 20);
        add(tfAccount);

        tfAccount.addKeyListener((KeyListener) this);

        rgsLogin = new JButton("Registrati");
        rgsLogin.addActionListener(this);

        JPanel bp = new JPanel();
        bp.add(rgsLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rgsLogin){
            if(registryCheck()) {
                gamePanel.initialize();
                mainPanel.changePanel(gamePanel);
            }
            else {
                tfUsername.setText("");
                pfPassword.setText("");
            }
        }

        if(e.getSource() == btnCancel)
            dispose();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 10){
            if(registryCheck()) {
                gamePanel.initialize();
                mainPanel.changePanel(gamePanel);
            }
            else {
                tfUsername.setText("");
                pfPassword.setText("");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean registryCheck(){
        try {
            if (MyDB.isUserinDB(getUsername())) {
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Username già esistente",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            else if (getUsername()=="" || getPassword()=="") {
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Username o Password non validi",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            else if (getAccount() < 10 || getAccount() > 1000000){
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Importo inserito non valido (minimo 10 e massimo 1000000)",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            else {
                MyDB.addPlayer(new Player(getUsername(), 0 , getAccount(), getPassword(), 0));
                MyDB.changePlayerFromDB(getUsername());

                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Benvenuto " + getUsername() + "!",
                        "Registrazione",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
                System.out.println("Il giocatore attuale è: " + player.getName());
                return true;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public int getAccount() {
        return Integer.parseInt(tfAccount.getText().trim());
    }
}
