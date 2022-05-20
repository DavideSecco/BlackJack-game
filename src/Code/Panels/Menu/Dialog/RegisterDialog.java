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

import static Code.Panels.MainPanel.gamePanel;
import static Code.TestApp.player;
import static Code.Panels.Menu.Dialog.LoginDialog.succeeded;

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

    public void registry(){
        try {

            if (MyDB.isUserinDB(getUsername())) {
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Username già esistente",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                // azzera username and password
                succeeded = false;
                tfUsername.setText("");
                pfPassword.setText("");

            }else if(getUsername()=="" || getPassword()=="") {
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Username o Password non validi",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                // azzera username and password
                succeeded = false;
                tfUsername.setText("");
                pfPassword.setText("");

            }else if(getAccount() < 10 || getAccount() > 1000000){
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Importo inserito non valido (minimo 10 e massimo 1000000)",
                        "Registrazione",
                        JOptionPane.ERROR_MESSAGE);
                // azzera username and password
                succeeded = false;
                tfUsername.setText("");
                pfPassword.setText("");

            }else {
                Player p = new Player(getUsername(), 0 , getAccount(), getPassword(), 0);
                MyDB.addPlayer(p);
                MyDB.changePlayerFromDB(getUsername());
                JOptionPane.showMessageDialog(RegisterDialog.this,
                        "Benvenuto " + getUsername() + "!",
                        "Registrazione",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose();
                System.out.println("Il giocatore attuale è: " + player.getName());
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rgsLogin){
            registry();
            gamePanel.initialize();
        }
        if(e.getSource() == btnCancel)
            dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 10){
            registry();
            gamePanel.initialize();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
