package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AccountsPanel extends JPanel {

    private JComboBox<String> selectProfileBox;

    public AccountsPanel(JComboBox<String> selectProfileBox)
    {
        // Add for reference to profilePanel for actionListener
        this.selectProfileBox = selectProfileBox;

        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("accountsPanel");
    }

    /**
     * Create AccountsPanel components
     */
    private void createComponents()
    {

        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        JPanel firstComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fillerPanel = new JPanel();


        // Set colors
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Account"));
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);


        JLabel selectProfileLabel = new JLabel("Selecteer account: ");

        JComboBox<String> selectAccountBox = new JComboBox<>(this.returnAccountNames().toArray(new String[0]));
        selectAccountBox.setMinimumSize(new Dimension(230, 25));
        selectAccountBox.setPreferredSize(new Dimension(230, 25));

        JButton selectProfileButton = new JButton("Selecteer");
        selectProfileButton.addActionListener(new AccountsPanelSelectActionListener(this.selectProfileBox, selectAccountBox));

        firstComponentPanel.add(selectProfileLabel);
        firstComponentPanel.add(selectAccountBox);
        firstComponentPanel.add(selectProfileButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel filterAccountsLabel = new JLabel("Filter accounts");
        JLabel filterAccountsLabelBasedOn = new JLabel("aantal profielen in account");
        JTextField filterProfileAmountTextField = new JTextField();
        filterProfileAmountTextField.setPreferredSize(new Dimension(70, 25));
        JButton filterAccountButton = new JButton("Filter");
        String[] accountFilterBoxOptions = {"Gelijk aan", "Kleiner dan", "Groter dan"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);

        filterAccountButton.addActionListener(new AccountsPanelFilterActionListener(filterProfileAmountTextField, accountFilterBox, selectAccountBox));

        secondComponentPanel.add(filterAccountsLabel);
        secondComponentPanel.add(accountFilterBox);
        secondComponentPanel.add(filterAccountsLabelBasedOn);
        secondComponentPanel.add(filterProfileAmountTextField);
        secondComponentPanel.add(filterAccountButton);

        innerBoxPanel.add(firstComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,15))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

        innerBoxPanel.add(secondComponentPanel);
        innerFlowPanel.add(innerBoxPanel);
        this.add(innerFlowPanel);

        // Create space for panel alignment
        for(int x = 0; x < 10; x++)
        {
            fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    /**
     * Returns a list of account names of accounts by selected profile
     *
     * @return
     */
    public List<String> returnAccountNames() {
        List<String> accountList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Naam FROM Account");

            while (rs.next()) {
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
