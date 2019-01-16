package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountsPanelFilterActionListener handles the filter implementation for class AccountsPanel
 */
public class AccountsPanelFilterActionListener implements ActionListener
{
    private JTextField filterProfileAmountTextField;
    private JComboBox<String> accountFilterBox;
    private JComboBox<String> selectProfileBox;


    /**
     * Class constructor for AccountsPanelFilterActionListener
     *
     * @param filterProfileAmountTextField
     * @param accountFilterBox
     * @param selectProfileBox
     */
    public AccountsPanelFilterActionListener(JTextField filterProfileAmountTextField, JComboBox<String> accountFilterBox, JComboBox<String> selectProfileBox)
    {
        this.filterProfileAmountTextField = filterProfileAmountTextField;
        this.accountFilterBox = accountFilterBox;
        this.selectProfileBox = selectProfileBox;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        DefaultComboBoxModel model = new DefaultComboBoxModel( this.getFilteredAccountNames().toArray(new String[0]));
        this.selectProfileBox.setModel(model);
    }

    /**
     * Returns a list of filtered account names
     *
     * @return
     */
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
            ResultSet resultSet = database.query("SELECT Account.Naam FROM Account INNER JOIN Profiel ON Profiel.AccountNaam = Account.Naam GROUP BY Account.Naam, Profiel.AccountNaam HAVING COUNT(Profiel.AccountNaam) " + accountFilterBoxOption + " " + this.filterProfileAmountTextField.getText());

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
