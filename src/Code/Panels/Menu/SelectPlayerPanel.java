package Code.Panels.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static Code.MyDB.statement;
import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.menuPanel;
import static Code.TestApp.click;

public class SelectPlayerPanel extends JPanel implements ActionListener{
    private JButton menu;
    private JPanel parteAlta;

    public SelectPlayerPanel() {
        super();
        setLayout(new BorderLayout());

        menu = new JButton("Menu principale");
        menu.addActionListener(this);

        parteAlta = new JPanel();
        parteAlta.setLayout(new GridLayout(1,2));
        parteAlta.add(new JLabel("Ecco tutti i giocatori"));
        parteAlta.add(menu);

        add(BorderLayout.PAGE_START, parteAlta);
        add(new JScrollPane(getTable("SELECT * FROM Players")));
    }

    public JTable getTable(String query)  {
        JTable t = new JTable();
        //serve per rendere inmodificabili le celle
        DefaultTableModel dm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsMetaData = rs.getMetaData();

            // get columns metadata
            int cols = rsMetaData.getColumnCount();
            System.out.println("Ho trovato " + Integer.toString(cols) + " colonne ");
            String[] c = new String[cols];
            for (int i = 0; i < cols; i++) {
                c[i] = rsMetaData.getColumnName(i + 1);
                dm.addColumn(c[i]);
            }

            // Get rows
            Object[] row = new Object[cols];
            while (rs.next()) {
                for (int i = 0; i < cols; i++) {
                    row[i] = rs.getString(i+1);
                }
                dm.addRow(row);
            }
            t.setModel(dm);
            t.setGridColor(Color.BLACK);

            // Rendere la colonna delle password non leggibile
            t.getColumnModel().getColumn(4).setCellRenderer(new PasswordCellRenderer());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        click.play();

        if(e.getSource() == menu){
            mainPanel.changePanel(menuPanel);
        }
    }
}
