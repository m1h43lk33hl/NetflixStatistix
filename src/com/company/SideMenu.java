package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideMenu extends JPanel {

    private int buttonWidth = 250;
    private int buttonHeight = 70;

    public SideMenu()
    {
        this.createComponents();
    }

    /**
     * Creates SideMenu components
     */
    private void createComponents()
    {
        Map<Integer, String> sideMenuButtonList = new HashMap<Integer, String>();

        sideMenuButtonList.put(1, "Account overzicht");
        sideMenuButtonList.put(2, "Profiel overzicht");
        sideMenuButtonList.put(3, "Series");
        sideMenuButtonList.put(4, "Films");

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(layout);
        this.setBackground(Color.darkGray);

        // Add initial space
        this.add(Box.createRigidArea(new Dimension(0,90)));

        for ( Map.Entry<Integer, String> entry : sideMenuButtonList.entrySet()) {
            int buttonOffset = entry.getKey();
            String buttonTitle = entry.getValue();

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
            button.setFont(new Font("Segoe UI", Font.BOLD, 18));

            // Set button offset for cardLayout
            button.setName(Integer.toString(buttonOffset));

            button.addActionListener(new sideMenuButtonActionListener(this));
            this.add(button);

            // Create space between buttons
            this.add(Box.createRigidArea(new Dimension(0,20)));
        }
    }
}

class sideMenuButtonActionListener implements ActionListener
{

    private SideMenu sideMenu;

    public sideMenuButtonActionListener(SideMenu sideMenu)
    {
        this.sideMenu = sideMenu;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        for(Component comp : sideMenu.getRootPane().getContentPane().getComponents()){

            if(comp.getName() == "mainPanel")
            {
                // Get buttonOffset
                JButton buttonClicked = (JButton)actionEvent.getSource();
                int buttonOffset = Integer.parseInt(buttonClicked.getName());

                MainPanel p = (MainPanel)comp;
                CardLayout mainCardLayout = p.getCardLayout();

                // Select names of mapped button offsets to layouts within cardLayout
                switch(buttonOffset)
                {
                    case 1:
                        mainCardLayout.show(p, "accountsPanel");
                        break;

                    case 2:
                        mainCardLayout.show(p, "profilesPanel");
                        break;

                    case 3:
                        mainCardLayout.show(p, "seriesPanel");
                        break;

                    case 4:
                        mainCardLayout.show(p, "filmsPanel");
                        break;


                }

            }
        }

    }
}
