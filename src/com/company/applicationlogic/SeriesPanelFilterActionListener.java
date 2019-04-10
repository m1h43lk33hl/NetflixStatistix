package com.company.applicationlogic;

import com.company.datastorage.Database;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Class SeriesPanelFilterActionListener handles the filter implementation of class SeriesPanel
 */
public class SeriesPanelFilterActionListener implements ActionListener {

    private JTable seriesTable;
    private JFreeChart barChart;
    private JLabel selectedAccountName;
    JComboBox<String> accountFilterBox;

    /**
     * Class constructor for SeriesPanelFilterActionListener
     *
     * @param seriesTable
     * @param barChart
     * @param selectedAccountName
     * @param accountFilterBox
     */
    public SeriesPanelFilterActionListener(JTable seriesTable, JFreeChart barChart, JLabel selectedAccountName, JComboBox<String> accountFilterBox)
    {
        this.seriesTable = seriesTable;
        this.barChart = barChart;
        this.selectedAccountName = selectedAccountName;
        this.accountFilterBox = accountFilterBox;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.selectedAccountName.getText().equals(""))
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_NOT_SELECTED);
            return;
        }


        if(this.seriesTable.getSelectionModel().isSelectionEmpty())
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ROW_NOT_SELECTED);
            return;
        }

        // Get SeriesID from table
        int seriesID = Integer.parseInt(this.seriesTable.getModel().getValueAt(this.seriesTable.getSelectedRow(), 0).toString());

        String SQL;

        if(this.accountFilterBox.getSelectedItem().toString().equals("Account"))
        {
            SQL = "SELECT Aflevering.AfleveringID, Aflevering.Titel, AVG(Afleveringlog.PercentageBekeken) as 'PercentageBekeken' FROM Afleveringlog \n" +
                    "\tINNER JOIN Profiel ON Profiel.ProfielID = Afleveringlog.ProfielID \n" +
                    "\tINNER JOIN Account ON Account.Naam = Profiel.AccountNaam \n" +
                    "\tINNER JOIN Aflevering ON Aflevering.AfleveringID = Afleveringlog.AfleveringID \n" +
                    "\tINNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID\n" +
                    "\tINNER JOIN Serie ON Serie.SerieID = Seizoen.SeizoenID\n" +
                    "WHERE Account.Naam = '"+this.selectedAccountName.getText()+"' AND Serie.SerieID = "+seriesID+" GROUP BY Aflevering.AfleveringID, Aflevering.Titel;";
        }
        else
        {
            SQL = "SELECT Aflevering.AfleveringID, Aflevering.Titel, AVG(Afleveringlog.PercentageBekeken) as 'PercentageBekeken' FROM Afleveringlog \n" +
                    "\tINNER JOIN Profiel ON Profiel.ProfielID = Afleveringlog.ProfielID \n" +
                    "\tINNER JOIN Account ON Account.Naam = Profiel.AccountNaam \n" +
                    "\tINNER JOIN Aflevering ON Aflevering.AfleveringID = Afleveringlog.AfleveringID \n" +
                    "\tINNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID\n" +
                    "\tINNER JOIN Serie ON Serie.SerieID = Seizoen.SeizoenID\n" +
                    "WHERE Serie.SerieID = "+seriesID+" GROUP BY Aflevering.AfleveringID, Aflevering.Titel;";
        }

        try{
            Database database = Database.getInstance();
            ResultSet resultSet = database.query(SQL);

            System.out.println(SQL);

            // Update chart
            ((CategoryPlot) this.barChart.getPlot()).setDataset(this.createDataset(resultSet));


        }catch (Exception e)
        {

        }
    }

    /**
     * Returns a dataset for the JTable
     *
     * @param resultSet
     * @return
     */
    private CategoryDataset createDataset(ResultSet resultSet) {

        // Gebruiker of profiel
        final String gebruiker = "Afleveringen";

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        try {
            while (resultSet.next()) {
                dataset.addValue(resultSet.getInt("PercentageBekeken"), gebruiker, resultSet.getString("Titel"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return dataset;
    }
}