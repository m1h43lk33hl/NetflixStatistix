package com.company;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel()
    {
        this.setLayout(new CardLayout());
        this.setBackground(Color.gray);

        this.add(new AccountsPanel());
        this.add(new ProfilesPanel());

    }
}
