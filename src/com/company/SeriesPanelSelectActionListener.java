package com.company;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Class SeriesPanelSelectActionListener implements the selection functionality for class SeriesPanel
 */
public class SeriesPanelSelectActionListener implements ActionListener {

    private JTable seriesTable;
    private JFreeChart barChart;
    private JLabel selectedAccountName;
    private JComboBox<String> accountFilterBox;

    /**
     * Class constructor for SeriesPanelSelectActionListener
     *
     * @param seriesTable
     * @param barChart
     * @param selectedAccountName
     * @param accountFilterBox
     */
    public SeriesPanelSelectActionListener(JTable seriesTable, JFreeChart barChart, JLabel selectedAccountName, JComboBox<String> accountFilterBox)
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

        // Get SeriesID from table
        int seriesID = Integer.parseInt(this.seriesTable.getModel().getValueAt(this.seriesTable.getSelectedRow(), 0).toString());

        String SQL;

        if(this.accountFilterBox.getSelectedItem().toString().equals("Account"))
        {
            SQL = "SELECT Aflevering.AfleveringID, Aflevering.Titel, AVG(Programmalog.PercentageBekeken) as 'PercentageBekeken' FROM Programmalog \n" +
                    "\tINNER JOIN Profiel ON Profiel.ProfielID = Programmalog.ProfielID \n" +
                    "\tINNER JOIN Account ON Account.Naam = Profiel.AccountNaam \n" +
                    "\tINNER JOIN Aflevering ON Aflevering.AfleveringID = Programmalog.AfleveringID \n" +
                    "\tINNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID\n" +
                    "\tINNER JOIN Serie ON Serie.SerieID = Seizoen.SeizoenID\n" +
                    "WHERE Account.Naam = '"+this.selectedAccountName.getText()+"' AND Serie.SerieID = "+seriesID+" GROUP BY Aflevering.AfleveringID, Aflevering.Titel;";
        }
        else
        {
            SQL = "SELECT Aflevering.AfleveringID, Aflevering.Titel, AVG(Programmalog.PercentageBekeken) as 'PercentageBekeken' FROM Programmalog \n" +
                    "\tINNER JOIN Profiel ON Profiel.ProfielID = Programmalog.ProfielID \n" +
                    "\tINNER JOIN Account ON Account.Naam = Profiel.AccountNaam \n" +
                    "\tINNER JOIN Aflevering ON Aflevering.AfleveringID = Programmalog.AfleveringID \n" +
                    "\tINNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID\n" +
                    "\tINNER JOIN Serie ON Serie.SerieID = Seizoen.SeizoenID\n" +
                    "WHERE Serie.SerieID = "+seriesID+" GROUP BY Aflevering.AfleveringID, Aflevering.Titel;";
        }

        System.out.println(SQL);

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