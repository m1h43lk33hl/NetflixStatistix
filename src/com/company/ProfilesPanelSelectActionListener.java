package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Class ProfilesPanelSelectActionListener handles the selection implementation for class ProfilesPanel
 */
public class ProfilesPanelSelectActionListener implements ActionListener {

    private JComboBox<String> selectProfileBox;
    private JLabel selectedProfileName;
    private JLabel recommendedLabel;
    private JLabel selectedAccountName;

    /**
     * Class contructor for ProfilesPanelSelectActionListener
     *
     * @param selectedProfileName
     * @param selectProfileBox
     * @param recommendedLabel
     * @param selectedAccountName
     */
    public ProfilesPanelSelectActionListener(JLabel selectedProfileName, JComboBox<String> selectProfileBox, JLabel recommendedLabel, JLabel selectedAccountName)
    {
        this.selectProfileBox = selectProfileBox;
        this.selectedProfileName = selectedProfileName;
        this.selectedAccountName = selectedAccountName;
        this.recommendedLabel = recommendedLabel;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.selectedProfileName.setText(this.selectProfileBox.getSelectedItem().toString());
        this.setRecommendedLabel();
    }

    /**
     * Sets the recommendedLabel's text according to the user's selected profile
     */
    private void setRecommendedLabel()
    {
        // Sort by Serie categorie limit 1;

        // Select series with similar genre from episodes from series which have a watch-time greater than 80% by selected profile
        String SQL1 = "SELECT Titel FROM Serie \n" +
                "WHERE Serie.Genre = \n" +
                "\t(SELECT TOP 1 Serie.Genre \n" +
                "\tFROM Programmalog \n" +
                "\tINNER JOIN Aflevering ON Aflevering.AfleveringID=Programmalog.AfleveringID \n" +
                "\tINNER JOIN Serie ON Aflevering.SerieID=Serie.SerieID \n" +
                "\tWHERE Programmalog.ProfielID IN \n" +
                "\t\t(SELECT Profiel.ProfielID \n" +
                "\t\tFROM Profiel INNER JOIN Account \n" +
                "\t\tON Account.Naam = Profiel.AccountNaam \n" +
                "\t\tWHERE Account.Naam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"') \n" +
                "\t\tAND Programmalog.PercentageBekeken > 80) \n" +
                "\tAND Serie.Titel != (SELECT Serie.Titel FROM Programmalog \n" +
                "\tINNER JOIN Aflevering ON Aflevering.AfleveringID=Programmalog.AfleveringID \n" +
                "\tINNER JOIN Serie ON Aflevering.SerieID=Serie.SerieID \n" +
                "\tWHERE Programmalog.ProfielID IN \n" +
                "\t\t(SELECT Profiel.ProfielID \n" +
                "\t\tFROM Profiel \n" +
                "\t\tINNER JOIN Account ON Account.Naam = Profiel.AccountNaam \n" +
                "\t\tWHERE Account.Naam='"+this.selectedAccountName.getText()+"') \n" +
                "\t\tAND Programmalog.PercentageBekeken > 80);";

        String SQL2 = "SELECT Serie.Titel \n" +
                "\tFROM Programmalog \n" +
                "\tINNER JOIN Aflevering ON Aflevering.AfleveringID=Programmalog.AfleveringID \n" +
                "\tINNER JOIN Serie ON Aflevering.SerieID=Serie.SerieID \n" +
                "\tWHERE Programmalog.ProfielID IN \n" +
                "\t\t(SELECT Profiel.ProfielID \n" +
                "\t\tFROM Profiel INNER JOIN Account \n" +
                "\t\tON Account.Naam = Profiel.AccountNaam \n" +
                "\t\tWHERE Account.Naam='"+this.selectedAccountName.getText()+"' AND Profiel.Naam='"+this.selectProfileBox.getSelectedItem().toString()+"') \n" +
                "\t\tAND Programmalog.PercentageBekeken > 80";

        System.out.println(SQL2);
        try {

            // First query
            ResultSet resultSet1 = Database.getInstance().query(SQL1);

            String serieToWatch = "";

            if (!resultSet1.next() ) {
                this.recommendedLabel.setText("Geen recommendanties gevonden voor "+this.selectProfileBox.getSelectedItem().toString()+".");
            } else {

                serieToWatch = resultSet1.getString("Titel");

                do {
                    //statement(s)
                } while(resultSet1.next());
            }



            // Second query
            ResultSet resultSet2 = Database.getInstance().query(SQL2);

            while(resultSet2.next())
            {
                this.recommendedLabel.setText("Omdat je naar "+resultSet2.getString("Titel")+" hebt gekeken, is de volgende serie wellicht ook interessant: "+ serieToWatch);
                break;
            }

        }
        catch (Exception e)
        {

        }
    }
}
