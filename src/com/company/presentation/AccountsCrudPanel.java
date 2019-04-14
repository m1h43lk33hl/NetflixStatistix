package com.company.presentation;

import com.company.applicationlogic.listeners.AccountsCrudPanelActionListener;
import com.company.applicationlogic.listeners.AccountsPanelCrudActionListener;
import com.company.datastorage.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountsPanel handles components within the accountspanel
 */
public class AccountsCrudPanel extends JPanel {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountNameSeries;
    private JLabel selectedAccountNameFilm;
    private JButton selectAccountButton;

    private int crudMode = 0; // 0 = Create, 1=Update (edit)
    private Object[] accountLabelArray;
    private JComboBox<String> selectAccountBox;
    private InternalFrame internalFrame;


    /**
     * Class constructor for AccountsPanel
     *
     */
    public AccountsCrudPanel(int crudMode, JButton selectAccountButton, JComboBox<String> selectAccountBox )
    {
        // Set crudMode
        this.crudMode = crudMode;
        this.selectAccountButton = selectAccountButton;
        this.selectAccountBox = selectAccountBox;

        this.setBackground(Color.gray);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("Nieuw account");
    }

    public AccountsCrudPanel(int crudMode, JButton selectAccountButton ,Object[] accountLabelArray, JComboBox<String> selectAccountBox )
    {
        // Set crudMode
        this.crudMode = crudMode;
        this.selectAccountButton = selectAccountButton;
        this.accountLabelArray = accountLabelArray;
        this.selectAccountBox = selectAccountBox;

        this.setBackground(Color.gray);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setName("Account bewerken");
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

        JPanel fillerPanel = new JPanel();

        // Account panels
        JPanel accountNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountStreetNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountHouseNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel accountResidencePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Second panel
        JPanel someComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerFlowPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel2 = new JPanel();
        innerBoxPanel2.setLayout(new BoxLayout(innerBoxPanel2, BoxLayout.Y_AXIS));

        // Set borders
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Account"));

        if(this.crudMode == 0)
        {
            innerFlowPanel2.setBorder(BorderFactory.createTitledBorder("Nieuw account"));
        }
        else
        {
            innerFlowPanel2.setBorder(BorderFactory.createTitledBorder("Account bewerken"));
        }

        // Set colors
        innerBoxPanel2.setBackground(Color.gray);
        innerFlowPanel2.setBackground(Color.gray);
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        accountNamePanel.setBackground(Color.gray);
        accountStreetNamePanel.setBackground(Color.gray);
        accountHouseNumberPanel.setBackground(Color.gray);
        accountResidencePanel.setBackground(Color.gray);
        innerButtonPanel.setBackground(Color.gray);


        // Second ___
        JLabel accountNameLabel = new JLabel("Account Naam:");
        JTextField accountNameTextField = new JTextField("");
        accountNameTextField.setPreferredSize(new Dimension(100, 25));

        JLabel accountStreetNameLabel = new JLabel("Straatnaam:");
        JTextField accountStreetNameTextField = new JTextField("");
        accountStreetNameTextField.setPreferredSize(new Dimension(100, 25));

        JLabel accountHouseNumberLabel = new JLabel("Huisnummer:");
        JTextField accountHouseNumberTextField = new JTextField("");
        accountHouseNumberTextField.setPreferredSize(new Dimension(100, 25));

        JLabel accountHouseNumberExtraLabel = new JLabel("Toevoeging:");
        JTextField accountHouseNumberExtraTextField = new JTextField("");
        accountHouseNumberExtraTextField.setPreferredSize(new Dimension(100, 25));

        JLabel accountResidenceLabel = new JLabel("Woonplaats:");
        JTextField accountResidenceTextField = new JTextField("");
        accountResidenceTextField.setPreferredSize(new Dimension(100, 25));

        if(this.crudMode == 1)
        {
            accountNameTextField.setText( ((JLabel)this.accountLabelArray[0]).getText() );
            accountStreetNameTextField.setText( ((JLabel)this.accountLabelArray[1]).getText() );
            accountHouseNumberTextField.setText( ((JLabel)this.accountLabelArray[2]).getText() );
            accountHouseNumberExtraTextField.setText( ((JLabel)this.accountLabelArray[3]).getText() );
            accountResidenceTextField.setText( ((JLabel)this.accountLabelArray[4]).getText() );
        }

        Object[] accountFieldArray = new Object[5];
        accountFieldArray[0] = accountNameTextField;
        accountFieldArray[1] = accountStreetNameTextField;
        accountFieldArray[2] = accountHouseNumberTextField;
        accountFieldArray[3] = accountHouseNumberExtraTextField;
        accountFieldArray[4] = accountResidenceTextField;


        JButton createProfileButton = new JButton("Nieuw");

        createProfileButton.addActionListener(new AccountsPanelCrudActionListener(0, selectAccountButton, selectAccountBox));

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel filterAccountsLabel = new JLabel("Filter accounts");
        JLabel filterAccountsLabelBasedOn = new JLabel("aantal profielen in account");
        JTextField filterProfileAmountTextField = new JTextField();
        filterProfileAmountTextField.setPreferredSize(new Dimension(70, 25));
        JButton filterAccountButton = new JButton("Filter");
        String[] accountFilterBoxOptions = {"Gelijk aan", "Kleiner dan", "Groter dan"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);

        JLabel filterOptionsLabel = new JLabel("Filter opties:");

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,15))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);


        accountNamePanel.add(accountNameLabel);
        accountNamePanel.add(accountNameTextField);

        accountStreetNamePanel.add(accountStreetNameLabel);
        accountStreetNamePanel.add(accountStreetNameTextField);

        accountHouseNumberPanel.add(accountHouseNumberLabel);
        accountHouseNumberPanel.add(accountHouseNumberTextField);
        accountHouseNumberPanel.add(Box.createRigidArea(new Dimension(20,0)));

        accountHouseNumberPanel.add(accountHouseNumberExtraLabel);
        accountHouseNumberPanel.add(accountHouseNumberExtraTextField);

        accountResidencePanel.add(accountResidenceLabel);
        accountResidencePanel.add(accountResidenceTextField);

        innerBoxPanel2.add(Box.createRigidArea(new Dimension(0,15)));
        innerBoxPanel2.add(accountNamePanel);
        innerBoxPanel2.add(accountStreetNamePanel);
        innerBoxPanel2.add(accountHouseNumberPanel);
        innerBoxPanel2.add(accountResidencePanel);

        JButton crudButton = new JButton();

        if(this.crudMode == 0)
        {
            crudButton.setText("Voeg toe");
        }
        else
        {
            crudButton.setText("Sla op");
        }

        crudButton.addActionListener(new AccountsCrudPanelActionListener(accountFieldArray, this.selectAccountButton, this.crudMode, this.selectAccountBox, this.internalFrame));

        innerButtonPanel.add(crudButton);
        innerBoxPanel2.add(innerButtonPanel);

        innerFlowPanel.add(innerBoxPanel);
        innerFlowPanel2.add(innerBoxPanel2);

        // Keep FlowPanels aligned
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerFlowPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(innerFlowPanel2);

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

    /**
     * Sets internal frame
     *
     * @param internalFrame
     */
    public void setInternalFrame(InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
        this.createComponents();
    }
}
