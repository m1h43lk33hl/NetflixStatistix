package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountsCrudPanelActionListener implements ActionListener  {

    private Object[] accountFieldArray;
    private int crudMode = 0;
    private JComboBox<String> selectAccountBox;
    private JButton selectAccountButton;
    private InternalFrame internalFrame;

    public AccountsCrudPanelActionListener(Object[] accountFieldArray, JButton selectAccountButton, int crudMode, JComboBox<String> selectAccountBox, InternalFrame internalFrame)
    {
        this.accountFieldArray = accountFieldArray;
        this.crudMode = crudMode;
        this.selectAccountBox = selectAccountBox;
        this.selectAccountButton = selectAccountButton;
        this.internalFrame = internalFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("asdadsa");
        System.out.println(this.crudMode);

        if (this.crudMode == 0) {
            this.createAccount();
        } else {
            System.out.println("asIIII");
            System.out.println(this.crudMode);
            this.updateAccount();
        }

        // UPDATE Components
//        this.selectAccountBox = new JComboBox<>(this.returnAccountNames().toArray(new String[0]));

        // SET TO SELECTED
        // TRIGGER SELECT BUTTON

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

        // Close frame
        this.internalFrame.close();


    }

    private void updateAccount()
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

            System.out.println(SQL);
            database.queryDDL(SQL);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    private void createAccount()
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

            String SQL = "INSERT INTO Account VALUES('"+ ((JTextField)this.accountFieldArray[0]).getText() +"', '"+((JTextField)this.accountFieldArray[1]).getText()+"', "+((JTextField)this.accountFieldArray[2]).getText()+", "+extra+", '"+((JTextField)this.accountFieldArray[4]).getText()+"')";
            System.out.println(SQL);
            database.query(SQL);
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
