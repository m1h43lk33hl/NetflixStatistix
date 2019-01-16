package com.company;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

/**
 * Class MainPanel handles the implementation of the panels within the CardLayout.
 */
public class MainPanel extends JPanel {

    private CardLayout mainCardLayout;

    /**
     * Class constructor for MainPanel
     */
    public MainPanel()
    {
        this.mainCardLayout = new CardLayout();
        this.setLayout(this.mainCardLayout);
        this.setName("mainPanel");
        this.setBackground(Color.gray);

        // Create panels for cardlayout
        FilmsPanel filmsPanel = new FilmsPanel();
        filmsPanel.setName("filmsPanel");

        SeriesPanel seriesPanel = new SeriesPanel();
        seriesPanel.setName("seriesPanel");

        ProfilesPanel profilesPanel = new ProfilesPanel(seriesPanel.getSelectedProfileName(), seriesPanel.getSelectedAccountName());
        JComboBox<String> selectProfileBox = profilesPanel.getSelectProfileBox();
        profilesPanel.setName("profilesPanel");

        AccountsPanel accountsPanel = new AccountsPanel(selectProfileBox, seriesPanel.getSelectedAccountName(), filmsPanel.getSelectedAccountName());
        accountsPanel.setName("accountsPanel");

        // Adds panels to cardlayout panel
        this.add(accountsPanel, accountsPanel.getName());
        this.add(profilesPanel, profilesPanel.getName());
        this.add(filmsPanel, filmsPanel.getName());
        this.add(seriesPanel, seriesPanel.getName());

    }

    /**
     * Returns this cardLayout for reference sake
     *
     * @return
     */
    public CardLayout getCardLayout()
    {
        return this.mainCardLayout;
    }
}
