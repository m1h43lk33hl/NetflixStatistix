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



        ProfilesPanel profilesPanel = new ProfilesPanel();
        JComboBox<String> selectProfileBox = profilesPanel.getSelectProfileBox();
        profilesPanel.setName("profilesPanel");

        AccountsPanel accountsPanel = new AccountsPanel(selectProfileBox);
        accountsPanel.setName("accountsPanel");

        FilmsPanel filmsPanel = new FilmsPanel();
        filmsPanel.setName("filmsPanel");

        SeriesPanel seriesPanel = new SeriesPanel();
        seriesPanel.setName("seriesPanel");

        this.add(accountsPanel, accountsPanel.getName());
        this.add(profilesPanel, profilesPanel.getName());
        this.add(filmsPanel, filmsPanel.getName());
        this.add(seriesPanel, seriesPanel.getName());



    }

    public CardLayout getCardLayout()
    {
        return this.mainCardLayout;
    }
}
