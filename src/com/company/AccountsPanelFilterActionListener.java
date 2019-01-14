package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountsPanelFilterActionListener implements ActionListener
{
    private JTextField filterProfileAmountTextField;
    private JComboBox<String> accountFilterBox;
    private JComboBox<String> selectProfileBox;


    public AccountsPanelFilterActionListener(JTextField filterProfileAmountTextField, JComboBox<String> accountFilterBox, JComboBox<String> selectProfileBox)
    {
        this.filterProfileAmountTextField = filterProfileAmountTextField;
        this.accountFilterBox = accountFilterBox;
        this.selectProfileBox = selectProfileBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        DefaultComboBoxModel model = new DefaultComboBoxModel( this.getFilteredAccountNames().toArray(new String[0]));
        this.selectProfileBox.setModel(model);
    }

    private List<String> getFilteredAccountNames()
    {
        List<String> filteredAccountNames = new ArrayList<>();
        String accountFilterBoxOption;


        if(this.accountFilterBox.getSelectedIndex() == 0)
        {
            accountFilterBoxOption = "=";
        }
        else if(this.accountFilterBox.getSelectedIndex() == 1)
        {
            accountFilterBoxOption = "<";
        }
        else{
            accountFilterBoxOption = ">";
        }

        try {

            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT Account.Naam FROM Account INNER JOIN Profiel ON Profiel.AccountID = Account.AccountID GROUP BY Account.Naam, Profiel.AccountID HAVING COUNT(Profiel.AccountID) " + accountFilterBoxOption + " " + this.filterProfileAmountTextField.getText());

            while (resultSet.next()) {
                filteredAccountNames.add(resultSet.getString("Naam"));
            }
        }
        catch(Exception e)
        {
            return null;
        }

        return filteredAccountNames;

    }
}
