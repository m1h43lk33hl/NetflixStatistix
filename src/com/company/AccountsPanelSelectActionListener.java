package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountsPanelSelectActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JComboBox<String> selectAccountBox;
    private JLabel selectedAccountName;

    public AccountsPanelSelectActionListener(JComboBox<String> selectProfileBox, JComboBox<String> selectAccountBox, JLabel selectedAccountName)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectAccountBox = selectAccountBox;
        this.selectedAccountName = selectedAccountName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        this.selectedAccountName.setText(this.selectAccountBox.getSelectedItem().toString());
        DefaultComboBoxModel model = new DefaultComboBoxModel(this.returnProfileNames().toArray());
        this.selectProfileBox.setModel(model);
    }

    /**
     * Returns a list of names of accounts by selected profile
     *
     * @return
     */
    public java.util.List<String> returnProfileNames() {
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
