package com.company.presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Class NetflixStatistixUI implements the mainframe functionality of the application
 */
public class NetflixStatistixUI implements Runnable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("NetflixStatistix");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Center frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        frame.setPreferredSize(new Dimension((width/2 + (width/6)), 600));
        frame.setSize(width/2 + (width/6), height/2);
        frame.setLocationRelativeTo(null);

        //Add panels
        this.addElements(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds elements to container
     *
     * @param container
     */
    private void addElements(Container container)
    {
        container.add(new SouthBar(), BorderLayout.SOUTH);
        container.add(new SideMenu(), BorderLayout.WEST);
        container.add(new MainPanel());
    }
}

