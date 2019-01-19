package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

public class ProfilesPanelCrudActionListener implements ActionListener {

    private int crudMode = 0;

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    public ProfilesPanelCrudActionListener(int crudMode, JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.crudMode = crudMode;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        ProfilesCrudPanel profilesCrudPanel = new ProfilesCrudPanel(this.crudMode, this.selectProfileBox, this.selectedAccountName);

        InternalFrame internalFrame = new InternalFrame(profilesCrudPanel);
        profilesCrudPanel.setInternalFrame(internalFrame);

        internalFrame.run();
    }
}
