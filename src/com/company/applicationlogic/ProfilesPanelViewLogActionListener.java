package com.company.applicationlogic;

import com.company.presentation.InternalFrame;
import com.company.presentation.LogsCrudPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
  * Class ProfilesPanelViewLogActionListener handles view logic for ProfilesPanel
  */
public class ProfilesPanelViewLogActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    /**
     * Class constructor
     *
     * @param selectProfileBox
     * @param selectedAccountName
     */
    public ProfilesPanelViewLogActionListener(JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.selectProfileBox.getSelectedItem() == null || this.selectProfileBox.getSelectedItem().toString().equals("<Geen gegevens>"))
        {
            ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_NOT_SELECTED);
            return;
        }

        LogsCrudPanel logsCrudPanel = new LogsCrudPanel(this.selectProfileBox, this.selectedAccountName);

        InternalFrame internalFrame = new InternalFrame(logsCrudPanel);
        logsCrudPanel.setInternalFrame(internalFrame);

        internalFrame.run();
    }
}
