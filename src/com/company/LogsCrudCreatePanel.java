package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ProfilesPanel handles components within the profilespanel
 */
public class LogsCrudCreatePanel extends JPanel {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private JTextField profileNameTextField = new JTextField();
    private JTextField profileAgeTextField = new JTextField();
    private InternalFrame internalFrame;
    private JTable table;

    public LogsCrudCreatePanel(JComboBox<String> selectProfileBox, JLabel selectedAccountName, JTable table) {
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.table = table;
        this.setBackground(Color.gray);

        // Create components is in the public setInternalFrame method to allow the pass of the reference
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("profilesPanel");
    }


    /**
     * Create AccountsPanel components
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
        JPanel fourthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

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
        fourthComponentPanel.setBackground(Color.gray);


        // Radiobuttons
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton seriesRadioButton = new JRadioButton("Serie:");
        seriesRadioButton.setBackground(Color.gray);
        JRadioButton filmsRadioButton = new JRadioButton("Film:");
        filmsRadioButton.setBackground(Color.gray);

        buttonGroup.add(seriesRadioButton);
        buttonGroup.add(filmsRadioButton);


        JComboBox<String> selectEpisodeBox = new JComboBox();
        selectEpisodeBox.setPreferredSize(new Dimension(100, 25));

        JLabel SerieTitelLabel = new JLabel("Serie:");
        JComboBox<String> selectSerieBox = new JComboBox<>(this.returnSeriesTitles().toArray(new String[0]));
        selectSerieBox.setPreferredSize(new Dimension(100, 25));
        selectSerieBox.addItemListener(new LogsCrudCreatePanelSeriesChangeListener(selectEpisodeBox));


        firstComponentPanel.add(seriesRadioButton);
        firstComponentPanel.add(SerieTitelLabel);
        firstComponentPanel.add(selectSerieBox);
        firstComponentPanel.add(selectEpisodeBox);


        JComboBox<String> selectFilmBox = new JComboBox<>(this.returnFilmTitles().toArray(new String[0]));
        selectFilmBox.setPreferredSize(new Dimension(100, 25));

        secondComponentPanel.add(filmsRadioButton);
        secondComponentPanel.add(selectFilmBox);

        JLabel timeSpanLabel = new JLabel("Bekeken tijdsduur (%):");
        JTextField timeSpanTextField = new JTextField();
        timeSpanTextField.setPreferredSize(new Dimension(50, 25));

        fourthComponentPanel.add(timeSpanLabel);
        fourthComponentPanel.add(timeSpanTextField);

        JButton logsCreateButton = new JButton();

        logsCreateButton.setText("Maak aan");
        logsCreateButton.addActionListener(new LogsCrudCreateActionListener(buttonGroup, selectSerieBox, selectEpisodeBox, selectFilmBox, timeSpanTextField, this.selectedAccountName, this.selectProfileBox, this.internalFrame, this.table));

        thirdComponentPanel.add(logsCreateButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(secondComponentPanel);
        innerBoxPanel.add(fourthComponentPanel);
        innerBoxPanel.add(thirdComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

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
     * Returns a list of series titles of accounts by selected profile
     *
     * @return
     */
    private java.util.List<String> returnSeriesTitles() {
        List<String> seriesList = new ArrayList<String>();

        seriesList.add("");

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Titel FROM Serie");

            while (rs.next()) {
                seriesList.add(rs.getString("Titel"));
            }

            return seriesList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Returns a list of account names of accounts by selected profile
     *
     * @return
     */
    private java.util.List<String> returnFilmTitles() {
        List<String> seriesList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Titel FROM Film");

            while (rs.next()) {
                seriesList.add(rs.getString("Titel"));
            }

            return seriesList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void setInternalFrame(InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
        this.createComponents();
    }

}
