package com.company;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountsCrudPanelActionListener implements ActionListener {

    private Object[] accountFieldArray;
    private int crudMode = 0;

    public AccountsCrudPanelActionListener(Object[] accountFieldArray, int crudMode)
    {
        this.accountFieldArray = accountFieldArray;
        this.crudMode = crudMode;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(this.crudMode == 0)
        {
            this.createAccount();
        }
        else
        {

        }

        // Close frame

    }

    private void updateAccount()
    {

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
