package com.company.applicationlogic;

import com.company.presentation.InternalFrame;
import com.company.presentation.LogsCrudCreatePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class LogsCrudPanelCreateActionListener handles logic for LogsCrudPanel
 */
public class LogsCrudPanelCreateActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private JTable table;

    /**
     * Class constructor for LogsCrudPanelCreateActionListener
     *
     * @param selectProfileBox
     * @param selectedAccountName
     * @param table
     */
    public LogsCrudPanelCreateActionListener(JComboBox<String> selectProfileBox, JLabel selectedAccountName, JTable table)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.table = table;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println(this.table);

        LogsCrudCreatePanel logsCrudCreatePanel = new LogsCrudCreatePanel(this.selectProfileBox, this.selectedAccountName, this.table);
        InternalFrame internalFrame = new InternalFrame(logsCrudCreatePanel);
        logsCrudCreatePanel.setInternalFrame(internalFrame);

        internalFrame.run();
    }
}
