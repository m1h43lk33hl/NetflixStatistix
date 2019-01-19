package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfilesCrudPanelActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private int crudMode;
    private JTextField profileNameTextField;
    private JTextField profileAgeTextField;
    private InternalFrame internalFrame;


    public ProfilesCrudPanelActionListener(int crudMode, JComboBox<String> selectProfileBox, JLabel selectedAccountName, JTextField profileNameTextField, JTextField profileAgeTextField, InternalFrame internalFrame)
    {
        this.crudMode = crudMode;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.profileAgeTextField = profileAgeTextField;
        this.profileNameTextField = profileNameTextField;
        this.internalFrame = internalFrame;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.crudMode==0)
        {
            this.createProfile();
        }
        else
        {
            this.editProfile();
        }

        // Refresh profile box
        this.selectProfileBox.setModel(new DefaultComboBoxModel<String>(this.returnProfileNames().toArray(new String[0])));
        this.selectProfileBox.setSelectedItem( ((JTextField)this.profileNameTextField).getText());

        // Close JFrame
        this.internalFrame.close();

    }

    private void createProfile()
    {
        try{
            Database database = Database.getInstance();
            String SQL = "INSERT INTO Profiel VALUES('"+this.selectedAccountName.getText()+"', '"+this.profileNameTextField.getText()+"', "+this.profileAgeTextField.getText()+");";
            System.out.println(SQL);
            database.queryDDL(SQL);
        }
        catch (Exception e)
        {

        }
    }

    private void editProfile()
    {
        try{
            Database database = Database.getInstance();
            String SQL = "UPDATE Profiel SET Naam='"+this.profileNameTextField.getText()+"', Leeftijd="+this.profileAgeTextField.getText()+" WHERE AccountNaam = '"+this.selectedAccountName.getText()+"' AND Naam='"+this.selectProfileBox.getSelectedItem().toString()+"';";
            System.out.println(SQL);
            database.queryDDL(SQL);
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
