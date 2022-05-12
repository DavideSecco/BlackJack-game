package Code.Panels.Menu.Login;

import Code.Panels.Game.DisplayPanel.BetPanel;
import Utils.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.gamePanel;
import static Code.TestApp.*;

public class LoginDialog extends JDialog {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
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

        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (Login.authenticate(getUsername(), getPassword())) {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Benvenuto " + getUsername() + "!",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        succeeded = true;
                        dispose();
                        setPlayer();
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
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setPlayer(){
        player.setAccount(Login.account);
    }
}