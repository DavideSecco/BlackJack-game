package Code.Panels.Menu;

import Code.MyDB;
import Code.Participant.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import static Code.Panels.MainPanel.gamePanel;
import static Code.TestApp.player;

public class RegisterDialog extends JDialog implements ActionListener, KeyListener {

    private JTextField tfUsername;
    private JPasswordField pfPassword;

    private JTextField tfAccount;
    private JLabel lbUsername;
    private JLabel lbPassword;

    private JLabel lbAccount;
    public JButton rgsLogin;
    private JButton btnCancel;

    private boolean succeeded;
    public RegisterDialog(Frame parent) {
        super(parent, "Registrazione", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField("1", 20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
        tfUsername.addKeyListener((KeyListener) this);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField("1", 20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        pfPassword.addKeyListener((KeyListener) this);

        lbAccount = new JLabel("Account: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbAccount, cs);

        tfAccount = new JTextField("1000", 20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfAccount, cs);
        tfAccount.addKeyListener((KeyListener) this);

        rgsLogin = new JButton("Registrati");
        rgsLogin.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

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
            }else {
                Player p = new Player(getUsername(), 0 ,Integer.parseInt(getAccount()), getPassword(), 0);
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

    public String getAccount() {
        return tfAccount.getText().trim();
    }

    public boolean isSucceeded() { return succeeded; }


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
