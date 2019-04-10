package com.company.applicationlogic;

import com.company.datastorage.Database;
import com.company.presentation.InternalFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ProfilesCrudPanelActionListener handles logic for ProfilesCrudPanel
 */
public class ProfilesCrudPanelActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private int crudMode;
    private JTextField profileNameTextField;
    private JTextField profileAgeTextField;
    private InternalFrame internalFrame;


    /**
     * Class constructor for ProfilesCrudPanelActionListener
     *
     * @param crudMode
     * @param selectProfileBox
     * @param selectedAccountName
     * @param profileNameTextField
     * @param profileAgeTextField
     * @param internalFrame
     */
    public ProfilesCrudPanelActionListener(int crudMode, JComboBox<String> selectProfileBox, JLabel selectedAccountName, JTextField profileNameTextField, JTextField profileAgeTextField, InternalFrame internalFrame)
    {
        this.crudMode = crudMode;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.profileAgeTextField = profileAgeTextField;
        this.profileNameTextField = profileNameTextField;
        this.internalFrame = internalFrame;
    }


    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.crudMode==0)
        {
            if(this.createProfile())
            {
                // Refresh profile box
                this.selectProfileBox.setModel(new DefaultComboBoxModel<String>(this.returnProfileNames().toArray(new String[0])));
                this.selectProfileBox.setSelectedItem( ((JTextField)this.profileNameTextField).getText());

                // Close JFrame
                this.internalFrame.close();
            }

        }
        else
        {
            if(this.editProfile())
            {
                // Refresh profile box
                this.selectProfileBox.setModel(new DefaultComboBoxModel<String>(this.returnProfileNames().toArray(new String[0])));
                this.selectProfileBox.setSelectedItem( ((JTextField)this.profileNameTextField).getText());

                // Close JFrame
                this.internalFrame.close();            }
        }
    }

    /**
     * Creates a profile
     *
     * @return
     */
    private boolean createProfile()
    {

        try{
            Database database = Database.getInstance();

            String SQLcheck = "SELECT * FROM Profiel WHERE Profiel.AccountNaam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.profileNameTextField.getText()+"';";
            if (database.query(SQLcheck).next()) {
                ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_DATA_NOT_VALID);
                return false;
            }

            String SQL = "INSERT INTO Profiel VALUES('"+this.selectedAccountName.getText()+"', '"+this.profileNameTextField.getText()+"', "+this.profileAgeTextField.getText()+");";
            System.out.println(SQL);
            database.queryDDL(SQL);
            return true;
        }
        catch (Exception e)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_DATA_NOT_VALID);
            return false;
        }
    }

    /**
     * Edits a profile
     *
     * @return
     */
    private boolean editProfile()
    {
        try{
            Database database = Database.getInstance();

            String SQLcheck = "SELECT * FROM Profiel WHERE Profiel.AccountNaam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.profileNameTextField.getText()+"';";
            if (database.query(SQLcheck).next()) {
                ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_DATA_NOT_VALID);
                return false;
            }

            String SQL = "UPDATE Profiel SET Naam='"+this.profileNameTextField.getText()+"', Leeftijd="+this.profileAgeTextField.getText()+" WHERE AccountNaam = '"+this.selectedAccountName.getText()+"' AND Naam='"+this.selectProfileBox.getSelectedItem().toString()+"';";
            System.out.println(SQL);
            database.queryDDL(SQL);
            return true;
        }
        catch (Exception e)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.PROFILE_DATA_NOT_VALID);
            return false;
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
