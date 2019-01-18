package com.company;

import javax.swing.*;
import java.awt.*;

public class InternalFrame implements Runnable {

    private JPanel mainPanel;

    public InternalFrame(JPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("NetflixStatistix");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Center frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        frame.setPreferredSize(new Dimension((width/4 + (width/6)), 600));
        frame.setSize(width/4 + (width/6), height/2);
        frame.setLocationRelativeTo(null);

//        Add panels
        frame.add(this.mainPanel);

        frame.pack();
        frame.setVisible(true);
    }


}
