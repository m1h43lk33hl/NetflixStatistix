package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountsPanel handles components within the accountspanel
 */
public class AccountsPanel extends JPanel {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountNameSeries;
    private JLabel selectedAccountNameFilm;

    // Internal frame
    private InternalFrame internalFrame;

    /**
     * Class constructor for AccountsPanel
     *
     * @param selectProfileBox
     * @param selectedAccountNameSeries
     * @param selectedAccountNameFilm
     */
    public AccountsPanel(JComboBox<String> selectProfileBox, JLabel selectedAccountNameSeries, JLabel selectedAccountNameFilm, InternalFrame internalFrame)
    {
        this.internalFrame = internalFrame;

        // Add for reference to profilePanel for actionListener
        this.selectProfileBox = selectProfileBox;

        // Add for reference to series table
        this.selectedAccountNameSeries = selectedAccountNameSeries;
        this.selectedAccountNameFilm = selectedAccountNameFilm;

        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        JPanel thirdComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        JPanel fillerPanel = new JPanel();

        //
        JPanel accountNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountStreetNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountHouseNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountResidencePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        // Second panel
        JPanel someComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerFlowPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel2 = new JPanel();
        innerBoxPanel2.setLayout(new BoxLayout(innerBoxPanel2, BoxLayout.Y_AXIS));
        JPanel accountButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));



        // Set borders
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Account"));
        innerFlowPanel2.setBorder(BorderFactory.createTitledBorder("Account gegevens"));

        // Set colors
        innerBoxPanel2.setBackground(Color.gray);
        innerFlowPanel2.setBackground(Color.gray);
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        accountNamePanel.setBackground(Color.gray);
        accountStreetNamePanel.setBackground(Color.gray);
        accountHouseNumberPanel.setBackground(Color.gray);
        accountResidencePanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);
        accountButtonsPanel.setBackground(Color.gray);

        // Second ___
        JLabel accountNameLabel = new JLabel("Account Naam:");
        JLabel accountNameSelectLabel  = new JLabel("<Geen gegevens>");
        accountNameSelectLabel.setForeground(Color.white);

        JLabel accountStreetNameLabel = new JLabel("Straatnaam:");
        JLabel accountStreetNameSelectLabel = new JLabel("<Geen gegevens>");
        accountStreetNameSelectLabel.setForeground(Color.white);

        JLabel accountHouseNumberLabel = new JLabel("Huisnummer:");
        JLabel accountHouseNumberSelectLabel = new JLabel("<Geen gegevens>");
        accountHouseNumberSelectLabel.setForeground(Color.white);

        JLabel accountHouseNumberExtraLabel = new JLabel("Toevoeging:");
        JLabel accountHouseNumberExtraSelectLabel = new JLabel("<Geen gegevens>");
        accountHouseNumberExtraSelectLabel.setForeground(Color.white);

        JLabel accountResidenceLabel = new JLabel("Woonplaats:");
        JLabel accountResidenceSelectLabel = new JLabel("<Geen gegevens>");
        accountResidenceSelectLabel.setForeground(Color.white);

        Object[] accountLabelArray = new Object[5];
        accountLabelArray[0] = accountNameSelectLabel;
        accountLabelArray[1] = accountStreetNameSelectLabel;
        accountLabelArray[2] = accountHouseNumberSelectLabel;
        accountLabelArray[3] = accountHouseNumberExtraSelectLabel;
        accountLabelArray[4] = accountResidenceSelectLabel;

        JLabel selectProfileLabel = new JLabel("Selecteer account: ");

        JComboBox<String> selectAccountBox = new JComboBox<>(this.returnAccountNames().toArray(new String[0]));
        selectAccountBox.setMinimumSize(new Dimension(230, 25));
        selectAccountBox.setPreferredSize(new Dimension(230, 25));

        JButton selectAccountButton = new JButton("Selecteer");
        selectAccountButton.addActionListener(new AccountsPanelSelectActionListener(this.selectProfileBox, selectAccountBox, this.selectedAccountNameSeries, this.selectedAccountNameFilm, accountLabelArray));

        JButton createProfileButton = new JButton("Nieuw");
        createProfileButton.addActionListener(new AccountsPanelCrudActionListener(0, selectAccountButton, selectAccountBox));

        firstComponentPanel.add(selectProfileLabel);
        firstComponentPanel.add(selectAccountBox);
        firstComponentPanel.add(selectAccountButton);
        firstComponentPanel.add(createProfileButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel filterAccountsLabel = new JLabel("Filter accounts");
        JLabel filterAccountsLabelBasedOn = new JLabel("aantal profielen in account");
        JTextField filterProfileAmountTextField = new JTextField();
        filterProfileAmountTextField.setPreferredSize(new Dimension(70, 25));
        JButton filterAccountButton = new JButton("Filter");
        String[] accountFilterBoxOptions = {"Gelijk aan", "Kleiner dan", "Groter dan"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);

        filterAccountButton.addActionListener(new AccountsPanelFilterActionListener(filterProfileAmountTextField, accountFilterBox, selectAccountBox));

        JLabel filterOptionsLabel = new JLabel("Filter opties:");
        thirdComponentPanel.add(filterOptionsLabel);

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

        innerBoxPanel.add(thirdComponentPanel);
        innerBoxPanel.add(secondComponentPanel);

        accountNamePanel.add(accountNameLabel);
        accountNamePanel.add(accountNameSelectLabel);

        accountStreetNamePanel.add(accountStreetNameLabel);
        accountStreetNamePanel.add(accountStreetNameSelectLabel);

        accountHouseNumberPanel.add(accountHouseNumberLabel);
        accountHouseNumberPanel.add(accountHouseNumberSelectLabel);
        accountHouseNumberPanel.add(Box.createRigidArea(new Dimension(20,0)));

        accountHouseNumberPanel.add(accountHouseNumberExtraLabel);
        accountHouseNumberPanel.add(accountHouseNumberExtraSelectLabel);

        accountResidencePanel.add(accountResidenceLabel);
        accountResidencePanel.add(accountResidenceSelectLabel);

        JButton editAccountButton = new JButton("Bewerk");
        editAccountButton.addActionListener(new AccountsPanelCrudActionListener(1, selectAccountButton, accountLabelArray, selectAccountBox));

        JButton deleteAccountButton = new JButton("Verwijder");
        deleteAccountButton.addActionListener(new AccountsPanelDeleteActionListener(selectAccountBox, accountNameSelectLabel));

        accountButtonsPanel.add(editAccountButton);
        accountButtonsPanel.add(deleteAccountButton);

        innerBoxPanel2.add(Box.createRigidArea(new Dimension(0,15)));
        innerBoxPanel2.add(accountNamePanel);
        innerBoxPanel2.add(accountStreetNamePanel);
        innerBoxPanel2.add(accountHouseNumberPanel);
        innerBoxPanel2.add(accountResidencePanel);
        innerBoxPanel2.add(accountButtonsPanel);

        innerFlowPanel.add(innerBoxPanel);
        innerFlowPanel2.add(innerBoxPanel2);

        // Keep FlowPanels aligned
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerFlowPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(innerFlowPanel);
        this.add(innerFlowPanel2);

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
    private List<String> returnAccountNames() {
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
