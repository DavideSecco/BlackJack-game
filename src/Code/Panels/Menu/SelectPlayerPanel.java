package Code.Panels.Menu;

import Utils.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static Code.MyDB.statement;

public class SelectPlayerPanel extends JPanel {
    public SelectPlayerPanel() throws SQLException {
        super();
        setLayout(new BorderLayout());
        add(BorderLayout.PAGE_START, new JLabel("Ecco tutti i giocatori (sotto questa linea vorrei ci fosse i titoli delle colonne!)"));
        add(BorderLayout.CENTER, getTable("SELECT * FROM Players"));
    }

    public JTable getTable(String query) throws SQLException {
        JTable t = new JTable();
        DefaultTableModel dm = new DefaultTableModel();

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
}
