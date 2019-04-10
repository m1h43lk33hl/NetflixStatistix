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
 * Class AccountsCrudPanelActionListener handles actions for AccountsCrudPanel
 */
public class AccountsCrudPanelActionListener implements ActionListener  {

    private Object[] accountFieldArray;
    private int crudMode = 0;
    private JComboBox<String> selectAccountBox;
    private JButton selectAccountButton;
    private InternalFrame internalFrame;

    /**
     * Class constructor for AccountsCrudPanelActionListener
     *
     * @param accountFieldArray
     * @param selectAccountButton
     * @param crudMode
     * @param selectAccountBox
     * @param internalFrame
     */
    public AccountsCrudPanelActionListener(Object[] accountFieldArray, JButton selectAccountButton, int crudMode, JComboBox<String> selectAccountBox, InternalFrame internalFrame)
    {
        this.accountFieldArray = accountFieldArray;
        this.crudMode = crudMode;
        this.selectAccountBox = selectAccountBox;
        this.selectAccountButton = selectAccountButton;
        this.internalFrame = internalFrame;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {


        if (this.crudMode == 0) {
            if(!this.createAccount())
            {
                return;
            }

        } else {
            if(!this.updateAccount())
            {
                return;
            }
        }

        if(this.crudMode == 1)
        {
            this.selectAccountBox.setModel(new DefaultComboBoxModel<String>(this.returnAccountNames().toArray(new String[0])));
            this.selectAccountBox.setSelectedItem( ((JTextField)this.accountFieldArray[0]).getText());
            this.selectAccountButton.doClick();
        }
        else
        {
            this.selectAccountBox.setModel(new DefaultComboBoxModel<String>(this.returnAccountNames().toArray(new String[0])));
            this.selectAccountBox.setSelectedItem( ((JTextField)this.accountFieldArray[0]).getText());
        }

        this.internalFrame.close();

    }

    /**
     * Updates account and returns false when failed
     *
     * @return
     */
    private Boolean updateAccount()
    {
        try{
            Database database = Database.getInstance();
            String extra = ((JTextField)this.accountFieldArray[3]).getText();


            if(extra.equals(""))
            {
                extra = "NULL";
            }
            else
            {
                extra = "'"+extra+"'";
            }

            String SQL = "UPDATE Account\n" +
                    "SET Naam='"+((JTextField)this.accountFieldArray[0]).getText()+"', Straatnaam='"+((JTextField)this.accountFieldArray[1]).getText()+"', Huisnummer="+((JTextField)this.accountFieldArray[2]).getText()+", Toevoeging="+extra+", Woonplaats='"+((JTextField)this.accountFieldArray[4]).getText()+"'\n" +
                    "WHERE Naam='"+this.selectAccountBox.getSelectedItem().toString()+"';";

            database.queryDDL(SQL);

            return true;
        }
        catch (Exception e)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_DATA_NOT_VALID);
            return  false;
        }

    }


    /**
     * Creates account and returns false when failed
     *
     * @return
     */
    private Boolean createAccount()
    {
        try{

            Database database = Database.getInstance();
            String extra = ((JTextField)this.accountFieldArray[3]).getText();

            if(extra.equals(""))
            {
                extra = "NULL";
            }
            else
            {
                extra = "'"+extra+"'";
            }

            // Error if account already exists
            String SQLcheck = "SELECT Account.Naam FROM Account WHERE Account.Naam = '"+((JTextField)this.accountFieldArray[0]).getText()+"';";
            if (database.query(SQLcheck).next()) {
                ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_EXISTS);
                return false;
            }

            String SQL = "INSERT INTO Account VALUES('"+ ((JTextField)this.accountFieldArray[0]).getText() +"', '"+((JTextField)this.accountFieldArray[1]).getText()+"', "+((JTextField)this.accountFieldArray[2]).getText()+", "+extra+", '"+((JTextField)this.accountFieldArray[4]).getText()+"')";
            System.out.println(SQL);
            database.query(SQL);
            return true;
        }
        catch (Exception e)
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_DATA_NOT_VALID);
            return false;
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
