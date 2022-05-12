package Code.Panels.Menu;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


/**
 * Questa classe serve unicamente a mascherare le password con degli asterischi
 * la lunghezza degli asterischi è data dalla lunghezza di "filler123"
 *
 * visto da: https://stackoverflow.com/questions/10679786/how-to-create-a-column-cell-for-entering-password-in-jtable
 */
class PasswordCellRenderer extends JPasswordField implements TableCellRenderer {
    public PasswordCellRenderer() {
        super();
        this.setText("filler123");
    }

    public Component getTableCellRendererComponent(
            JTable arg0,
            Object arg1,
            boolean arg2,
            boolean arg3,
            int arg4,
            int arg5) {
        return this;
    }
}