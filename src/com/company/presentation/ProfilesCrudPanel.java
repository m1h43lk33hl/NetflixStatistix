package com.company.presentation;

import com.company.applicationlogic.ProfilesCrudPanelActionListener;
import com.company.datastorage.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

/**
 * Class ProfilesPanel handles components within the profilespanel
 */
public class ProfilesCrudPanel extends JPanel {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;
    private int crudMode;
    private JTextField profileNameTextField = new JTextField();
    private JTextField profileAgeTextField = new JTextField();
    private InternalFrame internalFrame;

    public ProfilesCrudPanel(int crudMode, JComboBox<String> selectProfileBox, JLabel selectedAccountName)
    {
        this.crudMode = crudMode;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
        this.setBackground(Color.gray);
        // Create components is in the public setInternalFrame method to allow the pass of the reference
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("profilesPanel");
    }


    /**
     * Create AccountsPanel components
     */
    private void createComponents()
    {
        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        JPanel innerBoxPanel2 = new JPanel();
        JPanel recommendedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel firstComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel thirdComponentPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));


        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerBoxPanel2.setLayout(new BoxLayout(innerBoxPanel2, BoxLayout.Y_AXIS));

        JPanel fillerPanel = new JPanel();

        // Set borders
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Profiel"));

        // Set colors
        innerBoxPanel2.setBackground(Color.gray);
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        recommendedPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);

        JLabel profileNameLabel = new JLabel("Naam:");
        profileNameTextField.setPreferredSize(new Dimension(100, 25));

        firstComponentPanel.add(profileNameLabel);
        firstComponentPanel.add(profileNameTextField);

        JLabel profileAgeLabel = new JLabel("Leeftijd:");
        profileAgeTextField.setPreferredSize(new Dimension(100, 25));

        secondComponentPanel.add(profileAgeLabel);
        secondComponentPanel.add(profileAgeTextField);

        JButton profileCrudButton = new JButton();

        if(this.crudMode == 0)
        {
            profileCrudButton.setText("Maak aan");
        }
        else
        {
            this.fillFields();
            profileCrudButton.setText("Sla op");
        }

        profileCrudButton.addActionListener(new ProfilesCrudPanelActionListener(this.crudMode, this.selectProfileBox, this.selectedAccountName, profileNameTextField, profileAgeTextField, this.internalFrame));

        thirdComponentPanel.add(profileCrudButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(secondComponentPanel);
        innerBoxPanel.add(thirdComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

        innerBoxPanel2.add(recommendedPanel);

        innerFlowPanel.add(innerBoxPanel);

        // Keep FlowPanels aligned
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(innerFlowPanel);

    }

    /**
     * Returns the selectProfileBox for reference sake
     *
     * @return
     */
    public JComboBox<String> getSelectProfileBox()
    {
        return this.selectProfileBox;
    }

    private void fillFields()
    {
        try
        {
            Database database = Database.getInstance();
            String SQL = "SELECT Leeftijd FROM Profiel WHERE AccountNaam='"+this.selectedAccountName.getText()+"' AND Naam='"+this.selectProfileBox.getSelectedItem().toString()+"';";
            System.out.println(SQL);
            ResultSet resultSet = database.query(SQL);
            int age = 0;

            while(resultSet.next())
            {
                age = resultSet.getInt("Leeftijd");
            }

            this.profileNameTextField.setText(this.selectProfileBox.getSelectedItem().toString());
            this.profileAgeTextField.setText(Integer.toString(age));
        }
        catch (Exception e)
        {

        }
    }

    public void setInternalFrame(InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
        this.createComponents();
    }

}
