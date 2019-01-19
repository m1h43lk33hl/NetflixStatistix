package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Class ProfilesPanel handles components within the profilespanel
 */
public class ProfilesPanel extends JPanel {

    String[] emptyProfileName = {"<Geen gegevens>"};
    private JComboBox<String> selectProfileBox = new JComboBox<>(emptyProfileName);
    private JLabel selectedProfileName;
    private JLabel selectedAccountName;


    /**
     * Class constructor for ProfilesPanel
     *
     * @param selectedProfileName
     * @param selectedAccountName
     */
    public ProfilesPanel(JLabel selectedProfileName, JLabel selectedAccountName)
    {
        this.selectedProfileName = selectedProfileName;
        this.selectedAccountName = selectedAccountName;
        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("profilesPanel");
    }


    /**
     * Create AccountsPanel components
     */
    private void createComponents()
    {
        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerFlowPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        JPanel innerBoxPanel2 = new JPanel();
        JPanel recommendedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel recommendedPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel firstComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerBoxPanel2.setLayout(new BoxLayout(innerBoxPanel2, BoxLayout.Y_AXIS));

        JPanel fillerPanel = new JPanel();

        // Set borders
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Profiel"));
        innerFlowPanel2.setBorder(BorderFactory.createTitledBorder("Profiel voorkeuren"));

        // Set colors
        innerBoxPanel2.setBackground(Color.gray);
        innerFlowPanel2.setBackground(Color.gray);
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        recommendedPanel.setBackground(Color.gray);
        recommendedPanel2.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);


        // Recommended panel labels
        JLabel recommendedLabel = new JLabel("Selecteer eerst een profiel.");

        JLabel selectProfileLabel = new JLabel("Selecteer profiel: ");

        this.selectProfileBox.setMinimumSize(new Dimension(230, 25));
        this.selectProfileBox.setPreferredSize(new Dimension(230, 25));
        this.selectProfileBox.setName("selectProfileBox");


        JButton selectProfileButton = new JButton("Selecteer");
        selectProfileButton.addActionListener(new ProfilesPanelSelectActionListener(this.selectedProfileName, this.selectProfileBox, recommendedLabel, this.selectedAccountName));

        JButton createProfileButton = new JButton("Nieuw");
        createProfileButton.addActionListener(new ProfilesPanelCrudActionListener(0, selectProfileBox, this.selectedAccountName));

        firstComponentPanel.add(selectProfileLabel);
        firstComponentPanel.add(this.selectProfileBox);
        firstComponentPanel.add(selectProfileButton);
        firstComponentPanel.add(createProfileButton);

        JButton editProfileButton = new JButton("Bewerk");
        editProfileButton.addActionListener(new ProfilesPanelCrudActionListener(1, selectProfileBox, this.selectedAccountName));

        JButton deleteProfileButton = new JButton("Verwijder");
        deleteProfileButton.addActionListener(new ProfilesPanelDeleteActionListener(selectProfileBox, this.selectedAccountName));

        secondComponentPanel.add(editProfileButton);
        secondComponentPanel.add(deleteProfileButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(secondComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

        JButton viewLogsButton = new JButton("Bekijk logs");
        viewLogsButton.addActionListener(new ProfilesPanelViewLogActionListener(this.selectProfileBox, this.selectedAccountName));
        recommendedPanel2.add(viewLogsButton);

        recommendedPanel.add(recommendedLabel);
        innerBoxPanel2.add(recommendedPanel);
        innerBoxPanel2.add(recommendedPanel2);

        innerFlowPanel.add(innerBoxPanel);
        innerFlowPanel2.add(innerBoxPanel2);

        // Keep FlowPanels aligned
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerFlowPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(innerFlowPanel);
        this.add(innerFlowPanel2);

        // Create space for panel alignment
        for(int x = 0; x < 20; x++)
        {
            fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    /**
     * Returns the selectProfileBox for reference sake
     *
     * @return
     */
    public JComboBox<String> getSelectProfileBox()
    {
        return this.selectProfileBox;
    }

}
