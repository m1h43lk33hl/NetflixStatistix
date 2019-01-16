package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Class FilmsPanelFilterActionListener handles the filter implementation within the FilmsPanel class
 */
public class FilmsPanelFilterActionListener implements ActionListener {

    private JComboBox<String> ageIndicationFilterBox;
    private JComboBox<String> amountFilterBox;
    private JComboBox<String> timeSpanFilterBox;
    private JTable filmsTable;
    private JLabel selectedAccountName;

    /**
     * Class constructor for FilmsPanelFilterActionListener
     *
     * @param ageIndicationFilterBox
     * @param amountFilterBox
     * @param timeSpanFilterBox
     * @param filmsTable
     * @param selectedAccountName
     */
    public FilmsPanelFilterActionListener(JComboBox<String> ageIndicationFilterBox, JComboBox<String> amountFilterBox, JComboBox<String> timeSpanFilterBox, JTable filmsTable, JLabel selectedAccountName)
    {
        this.ageIndicationFilterBox = ageIndicationFilterBox;
        this.amountFilterBox = amountFilterBox;
        this.timeSpanFilterBox = timeSpanFilterBox;
        this.filmsTable = filmsTable;
        this.selectedAccountName = selectedAccountName;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.filmsTable.setModel(this.buildTableModel());
    }

    /**
     * Builds default table model from query and returns it
     *
     * @return
     */
    private DefaultTableModel buildTableModel(){

        String orderBy = "";
        String ageIndicationSelector = "";
        String ageIndication = this.ageIndicationFilterBox.getSelectedItem().toString();

        if(this.amountFilterBox.getSelectedItem().toString().equals("Kleiner dan"))
        {
            ageIndicationSelector = "AND Film.Leeftijdsindicatie < "+ageIndication;
        }
        else if(this.amountFilterBox.getSelectedItem().toString().equals("Groter dan"))
        {
            ageIndicationSelector = "AND Film.Leeftijdsindicatie > "+ageIndication;
        }
        else if(this.amountFilterBox.getSelectedItem().toString().equals("Gelijk aan"))
        {
            ageIndicationSelector = "AND Film.Leeftijdsindicatie = "+ageIndication;
        }

        if(timeSpanFilterBox.getSelectedItem().toString().equals("Kortst"))
        {
            orderBy = "ORDER BY Film.Tijdsduur DESC";
        }
        else if(timeSpanFilterBox.getSelectedItem().toString().equals("Langst"))
        {
            orderBy = "ORDER BY Film.Tijdsduur ASC";
        }

        String SQL = "SELECT Film.FilmID, Film.Titel, Film.Genre, Film.Tijdsduur, Film.Taal, Film.Leeftijdsindicatie FROM Film \n" +
                "\tINNER JOIN Programmalog ON Programmalog.FilmID = Film.FilmID \n" +
                "\tINNER JOIN Account ON Account.Naam = '"+this.selectedAccountName.getText()+"' \n" +
                "WHERE Programmalog.PercentageBekeken = 100 \n" +
                "\t "+ageIndicationSelector+" "+orderBy+"; ";

        System.out.println(SQL);

        try {
            Database database = Database.getInstance();
            ResultSet resultSet = database.query(SQL);

            ResultSetMetaData metaData = resultSet.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

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
        }
        catch(Exception e)
        {
            return null;
        }

    }

}
