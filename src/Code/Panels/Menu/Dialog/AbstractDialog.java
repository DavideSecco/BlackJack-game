package Code.Panels.Menu.Dialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public abstract class AbstractDialog extends JDialog {
    protected JLabel lbUsername;
    protected JLabel lbPassword;

    protected JTextField tfUsername;
    protected JPasswordField pfPassword;

    public JButton btnLogin;
    protected JButton btnCancel;

    public JPanel panel;

    public AbstractDialog(Frame parent){
        super(parent);
        panel = new JPanel(new GridLayout(2,2));

        lbUsername = new JLabel("Username: ");
        panel.add(lbUsername);

        tfUsername = new JTextField("Davide", 20);
        panel.add(tfUsername);
        tfUsername.addKeyListener((KeyListener) this);

        lbPassword = new JLabel("Password: ");
        panel.add(lbPassword);

        pfPassword = new JPasswordField("123", 20);
        panel.add(pfPassword);
        pfPassword.addKeyListener((KeyListener) this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener((ActionListener) this);

        panel.setBorder(new LineBorder(Color.GRAY));
    }
}
