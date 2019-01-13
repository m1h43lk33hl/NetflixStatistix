package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

public class AccountsPanel extends JPanel {

    public AccountsPanel()
    {
        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        JComboBox<String> selectProfileBox = new JComboBox<>(this.returnProfileNames());
        selectProfileBox.setMinimumSize(new Dimension(230, 25));
        selectProfileBox.setPreferredSize(new Dimension(230, 25));

        JButton selectProfileButton = new JButton("Selecteer");

        firstComponentPanel.add(selectProfileLabel);
        firstComponentPanel.add(selectProfileBox);
        firstComponentPanel.add(selectProfileButton);

        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel filterAccountsLabel = new JLabel("Filter accounts");
        JLabel filterAccountsLabelBasedOn = new JLabel("aantal profielen in account");
        JTextField filterProfileAmountTextField = new JTextField();
        filterProfileAmountTextField.setPreferredSize(new Dimension(70, 25));
        JButton filterAccountButton = new JButton("Filter");


        String[] accountFilterBoxOptions = {"Gelijk aan", "Groter dan", "Kleiner dan"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);

        secondComponentPanel.add(filterAccountsLabel);
        secondComponentPanel.add(accountFilterBox);
        secondComponentPanel.add(filterAccountsLabelBasedOn);
        secondComponentPanel.add(filterProfileAmountTextField);
        secondComponentPanel.add(filterAccountButton);

        innerBoxPanel.add(firstComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,30))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);
        innerBoxPanel.add(fillerPanel);

        innerBoxPanel.add(secondComponentPanel);
        innerFlowPanel.add(innerBoxPanel);
        this.add(innerFlowPanel);

        // Create space for panel alignment
        for(int x = 0; x < 3; x++)
        {

            fillerPanel = new JPanel();
            fillerPanel.setBackground(Color.gray);
            this.add(fillerPanel);
        }
    }

    /**
     * Returns a string array of names of accounts by selected profile
     *
     * @return
     */
    private String[] returnProfileNames(){
        String[] x = {"asjdashdaskjh", "asdjsaiodjasd", "ajdkadjsaljlsdj"};
        return x;

    }
}
