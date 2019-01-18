package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

public class AccountsPanelCrudActionListener implements ActionListener {

    private int crudMode = 0;
    private  Object[] accountLabelArray;
    private JComboBox<String> selectAccountBox;

    public AccountsPanelCrudActionListener(int crudMode)
    {
        this.crudMode = crudMode;
    }

    public AccountsPanelCrudActionListener(int crudMode, Object[] accountLabelArray, JComboBox<String> selectAccountBox)
    {
        this.crudMode = crudMode;
        this.accountLabelArray = accountLabelArray;
        this.selectAccountBox = selectAccountBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.crudMode == 0)
        {
            InternalFrame f = new InternalFrame(new AccountsCrudPanel(0));
            f.run();
        }
        else
        {
            InternalFrame f = new InternalFrame(new AccountsCrudPanel(1, this.accountLabelArray, this.selectAccountBox));
            f.run();
        }
    }
}
