package com.company;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

            // Refresh list
            this.selectAccountBox.setModel(new DefaultComboBoxModel<String>(this.returnAccountNames().toArray(new String[0])));
        } else {
        }
    }


    private void deleteAccount()
    {
        try{
            Database database = Database.getInstance();
            System.out.println("DELETE FROM Account WHERE Naam='"+this.selectAccountBox.getSelectedItem().toString()+"';");
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
                System.out.println(rs.getString("Naam"));
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
