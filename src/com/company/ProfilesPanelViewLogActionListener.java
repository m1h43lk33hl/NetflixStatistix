package com.company;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.lang.reflect.Array;
        import java.util.List;

public class ProfilesPanelViewLogActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    public ProfilesPanelViewLogActionListener(JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        LogsCrudPanel logsCrudPanel = new LogsCrudPanel();
//        ProfilesCrudPanel profilesCrudPanel = new ProfilesCrudPanel(this.crudMode, this.selectProfileBox, this.selectedAccountName);

        InternalFrame internalFrame = new InternalFrame(logsCrudPanel);
        logsCrudPanel.setInternalFrame(internalFrame);

        internalFrame.run();
    }
}
