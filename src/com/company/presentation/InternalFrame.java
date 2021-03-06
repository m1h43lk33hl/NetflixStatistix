package com.company.presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Class InternalFrame is an InternalFrame for internal windows
 */
public class InternalFrame implements Runnable {

    private JPanel mainPanel;
    private JFrame internalFrame;

    /**
     * Class constructor for InternalFrame
     *
     * @param mainPanel
     */
    public InternalFrame(JPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("NetflixStatistix");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.internalFrame = frame;

        // Center frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        frame.setPreferredSize(new Dimension((width/4 + (width/6)), 300));
        frame.setSize(width/4 + (width/6), height/4);
        frame.setLocationRelativeTo(null);

        frame.add(this.mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Closes InternalFrame
     */
    public void close()
    {
        this.internalFrame.dispose();
    }
}
