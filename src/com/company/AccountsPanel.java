package com.company;

import javax.swing.*;
import java.awt.*;

public class AccountsPanel extends JPanel {

    public AccountsPanel() {

        this.setBackground(Color.gray);
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);

        JComboBox<String> accountBox = new JComboBox<>(this.loadAccountNames());
        JButton loadAccountButton = new JButton();
        JLabel selectAccountLabel = new JLabel("Selecteer account:");

        loadAccountButton.setMaximumSize(new Dimension(100, 25));
        loadAccountButton.setPreferredSize(new Dimension(100, 25));

        loadAccountButton.setText("Laad");

        c.gridy = 0;
        this.add(selectAccountLabel, c);

        c.gridx = 0;
        c.gridy++;
        this.add(accountBox, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.fill = GridBagConstraints.NONE;
        this.add(loadAccountButton, c);

        // Filler
        int mgridy = c.gridy + 1;

        for (int x = mgridy; x < (mgridy + 1); x++) {
            JLabel filler = new JLabel("");
            c.gridx = 0;
            c.gridy = x;
            this.add(filler, c);
        }

        JLabel requestAccountLabel = new JLabel("Filter account selectie:");
        c.gridy++;
        c.gridx = 0;
        this.add(requestAccountLabel, c);

        JLabel requestAccountLabel1 = new JLabel("Filter accounts ");
        String[] requestAccountComparisonBoxOptions = {"gelijk aan", "groter dan", "kleiner dan"};
        JLabel requestAccountLabel2 = new JLabel("aantal profielen in account:");
        JComboBox<String> requestAccountComparisonBox = new JComboBox<String>(requestAccountComparisonBoxOptions);
        JTextField requestAccountTextfield = new JTextField("Jemoder");
        requestAccountTextfield.setPreferredSize(new Dimension(130, 30));
        requestAccountTextfield.setMinimumSize(requestAccountTextfield.getPreferredSize()); // Bug fix

        c.gridy++;
        c.gridx = 0;
        this.add(requestAccountLabel1, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.NORTH;
        System.out.println(c.gridy);
        c.gridx = 1;
        this.add(requestAccountComparisonBox, c);
        c.gridx = 2;
        this.add(requestAccountLabel2, c);
        c.gridx = 3;
        this.add(requestAccountTextfield, c);

        c.gridx = 0;

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(Color.gray);


        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        requestAccountLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        p.add(requestAccountLabel1);
        p.add(requestAccountComparisonBox);
        p.add(requestAccountLabel2);
        p.add(requestAccountTextfield);
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        this.add(p, c);

        JButton filterAccountButton = new JButton("Filter");
        filterAccountButton.setMaximumSize(new Dimension(100, 25));
        filterAccountButton.setPreferredSize(new Dimension(100, 25));
        c.gridy++;
        c.gridx = 0;

        c.fill = GridBagConstraints.NONE;
        this.add(filterAccountButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;


        // Fillers
        mgridy = c.gridy + 1;

        for (int x = mgridy; x < (mgridy + 15); x++) {
            JLabel filler = new JLabel("");
            c.gridx = 3;
            c.gridy = x;
            this.add(filler, c);
        }

    }


    /**
     * Returns list of accounts
     *
     * @return
     */
    private String[] loadAccountNames() {
        String[] s = {"aaaaaaaa", "bcccccccc"};

        return s;

    }
}