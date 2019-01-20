package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Class ProfilesPanel handles components within the profilespanel
 */
public class LogsCrudPanel extends JPanel {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private int crudMode;
    private JTextField profileNameTextField = new JTextField();
    private JTextField profileAgeTextField = new JTextField();
    private InternalFrame internalFrame;

    public LogsCrudPanel(JComboBox<String> selectProfileBox, JLabel selectedAccountName) {
        this.setBackground(Color.gray);
        // Create components is in the public setInternalFrame method to allow the pass of the reference
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("profilesPanel");
        this.selectedAccountName = selectedAccountName;
        this.selectProfileBox = selectProfileBox;

    }


    /**
     * Create LogsPanel components
     */
    private void createComponents() {
        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        JPanel innerBoxPanel2 = new JPanel();
        JPanel recommendedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel firstComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel thirdComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerBoxPanel2.setLayout(new BoxLayout(innerBoxPanel2, BoxLayout.Y_AXIS));

        JPanel fillerPanel = new JPanel();

        // Set borders
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Profiel"));

        // Set colors
        innerBoxPanel2.setBackground(Color.gray);
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        recommendedPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);

        // Create JTable with dropdown menu
        // If you select a serie->new selection for episode cell
        // If you select a serie AND a movie -> ...

        JTable table = new JTable(this.buildTableModel()){


            @Contract(pure = true)
            public boolean isCellEditable(int rowindex, int colindex)
            {
                try{
                    if(this.getValueAt(rowindex, colindex).toString().equals(""))
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                catch (Exception e)
                {
                    return false;
                }
            }


            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel( column );

                if (modelColumn == 2)
                {
                    JComboBox<String> comboBox1 = new JComboBox<String>();
                    System.out.println( this.getValueAt(row, column-1).toString());
                    return new DefaultCellEditor( returnEpisodeBox( this.getValueAt(row, column-1).toString() ) );
                }
                else
                    return super.getCellEditor(row, column);
            }
        };

        this.setSerieTitle(table);


        table.setPreferredSize(new Dimension(700, 130));
        table.setMinimumSize(new Dimension(700, 130));


        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setPreferredSize(new Dimension(600, 130));
        tablePane.setMaximumSize(new Dimension(600, 130));

        JButton newLogButton = new JButton("Nieuw");
        newLogButton.addActionListener(new LogsCrudPanelCreateActionListener(this.selectProfileBox, this.selectedAccountName, table));

        firstComponentPanel.add(tablePane);
        firstComponentPanel.add(newLogButton);


        JButton saveLogButton = new JButton("Opslaan");
        saveLogButton.addActionListener(new LogsCrudPanelSaveActionListener(table, this.selectProfileBox, this.selectedAccountName));

        JButton deleteLogButton = new JButton("Verwijder");
        deleteLogButton.addActionListener(new LogsCrudPanelDeleteActionListener(table));

        secondComponentPanel.add(saveLogButton);
        secondComponentPanel.add(deleteLogButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(secondComponentPanel);

        innerBoxPanel2.add(recommendedPanel);

        innerFlowPanel.add(innerBoxPanel);

        // Keep FlowPanels aligned
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(innerFlowPanel);
    }

    /**
     * Returns the selectProfileBox for reference sake
     *
     * @return
     */
    public JComboBox<String> getSelectProfileBox() {
        return this.selectProfileBox;
    }

    /**
     * Fills fields
     */
    private void fillFields() {
        try {
            Database database = Database.getInstance();
            String SQL = "SELECT Leeftijd FROM Profiel WHERE AccountNaam='" + this.selectedAccountName.getText() + "' AND Naam='" + this.selectProfileBox.getSelectedItem().toString() + "';";
            System.out.println(SQL);
            ResultSet resultSet = database.query(SQL);
            int age = 0;

            while (resultSet.next()) {
                age = resultSet.getInt("Leeftijd");
            }

            this.profileNameTextField.setText(this.selectProfileBox.getSelectedItem().toString());
            this.profileAgeTextField.setText(Integer.toString(age));
        } catch (Exception e) {

        }
    }

    /**
     * Sets internal frame
     *
     * @param internalFrame
     */
    public void setInternalFrame(InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
        this.createComponents();
    }

    /**
     * Builds default table model from query and returns it
     *
     * @return
     */
    private DefaultTableModel buildTableModel() {

        try {
            Database database = Database.getInstance();
            System.out.println("SELECT DISTINCT Programmalog.ProgrammalogID, Serie.Titel, Aflevering.Titel, Film.Titel,Programmalog.PercentageBekeken FROM Programmalog INNER JOIN Profiel ON Profiel.AccountNaam = 'Bas Brouwers' LEFT JOIN Film ON Film.FilmID = Programmalog.FilmID INNER JOIN Aflevering ON Aflevering.AfleveringID = Programmalog.AfleveringID INNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID WHERE Profiel.AccountNaam = 'Bas Brouwers';");

            String SQL = "SELECT DISTINCT Afleveringlog.AfleveringlogID, Serie.Titel, Aflevering.Titel, NULL AS ' ', Afleveringlog.PercentageBekeken \n" +
                    "FROM Afleveringlog \n" +
                    "INNER JOIN Profiel ON Profiel.ProfielID = Afleveringlog.ProfielID\n" +
                    "INNER JOIN Aflevering ON Aflevering.AfleveringID = Afleveringlog.AfleveringID \n" +
                    "INNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID \n" +
                    "INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID \n" +
                    "WHERE Profiel.AccountNaam = '"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"'"+
                    "\n" +
                    "UNION\n" +
                    "\n" +
                    "SELECT DISTINCT Filmlog.FilmlogID, NULL AS ' ', NULL AS ' ', Film.Titel, Filmlog.PercentageBekeken\n" +
                    "FROM Filmlog\n" +
                    "INNER JOIN Profiel ON Profiel.ProfielID = Filmlog.ProfielID\n" +
                    "INNER JOIN Film ON Film.FilmID = Filmlog.FilmID\n" +
                    "WHERE Profiel.AccountNaam = '"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"'";


            ResultSet resultSet = database.query(SQL);

            ResultSetMetaData metaData = resultSet.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();

            columnNames.add("AfleveringID");
            columnNames.add("Serie");
            columnNames.add("Aflevering");
            columnNames.add("Film");
            columnNames.add("Bekeken (%)");

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();

            while (resultSet.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultSet.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * Sets series title
     */
    private void setSerieTitle(JTable table)
    {

        TableColumnModel tableColumnModel = table.getColumnModel();

        TableColumn seriesColumn = tableColumnModel.getColumn(1);
        TableColumn filmColumn = tableColumnModel.getColumn(3);

        JComboBox<String> comboBox = new JComboBox();
        JComboBox<String> comboBox2 = new JComboBox();

        try
        {
            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT Titel from Serie");

            while(resultSet.next())
            {
                comboBox.addItem(resultSet.getString("Titel"));
            }

            seriesColumn.setCellEditor(new DefaultCellEditor(comboBox));


            ResultSet resultSet2 = database.query("SELECT Titel from Film");

            while(resultSet2.next())
            {
                comboBox2.addItem(resultSet2.getString("Titel"));
            }

            filmColumn.setCellEditor(new DefaultCellEditor(comboBox2));


        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Returns and episode combobox for table
     *
     * @param serie
     * @return
     */
    private JComboBox<String> returnEpisodeBox(String serie)
    {

        JComboBox<String> episodeBox = new JComboBox<>();

        try{
            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT Aflevering.Titel FROM Aflevering INNER JOIN Seizoen ON Aflevering.SezoenID = Seizoen.SeizoenID INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID WHERE Serie.Titel = '"+serie+"';");

            while(resultSet.next())
            {
                episodeBox.addItem(resultSet.getString("Titel"));
            }
        }
        catch (Exception e)
        {

        }

        return episodeBox;
    }
}
