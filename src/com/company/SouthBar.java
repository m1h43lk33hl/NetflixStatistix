package com.company;

import javax.swing.*;

public class SouthBar extends JPanel{
    public SouthBar()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(new JLabel("Netflix Statistix"));
        this.add(Box.createHorizontalGlue());
        this.add(new JLabel("Informatica 2018-2019 23IVT1C1 Jordy van Ekelen, Henke Nachtegaal"));
    }
}
