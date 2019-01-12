package com.company;

import javax.swing.*;
import java.awt.*;

public class NetflixStatistixUI implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("NetflixStatistix");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setLocation(new Point(100,300));

        this.addElements(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void addElements(Container container)
    {
        container.add(new SouthBar(), BorderLayout.SOUTH);
        container.add(new SideMenu(), BorderLayout.WEST);
    }
}
