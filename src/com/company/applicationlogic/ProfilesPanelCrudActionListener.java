package com.company.applicationlogic;

import com.company.ErrorDialog;
import com.company.ErrorMessages;
import com.company.presentation.InternalFrame;
import com.company.presentation.ProfilesCrudPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class ProfilesPanelCrudActionListener handles logic for ProfilesPanel
 */
public class ProfilesPanelCrudActionListener implements ActionListener {

    private int crudMode = 0;

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    /**
     * Class constructor
     *
     * @param crudMode
     * @param selectProfileBox
     * @param selectedAccountName
     */
    public ProfilesPanelCrudActionListener(int crudMode, JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.crudMode = crudMode;
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

        if(this.crudMode == 1 && this.selectProfileBox.getSelectedItem() == null)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_NOT_SELECTED);
            return;
        }

        try
        {
            if(this.selectProfileBox.getSelectedItem().toString().equals("<Geen gegevens>"))
            {
                ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_NOT_SELECTED);
                return;
            }
        }
        catch (Exception e) {
        }
        ProfilesCrudPanel profilesCrudPanel = new ProfilesCrudPanel(this.crudMode, this.selectProfileBox, this.selectedAccountName);

        InternalFrame internalFrame = new InternalFrame(profilesCrudPanel);
        profilesCrudPanel.setInternalFrame(internalFrame);

        internalFrame.run();
    }
}
