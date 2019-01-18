package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

public class AccountsPanelCrudActionListener implements ActionListener {

    private int crudMode = 0;
    private  Object[] accountLabelArray;

    public AccountsPanelCrudActionListener(int crudMode)
    {
        this.crudMode = crudMode;
    }

    public AccountsPanelCrudActionListener(int crudMode, Object[] accountLabelArray)
    {
        this.crudMode = crudMode;
        this.accountLabelArray = accountLabelArray;


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println(((JLabel)this.accountLabelArray[0]).getText());

        if(this.crudMode == 0)
        {
            InternalFrame f = new InternalFrame(new AccountsCrudPanel(0));
            f.run();
        }
        else
        {
            InternalFrame f = new InternalFrame(new AccountsCrudPanel(1, this.accountLabelArray));
            f.run();
        }
    }
}
