package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

public class ProfilesPanel extends JPanel {

    private JComboBox<String> selectProfileBox = new JComboBox<>(this.returnProfileNames());


    public ProfilesPanel()
    {
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
        JPanel innerBoxPanel = new JPanel();
        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        JPanel firstComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fillerPanel = new JPanel();


        // Set colors
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Profiel"));
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);


        JLabel selectProfileLabel = new JLabel("Selecteer profiel: ");

        this.selectProfileBox.setMinimumSize(new Dimension(230, 25));
        this.selectProfileBox.setPreferredSize(new Dimension(230, 25));
        this.selectProfileBox.setName("selectProfileBox");

        JButton selectProfileButton = new JButton("Selecteer");

        firstComponentPanel.add(selectProfileLabel);
        firstComponentPanel.add(this.selectProfileBox);
        firstComponentPanel.add(selectProfileButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerBoxPanel.add(firstComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

        innerFlowPanel.add(innerBoxPanel);
        this.add(innerFlowPanel);

        // Create space for panel alignment
        for(int x = 0; x < 20; x++)
        {
            fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    public JComboBox<String> getSelectProfileBox()
    {
        return this.selectProfileBox;
    }

    /**
     * Returns a string array of names of accounts by selected profile
     *
     * @return
     */
    private String[] returnProfileNames(){
        String[] x = {"asjdashdaskjh", "asdjsaiodjasd", "ajdkadjsaljlsdj"};
        return x;

    }
}
