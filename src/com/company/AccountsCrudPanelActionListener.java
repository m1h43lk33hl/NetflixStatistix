package com.company;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountsCrudPanelActionListener implements ActionListener {

    private Object[] accountFieldArray;
    private int crudMode = 0;
    private JComboBox<String> selectAccountBox;

    public AccountsCrudPanelActionListener(Object[] accountFieldArray, int crudMode, JComboBox<String> selectAccountBox )
    {

        this.accountFieldArray = accountFieldArray;
        this.crudMode = crudMode;
        this.selectAccountBox = selectAccountBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("asdadsa");
        System.out.println(this.crudMode);

        if(this.crudMode == 0)
        {
            this.createAccount();
        }
        else
        {
            System.out.println("asIIII");
            System.out.println(this.crudMode);
            this.updateAccount();
        }

        // Close frame

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
                    "SET Naam='"+((JTextField)this.accountFieldArray[0]).getText()+"', Straatnaam='"+((JTextField)this.accountFieldArray[1]).getText()+"', Huisnummer="+((JTextField)this.accountFieldArray[2]).getText()+", Toevoeging='"+extra+"', Woonplaats='"+((JTextField)this.accountFieldArray[4]).getText()+"'\n" +
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
}
