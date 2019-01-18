package com.company;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountsPanelDeleteActionListener implements ActionListener {

    private JComboBox<String> selectAccountBox;

    public AccountsPanelDeleteActionListener(JComboBox<String> selectAccountBox)
    {
        this.selectAccountBox = selectAccountBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Weet je het zeker", "Verwijder account", dialogButton);

        if(dialogResult == 0) {
            this.deleteAccount();
        } else {
            System.out.println("No Option");
        }
    }


    private void deleteAccount()
    {
        try{
            Database database = Database.getInstance();
            database.queryDDL("");
        }
        catch (Exception e)
        {

        }
    }
}
