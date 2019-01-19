package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfilesPanelDeleteActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    public ProfilesPanelDeleteActionListener(JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Weet je het zeker", "Verwijder account", dialogButton);


        if(dialogResult == 0) {
            this.deleteProfile();
            // REFRESH AND CLOSE WINDOW
            this.selectProfileBox.setModel(new DefaultComboBoxModel<String>(this.returnProfileNames().toArray(new String[0])));
        } else {
            System.out.println("No Option");
        }
    }

    private void deleteProfile()
    {
        try{
            Database database = Database.getInstance();
            System.out.println("DELETE FROM Profiel WHERE AccountNaam='"+this.selectedAccountName.getText()+"' AND Naam='"+this.selectProfileBox.getSelectedItem().toString()+"';");
            database.queryDDL("DELETE FROM Profiel WHERE AccountNaam='"+this.selectedAccountName.getText()+"' AND Naam='"+this.selectProfileBox.getSelectedItem().toString()+"';");
        }
        catch (Exception e)
        {

        }
    }

    /**
     * Returns a list of names of accounts by selected profile
     *
     * @return
     */
    private java.util.List<String> returnProfileNames() {
        List<String> profileList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Profiel.Naam FROM Profiel WHERE Profiel.AccountNaam = (SELECT Account.Naam FROM Account WHERE Account.Naam = '"+this.selectedAccountName.getText()+"');");

            while (rs.next()) {
                profileList.add(rs.getString("Naam"));
            }

            return profileList;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
