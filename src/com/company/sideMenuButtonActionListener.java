package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sideMenuButtonActionListener implements ActionListener
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

                MainPanel mainPanel = (MainPanel)comp;
                CardLayout mainCardLayout = mainPanel.getCardLayout();

                // Select names of mapped button offsets to layouts within cardLayout
                switch(buttonOffset)
                {
                    case 1:
                        mainCardLayout.show(mainPanel, "accountsPanel");
                        break;

                    case 2:
                        mainCardLayout.show(mainPanel, "profilesPanel");
                        break;

                    case 3:
                        mainCardLayout.show(mainPanel, "seriesPanel");
                        break;

                    case 4:
                        mainCardLayout.show(mainPanel, "filmsPanel");
                        break;


                }

            }
        }

    }
}

