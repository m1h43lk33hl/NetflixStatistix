package com.company.presentation;

import com.company.applicationlogic.listeners.FilmsPanelFilterActionListener;
import com.company.applicationlogic.listeners.FilmsPanelSelectActionListener;
import com.company.datastorage.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Class FilmsPanel handles components within the filmspanel.
 */
public class FilmsPanel extends JPanel {

    // Labels for reference
    private JLabel selectedAccountName = new JLabel();
    private JLabel selectedProfileName = new JLabel();

    /**
     * Class constructor for FilmsPanel
     */
    public FilmsPanel()
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
        JPanel sixthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel seventhComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel fillerPanel = new JPanel();

        // Set colors and titles
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Films"));
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);
        forthComponentPanel.setBackground(Color.gray);
        fifthComponentPanel.setBackground(Color.gray);
        sixthComponentPanel.setBackground(Color.gray);
        seventhComponentPanel.setBackground(Color.gray);


        // Set options
        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //create table and fill with data then make selection mode single
        JTable filmsTable = new JTable(this.buildTableModel());
        filmsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Remove FilmID so it's invisible to the user but accessible for us
        TableColumnModel tcm = filmsTable.getColumnModel();
        tcm.removeColumn( tcm.getColumn(0) );

        JScrollPane tablePane = new JScrollPane(filmsTable);

        // Manually set size of tablePane
        tablePane.setPreferredSize(new Dimension(900, 190));
        tablePane.setMaximumSize(new Dimension(900, 190));


        // Add components to first component panel
        firstComponentPanel.add(tablePane);

        // Add components to third component panel
        JLabel filterOptionsLabel = new JLabel("Filter opties (per account):");
        secondComponentPanel.add(filterOptionsLabel);

        // Add components to second component panel
        JLabel filterAccountsLabel = new JLabel("Bekeken door:");

        // add components to forth component panel
        JLabel timeSpanLabel = new JLabel("Tijdsduur:");
        String[] timeSpanFilterBoxOptions = {"Geen selectie", "Kortst", "Langst"};
        JComboBox<String> timeSpanFilterBox = new JComboBox<>(timeSpanFilterBoxOptions);

        JLabel ageIndicationLabel = new JLabel("Leeftijdsindicatie: ");
        String[] ageIndicationFilterBoxOptions = {"0", "6", "9", "12", "16"};
        JComboBox<String> ageIndicationFilterBox = new JComboBox<>(ageIndicationFilterBoxOptions);

        String[] amountFilterBoxOptions = {"Gelijk aan", "Kleiner dan", "Groter dan"};
        JComboBox<String> amountFilterBox = new JComboBox<>(amountFilterBoxOptions);

        JButton filterAccountButton = new JButton("Filter");
        filterAccountButton.addActionListener(new FilmsPanelFilterActionListener(ageIndicationFilterBox, amountFilterBox, timeSpanFilterBox, filmsTable, this.selectedAccountName));

        forthComponentPanel.add(timeSpanLabel);
        forthComponentPanel.add(timeSpanFilterBox);
        forthComponentPanel.add(ageIndicationLabel);
        forthComponentPanel.add(amountFilterBox);
        forthComponentPanel.add(ageIndicationFilterBox);
        forthComponentPanel.add(filterAccountButton);

        //Labels
        this.selectedAccountName.setVisible(false);
        this.selectedProfileName.setVisible(false);

        forthComponentPanel.add(this.selectedAccountName);
        forthComponentPanel.add(this.selectedProfileName);

        //Fifth component panel
        JLabel movieLabel = new JLabel("Film:");
        JLabel movieSelectedLabel = new JLabel("<Geen gegevens>");
        movieSelectedLabel.setForeground(Color.white);

        JLabel watchByLabel = new JLabel("Totaal bekeken door:");
        JLabel watchByAmountLabel = new JLabel("<Geen gegevens>");
        watchByAmountLabel.setForeground(Color.white);

        seventhComponentPanel.add(watchByLabel);
        seventhComponentPanel.add(watchByAmountLabel);

        fifthComponentPanel.add(movieLabel);
        fifthComponentPanel.add(movieSelectedLabel);

        JButton selectMovieButton = new JButton("Select");
        selectMovieButton.addActionListener(new FilmsPanelSelectActionListener(filmsTable, movieSelectedLabel, watchByAmountLabel, this.selectedAccountName));

        sixthComponentPanel.add(selectMovieButton);


        innerBoxPanel.add(firstComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);

        innerBoxPanel.add(sixthComponentPanel);
        innerBoxPanel.add(fifthComponentPanel);
        innerBoxPanel.add(seventhComponentPanel);
        innerBoxPanel.add(fillerPanel);
        innerBoxPanel.add(secondComponentPanel);
        innerBoxPanel.add(thirdComponentPanel);
        innerBoxPanel.add(forthComponentPanel);

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

    public JLabel getSelectedAccountName()
    {
        return this.selectedAccountName;
    }
    public JLabel getSelectedProfileName()
    {
        return this.selectedProfileName;
    }


    /**
     * Builds default table model from query and returns it
     *
     * @return
     */
    private DefaultTableModel buildTableModel(){

        try {
            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT * FROM Film");

            ResultSetMetaData metaData = resultSet.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // Data of the table
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
