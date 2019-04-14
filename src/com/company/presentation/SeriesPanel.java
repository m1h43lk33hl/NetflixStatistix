package com.company.presentation;

import com.company.applicationlogic.listeners.SeriesPanelFilterActionListener;
import com.company.applicationlogic.listeners.SeriesPanelSelectActionListener;
import com.company.datastorage.Database;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Class SeriesPanel handles components within the seriespanel
 */
public class SeriesPanel extends JPanel {

    // Labels for reference
    private JLabel selectedAccountName = new JLabel();
    private JLabel selectedProfileName = new JLabel();

    /**
     * Class constructor for SeriesPanel
     */
    public SeriesPanel()
    {
        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Create FilmsPanels components
     */
    private void createComponents()
    {
        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        JPanel firstComponentPanel = new JPanel(new GridLayout(0, 1));
        JPanel thirdComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel forthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fifthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel fillerPanel = new JPanel();

        // Set colors and titles
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Series"));
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);
        forthComponentPanel.setBackground(Color.gray);

        // Set options
        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Create table
        JTable seriesTable = new JTable(this.buildTableModel());
        seriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Remove serieID so it's invisible to the user but accessible for us
        TableColumnModel tcm = seriesTable.getColumnModel();
        tcm.removeColumn( tcm.getColumn(0) );

        JScrollPane tablePane = new JScrollPane(seriesTable);
        tablePane.setPreferredSize(new Dimension(500, 130));
        tablePane.setMaximumSize(new Dimension(500, 13-0));


        // Add components to first component panel
        firstComponentPanel.add(tablePane);

        // Add components to third component panel
        JLabel filterOptionsLabel = new JLabel("Filter opties:");
        secondComponentPanel.add(filterOptionsLabel);

        // JCHART
        JFreeChart barChart = ChartFactory.createBarChart(
                "Gemiddeld % bekeken van tijdsduur per aflevering",
                "Category",
                "Percentage",
                null,
                PlotOrientation.VERTICAL,
                true, true, false);

        barChart.getCategoryPlot().getRangeAxis().setLowerBound(0.0);
        barChart.getCategoryPlot().getRangeAxis().setUpperBound(100.0);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new Dimension(900, 190));
        chartPanel.setMinimumSize(new Dimension(900, 190));

        String[] accountFilterBoxOptions = {"Account", "Iedereen"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);


        // Add components to second component panel
        JLabel filterAccountsLabel = new JLabel("Bekeken door:");
        JButton filterAccountButton = new JButton("Filter");
        filterAccountButton.addActionListener(new SeriesPanelFilterActionListener(seriesTable, barChart, this.selectedAccountName, accountFilterBox));

        // Set Labels invisible
        this.selectedAccountName.setVisible(false);
        this.selectedProfileName.setVisible(false);

        thirdComponentPanel.add(accountFilterBox);
        thirdComponentPanel.add(filterAccountButton);
        thirdComponentPanel.add(this.selectedAccountName);
        thirdComponentPanel.add(this.selectedProfileName);

        // add components to forth component panel
        JLabel timeSpanLabel = new JLabel("Tijdsduur:");
        String[] timeSpanFilterBoxOptions = {"Geen selectie", "Kortst", "Langst"};
        JComboBox<String> timeSpanFilterBox = new JComboBox<>(timeSpanFilterBoxOptions);

        JLabel ageIndicationLabel = new JLabel("Leeftijdsindicatie: ");
        String[] ageIndicationFilterBoxOptions = {"Allemaal", "6", "9", "12", "16"};
        JComboBox<String> ageIndicationFilterBox = new JComboBox<>(ageIndicationFilterBoxOptions);

        JButton selectSerieButton = new JButton("Selecteer");
        selectSerieButton.addActionListener(new SeriesPanelSelectActionListener(seriesTable, barChart, this.selectedAccountName, accountFilterBox));

        forthComponentPanel.add(selectSerieButton);
        fifthComponentPanel.add(chartPanel);

        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(forthComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,15))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);

        innerBoxPanel.add(fillerPanel);
        innerBoxPanel.add(fifthComponentPanel);
        innerBoxPanel.add(secondComponentPanel);
        innerBoxPanel.add(thirdComponentPanel);

        innerFlowPanel.add(innerBoxPanel);

        this.add(innerFlowPanel);

        // Create space for panel alignment
        for(int x = 0; x < 3; x++)
        {
            fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    /**
     * Builds default table model from query and returns it
     *
     * @return
     */
    private DefaultTableModel buildTableModel(){

        try {
            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT * FROM Serie");

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

    /**
     * Returns the selected accountName label
     *
     * @return
     */
    public JLabel getSelectedAccountName()
    {
        return this.selectedAccountName;
    }


    /**
     * Returns the selected profilesName label
     *
     * @return
     */
    public JLabel getSelectedProfileName()
    {
        return this.selectedProfileName;
    }
}