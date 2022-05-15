package Code.Panels.Menu.Login;

import Code.Panels.Game.DisplayPanel.BetPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import static Code.Panels.Game.DisplayPanel.DisplayPanel.labelPanel;
import static Code.TestApp.*;

public class LoginDialog extends JDialog implements ActionListener, KeyListener {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    public JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    /**
     * ho visto da google come creare correttamente un jdialog per login:
     *
     * https://www.zentut.com/java-swing/simple-login-dialog/
     * @param parent
     */
    public LoginDialog(Frame parent) {
        super(parent, "Login", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
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

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        pfPassword.addKeyListener((KeyListener) this);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnLogin){
            labelPanel.inizialize();
            check();
        }

        if(e.getSource() == btnCancel)
            dispose();
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() { return succeeded; }

   @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 10){
            check();
            labelPanel.inizialize();
        }

    }

    public void check(){
        try {
            if (Login.authenticate(getUsername(), getPassword())) {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Benvenuto " + getUsername() + "!",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose();
                player.setAccount(Login.account);
                System.out.println("Il giocatore attuale è: " + player.getName());
                BetPanel.changeAccount(Login.account);
            } else {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Username o password invalidi",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                // azzera username and password
                tfUsername.setText("");
                pfPassword.setText("");
                succeeded = false;
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