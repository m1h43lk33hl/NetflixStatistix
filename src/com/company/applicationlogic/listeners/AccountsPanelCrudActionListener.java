package com.company.applicationlogic.listeners;

import com.company.applicationlogic.errorutils.ErrorDialog;
import com.company.applicationlogic.errorutils.ErrorMessages;
import com.company.presentation.AccountsCrudPanel;
import com.company.presentation.InternalFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountsPanelCrudActionListener implements ActionListener {

    private int crudMode = 0;
    private  Object[] accountLabelArray;
    private JComboBox<String> selectAccountBox;
    private JButton selectAccountButton;

    public AccountsPanelCrudActionListener(int crudMode, JButton selectAccountButton, JComboBox<String> selectAccountBox )
    {
        this.crudMode = crudMode;
        this.selectAccountButton = selectAccountButton;
        this.selectAccountBox = selectAccountBox;
    }

    public AccountsPanelCrudActionListener(int crudMode, JButton selectAccountButton, Object[] accountLabelArray, JComboBox<String> selectAccountBox)
    {
        this.crudMode = crudMode;
        this.selectAccountButton = selectAccountButton;
        this.accountLabelArray = accountLabelArray;
        this.selectAccountBox = selectAccountBox;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.crudMode == 0)
        {
            AccountsCrudPanel accountsCrudPanel = new AccountsCrudPanel(0, this.selectAccountButton, this.selectAccountBox);
            InternalFrame internalFrame = new InternalFrame(accountsCrudPanel);
            accountsCrudPanel.setInternalFrame(internalFrame);
            internalFrame.run();
        }
        else
        {
            // Error if no account is selected
            if( ((JLabel) this.accountLabelArray[0]).getText().equals("<Geen gegevens>")){
                ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_NOT_SELECTED);
                return;
            }

            AccountsCrudPanel accountsCrudPanel = new AccountsCrudPanel(1, this.selectAccountButton,this.accountLabelArray, this.selectAccountBox);
            InternalFrame internalFrame = new InternalFrame(accountsCrudPanel);
            accountsCrudPanel.setInternalFrame(internalFrame);
            internalFrame.run();
        }
    }
}
