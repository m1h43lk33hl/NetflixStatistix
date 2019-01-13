package com.company;

import javax.swing.*;
import java.awt.*;

public class ProfilesPanel extends JPanel {

    public ProfilesPanel()
    {
        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }


    /**
     * Create ProfilesPanel components
     */
    private void createComponents()
    {

        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        innerPanel.setBorder(BorderFactory.createTitledBorder("Profiel"));


        innerPanel.setBackground(Color.gray);

        JLabel selectProfileLabel = new JLabel("Selecteer profiel: ");

        JComboBox<String> selectProfileBox = new JComboBox<>(this.returnProfileNames());
        selectProfileBox.setMinimumSize(new Dimension(230, 25));
        selectProfileBox.setPreferredSize(new Dimension(230, 25));

        JButton selectProfileButton = new JButton("Selecteer");


        innerPanel.add(selectProfileLabel);
        innerPanel.add(selectProfileBox);
        innerPanel.add(selectProfileButton);

        innerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(innerPanel);


        // Create space for panel allignment

        for(int x = 0; x < 3; x++)
        {

            JPanel fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    /**
     * Returns a string array of names of profiles by selected account
     *
     * @return
     */
    private String[] returnProfileNames(){
        String[] x = {"asjdashdaskjh", "asdjsaiodjasd", "ajdkadjsaljlsdj"};
        return x;

    }
}
