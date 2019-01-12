package com.company;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;

public class NetflixStatistixUI implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("NetflixStatistix");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));

        // Center frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        frame.setSize(width/2, height/2);
        frame.setLocationRelativeTo(null);

        //Add panels
        this.addElements(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void addElements(Container container)
    {
        container.add(new SouthBar(), BorderLayout.SOUTH);
        container.add(new SideMenu(), BorderLayout.WEST);
        container.add(new MainPanel());
    }


    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}

