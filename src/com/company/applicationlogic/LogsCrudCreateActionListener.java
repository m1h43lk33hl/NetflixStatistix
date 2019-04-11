package com.company.applicationlogic;

import com.company.datastorage.Database;
import com.company.presentation.InternalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Class LogsCrudCreateActionListener handles LogsCrudCreatePanel
 */
public class LogsCrudCreateActionListener implements ActionListener {

    private ButtonGroup buttonGroup;
    private JComboBox<String> selectSerieBox;
    private JComboBox<String> selectEpisodeBox;
    private JComboBox<String> selectFilmBox;
    private JTextField timeSpanTextField;
    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private InternalFrame internalFrame;
    private JTable table;


    /**
     * Class constructor
     *
     * @param buttonGroup
     * @param selectSerieBox
     * @param selectEpisodeBox
     * @param selectFilmBox
     * @param timeSpanTextField
     * @param selectedAccountName
     * @param selectProfileBox
     * @param internalFrame
     * @param table
     */
    public LogsCrudCreateActionListener(ButtonGroup buttonGroup, JComboBox<String> selectSerieBox, JComboBox<String> selectEpisodeBox, JComboBox<String> selectFilmBox, JTextField timeSpanTextField, JLabel selectedAccountName, JComboBox<String> selectProfileBox, InternalFrame internalFrame, JTable table)
    {
        this.buttonGroup = buttonGroup;
        this.selectSerieBox = selectSerieBox;
        this.selectEpisodeBox = selectEpisodeBox;
        this.selectFilmBox = selectFilmBox;
        this.timeSpanTextField = timeSpanTextField;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.internalFrame = internalFrame;
        this.table = table;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.insertLog();
    }

    /**
     * Inserts log
     */
    private void insertLog()
    {
        if(this.getSelectedRadioButton(this.buttonGroup) == 0)
        {
            // Validate log data
            try
            {
                Database database = Database.getInstance();

                String SQLcheck = "SELECT * FROM Afleveringlog WHERE Afleveringlog.ProfielID = (SELECT Profiel.ProfielID FROM Profiel WHERE Profiel.AccountNaam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"')  AND Afleveringlog.AfleveringID = (SELECT TOP 1 Aflevering.AfleveringID FROM Aflevering INNER JOIN Seizoen ON Aflevering.SezoenID = Seizoen.SeizoenID INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID WHERE Serie.Titel = '"+this.selectSerieBox.getSelectedItem().toString()+"' AND Aflevering.Titel = '"+this.selectEpisodeBox.getSelectedItem().toString()+"');";
                if (database.query(SQLcheck).next()) {
                    ErrorDialog.showErrorDialog(ErrorMessages.LOG_DATA_NOT_VALID);
                    return;
                }

                // Check if percentage is between 0 and 100
                if(!(Integer.parseInt(this.timeSpanTextField.getText()) >= 0 && Integer.parseInt(this.timeSpanTextField.getText()) <= 100)  ){

                    ErrorDialog.showErrorDialog(ErrorMessages.LOG_DATA_PERCENTAGE_NOT_VALID);
                    return;
                }
            }
            catch (Exception e)
            {
            }

            String SQL = "INSERT INTO Afleveringlog \n" +
                    "VALUES( \n" +
                    "\t(SELECT TOP 1 AfleveringID \n" +
                    "\tFROM Aflevering \n" +
                    "\tINNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID \n" +
                    "\tINNER JOIN Serie ON Serie.SerieID = Seizoen.SeizoenID \n" +
                    "\tWHERE Serie.Titel='"+this.selectSerieBox.getSelectedItem().toString()+"' AND Aflevering.Titel='"+this.selectEpisodeBox.getSelectedItem().toString()+"'), \n" +
                    "\t\t(SELECT Profiel.ProfielID FROM Profiel INNER JOIN Account ON Account.Naam = '"+this.selectedAccountName.getText()+"' WHERE Profiel.Naam = '"+this.selectProfileBox.getSelectedItem().toString()+"' AND Profiel.AccountNaam = '"+this.selectedAccountName.getText()+"'), "+this.timeSpanTextField.getText()+")\n";


            try
            {
                Database database = Database.getInstance();
                database.queryDDL(SQL);
            }
            catch (Exception e)
            {
                ErrorDialog.showErrorDialog(ErrorMessages.LOG_DATA_NOT_VALID);
            }

        }
        else
        {
            // check if log already exists
            try
            {
                Database database = Database.getInstance();

                String SQLcheck = "SELECT * FROM Filmlog WHERE Filmlog.ProfielID = (SELECT Profiel.ProfielID FROM Profiel WHERE Profiel.AccountNaam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"')  AND Filmlog.FilmID = (SELECT TOP 1 Film.FilmID FROM Film WHERE Film.Titel= '"+this.selectFilmBox.getSelectedItem().toString()+"');";
                if (database.query(SQLcheck).next()) {
                    ErrorDialog.showErrorDialog(ErrorMessages.LOG_DATA_NOT_VALID);
                    return;
                }
            }
            catch (Exception e)
            {
            }

            String SQL = "INSERT INTO Filmlog \n" +
                    "VALUES( (SELECT TOP 1 Film.FilmID \n" +
                    "\tFROM Film \n" +
                    "\tWHERE Film.Titel = '"+this.selectFilmBox.getSelectedItem().toString()+"'), \n" +
                    "\t(SELECT Profiel.ProfielID \n" +
                    "\tFROM Profiel INNER JOIN Account ON Account.Naam = '"+this.selectedAccountName.getText()+"' \n" +
                    "\tWHERE Profiel.Naam = '"+this.selectProfileBox.getSelectedItem().toString()+"' AND Profiel.AccountNaam = '"+this.selectedAccountName.getText()+"'), "+this.timeSpanTextField.getText()+");";

            try
            {
                Database database = Database.getInstance();
                database.queryDDL(SQL);
            }
            catch (Exception e)
            {
                ErrorDialog.showErrorDialog(ErrorMessages.LOG_DATA_NOT_VALID);
            }
        }

        this.table.setModel(this.buildTableModel());
        this.internalFrame.close();
    }

    /**
     * Returns selected radiobutton
     *
     * @param buttonGroup
     * @return
     */
    public int getSelectedRadioButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(button.getText().equals("Serie:"))
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
        }

        return 0;
    }


    /**
     * Builds default table model from query and returns it
     *
     * @return
     */
    private DefaultTableModel buildTableModel() {

        try {
            Database database = Database.getInstance();

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
        } catch (Exception e) {
            return null;
        }

    }
}
