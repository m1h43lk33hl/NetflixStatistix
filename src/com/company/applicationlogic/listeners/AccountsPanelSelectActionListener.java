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
 * Class AccountsPanelSelectActionListener handles selection implementation for class AccountsPanel
 */
public class AccountsPanelSelectActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JComboBox<String> selectAccountBox;
    private JLabel selectedAccountNameSeries;
    private JLabel selectedAccountNameFilm;
    private Object[] accountLabelArray;


    /**
     * Class constructor for AccountsPanelSelectActionListener
     *
     * @param selectProfileBox
     * @param selectAccountBox
     * @param selectedAccountNameSeries
     * @param selectedAccountNameFilm
     * @param accountLabelArray
     */
    public AccountsPanelSelectActionListener(JComboBox<String> selectProfileBox, JComboBox<String> selectAccountBox, JLabel selectedAccountNameSeries, JLabel selectedAccountNameFilm, Object[] accountLabelArray )
    {
        this.selectProfileBox = selectProfileBox;
        this.selectAccountBox = selectAccountBox;
        this.selectedAccountNameSeries = selectedAccountNameSeries;
        this.selectedAccountNameFilm = selectedAccountNameFilm;
        this.accountLabelArray = accountLabelArray;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.selectAccountBox.getSelectedItem() == null)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_NOT_VALID);
            return;
        }

        this.selectedAccountNameSeries.setText(this.selectAccountBox.getSelectedItem().toString());
        this.selectedAccountNameFilm.setText(this.selectAccountBox.getSelectedItem().toString());

        DefaultComboBoxModel model = new DefaultComboBoxModel(this.returnProfileNames().toArray());
        this.selectProfileBox.setModel(model);
        this.setAccountLabelNames();
    }

    /**
     * Returns a list of names of accounts by selected profile
     *
     * @return
     */
    private void setAccountLabelNames() {
        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Naam, Straatnaam, Huisnummer, Toevoeging, Woonplaats FROM Account WHERE Account.Naam='"+this.selectAccountBox.getSelectedItem()+"';");

            while (rs.next()) {

                JLabel accountNameSelectLabel = (JLabel) this.accountLabelArray[0];
                JLabel accountStreetNameSelectLabel = (JLabel) this.accountLabelArray[1];
                JLabel accountHouseNumberSelectLabel = (JLabel) this.accountLabelArray[2];
                JLabel accountHouseNumberExtraSelectLabel = (JLabel) this.accountLabelArray[3];
                JLabel accountResidenceSelectLabel = (JLabel) this.accountLabelArray[4];

                accountNameSelectLabel.setText(rs.getString("Naam"));
                accountStreetNameSelectLabel.setText(rs.getString("Straatnaam"));
                accountHouseNumberSelectLabel.setText(rs.getString("Huisnummer"));
                accountHouseNumberExtraSelectLabel.setText(rs.getString("Toevoeging"));
                accountResidenceSelectLabel.setText(rs.getString("Woonplaats"));
            }

        }
        catch (Exception e)
        {
        }
    }

    /**
     * Returns a list of names of profiles by selected account
     *
     * @return
     */
    private java.util.List<String> returnProfileNames() {
        List<String> accountList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Profiel.Naam FROM Profiel WHERE Profiel.AccountNaam = (SELECT Account.Naam FROM Account WHERE Account.Naam = '"+this.selectAccountBox.getSelectedItem()+"');");

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
