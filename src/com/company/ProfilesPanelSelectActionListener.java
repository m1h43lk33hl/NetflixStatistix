package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilesPanelSelectActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedProfileName;

    public ProfilesPanelSelectActionListener(JLabel selectedProfileName, JComboBox<String> selectProfileBox)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedProfileName = selectedProfileName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.selectedProfileName.setText(this.selectProfileBox.getSelectedItem().toString());
    }
}
