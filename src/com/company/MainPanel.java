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
    }

    public CardLayout getCardLayout()
    {
        return this.mainCardLayout;
    }
}
