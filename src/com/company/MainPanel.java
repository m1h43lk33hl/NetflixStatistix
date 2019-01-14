package com.company;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private CardLayout mainCardLayout;

    public MainPanel()
    {
        this.mainCardLayout = new CardLayout();
        this.setLayout(this.mainCardLayout);
        this.setName("mainPanel");

        this.setBackground(Color.gray);


        AccountsPanel accountsPanel = new AccountsPanel();
        accountsPanel.setName("accountsPanel");
        this.add(accountsPanel, accountsPanel.getName());

        ProfilesPanel profilesPanel = new ProfilesPanel();
        profilesPanel.setName("profilesPanel");
        this.add(profilesPanel, profilesPanel.getName());

        FilmsPanel filmsPanel = new FilmsPanel();
        filmsPanel.setName("filmsPanel");
        this.add(filmsPanel, filmsPanel.getName());

        SeriesPanel seriesPanel = new SeriesPanel();
        seriesPanel.setName("seriesPanel");
        this.add(seriesPanel, seriesPanel.getName());

    }

    public CardLayout getCardLayout()
    {
        return this.mainCardLayout;
    }
}
