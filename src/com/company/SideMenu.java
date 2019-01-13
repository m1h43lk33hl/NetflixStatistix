package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SideMenu extends JPanel {

    private int buttonWidth = 250;
    private int buttonHeight = 40;

    public SideMenu()
    {
        this.createComponents();
    }

    /**
     * Creates SideMenu components
     */
    private void createComponents()
    {
        List<String> buttonTitleList = new ArrayList<String>();
        buttonTitleList.add("Account overzicht");
        buttonTitleList.add("Profiel overzicht");
        buttonTitleList.add("Series");
        buttonTitleList.add("Films");

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(layout);
        this.setBackground(Color.darkGray);

        // Add initial space
        this.add(Box.createRigidArea(new Dimension(0,90)));

        for(String buttonTitle : buttonTitleList)
        {
            JButton button = new JButton();

            // Set button size
            button.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));
            button.setMaximumSize(new Dimension(this.buttonWidth, this.buttonHeight));

            // Set button layout
            float[] hsbvals = new float[3];
            button.setText(buttonTitle);
            button.setBackground(new Color(39, 39, 39));
            button.setForeground(new Color(225,8,19));
            button.setFocusPainted(false);

            this.add(button);

            // Create space between buttons
            this.add(Box.createRigidArea(new Dimension(0,20)));
        }
    }
}
