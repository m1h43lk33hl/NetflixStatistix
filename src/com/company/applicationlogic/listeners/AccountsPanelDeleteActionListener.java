package com.company.applicationlogic.listeners;

import com.company.applicationlogic.errorutils.ErrorDialog;
import com.company.applicationlogic.errorutils.ErrorMessages;
import com.company.datastorage.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountsPanelDeleteActionListener handles delete function of class AccountsPanel
 */
public class AccountsPanelDeleteActionListener implements ActionListener {

    private JComboBox<String> selectAccountBox;
    private JLabel accountLabel;

    public AccountsPanelDeleteActionListener(JComboBox<String> selectAccountBox, JLabel accountLabel)
    {
        this.selectAccountBox = selectAccountBox;
        this.accountLabel = accountLabel;

    }

    /**
     * @{@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Error if no account is selected
        if( (this.accountLabel).getText().equals("<Geen gegevens>")){
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_NOT_SELECTED);
            return;
        }

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Weet je het zeker", "Verwijder account", dialogButton);

        if(dialogResult == 0) {
            this.deleteAccount();

            // Refresh list
            this.selectAccountBox.setModel(new DefaultComboBoxModel<String>(this.returnAccountNames().toArray(new String[0])));
        } else {
        }
    }

    /**
     * Deletes account
     */
    private void deleteAccount()
    {
        try{
            Database database = Database.getInstance();
            database.queryDDL("DELETE FROM Account WHERE Naam='"+this.selectAccountBox.getSelectedItem().toString()+"';");
        }
        catch (Exception e)
        {

        }
    }

    /**
     * Returns a list of account names of accounts by selected profile
     *
     * @return
     */
    private List<String> returnAccountNames() {
        List<String> accountList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Naam FROM Account");

            while (rs.next()) {
                accountList.add(rs.getString("Naam"));
            }

            return accountList;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
