package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SideMenu extends JPanel {

    private int buttonWidth = 250;
    private int buttonHeight = 30;

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
        this.setBackground(Color.white);

        // Add initial space
        this.add(Box.createRigidArea(new Dimension(0,90)));

        for(String buttonTitle : buttonTitleList)
        {
            JButton button = new JButton();

            // Set button size
            button.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));
            button.setMaximumSize(new Dimension(this.buttonWidth, this.buttonHeight));

            button.setText(buttonTitle);

            this.add(button);
            // Create space between buttons
            this.add(Box.createRigidArea(new Dimension(0,20)));
        }
    }
}
