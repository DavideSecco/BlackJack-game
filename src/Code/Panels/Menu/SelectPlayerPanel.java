package Code.Panels.Menu;

import Utils.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static Code.MyDB.statement;
import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.menuPanel;

public class SelectPlayerPanel extends JPanel implements ActionListener{

    private JButton menu;

    private JPanel parteAlta;

    private List<ActionListener> actionListener;

    public SelectPlayerPanel() throws SQLException {
        super();
        setLayout(new BorderLayout());
        menu = new JButton("Menu principale");
        menu.addActionListener(this);
        parteAlta = new JPanel();
        parteAlta.setLayout(new GridLayout(1,2));
        parteAlta.add(new JLabel("Ecco tutti i giocatori (sotto questa linea vorrei ci fosse i titoli delle colonne!)"));
        parteAlta.add(menu);
        add(BorderLayout.PAGE_START, parteAlta);
        add(BorderLayout.CENTER, getTable("SELECT * FROM Players"));
    }

    public JTable getTable(String query) throws SQLException {
        JTable t = new JTable();
        DefaultTableModel dm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {        //serve per rendere inmodificabili le celle
                return false;
            }
        };

        // ResultSet rs = DBManager.getConnection().createStatement().executeQuery(query);
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData();

        // get columns metadata
        int cols = rsMetaData.getColumnCount();
        System.out.println("Ho trovato " + Integer.toString(cols) + " colonne ");
        String[] c = new String[cols];
        for (int i = 0; i < cols; i++) {
            c[i] = rsMetaData.getColumnName(i + 1);
            System.out.println("La " + i + " colonna é " + c[i]);
             dm.addColumn(c[i]);
        }

        System.out.println("Dopo aver letto le colonne ho " + dm.getRowCount() + " righe (anche al prof viene 0 righe)");
        System.out.println("Dopo aver letto le colonne ho " + dm.getColumnCount() + " colonne");

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
        return t;
    }

    public void addActionListener(ActionListener actionListener) {
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menu){
            mainPanel.changePanel(menuPanel);
        }
    }
}
