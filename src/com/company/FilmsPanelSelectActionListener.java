package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Class FilmsPanelSelectActionListener handles the selection implementation for class FilmsPanel
 */
public class FilmsPanelSelectActionListener implements ActionListener {

    private JTable filmsTable;
    private JLabel movieSelectedLabel;
    private JLabel watchByAmountLabel;


    /**
     * Class constructor
     *
     * @param filmsTable
     * @param movieSelectedLabel
     * @param watchByAmountLabel
     */
    public FilmsPanelSelectActionListener(JTable filmsTable, JLabel movieSelectedLabel, JLabel watchByAmountLabel)
    {
        this.filmsTable = filmsTable;
        this.movieSelectedLabel = movieSelectedLabel;
        this.watchByAmountLabel = watchByAmountLabel;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        int FilmID = Integer.parseInt(this.filmsTable.getModel().getValueAt(this.filmsTable.getSelectedRow(), 0).toString());
        this.watchByAmountLabel.setText(Integer.toString(this.returnMovieViewerAmount(FilmID)));
        this.movieSelectedLabel.setText(this.filmsTable.getModel().getValueAt(this.filmsTable.getSelectedRow(), 1).toString());
    }

    /**
     * Returns the amount of viewers that watched a particular movie
     *
     * @param FilmID
     * @return
     */
    private int returnMovieViewerAmount(int FilmID)
    {

        int movieViewerAmount = 0;

        try {
            Database database = Database.getInstance();
            ResultSet resultSet = database.query("SELECT COUNT(FilmID) as 'viewerAmount' FROM Programmalog WHERE PercentageBekeken = 100 AND FilmID=" + FilmID + " GROUP BY FilmID;");

            while (resultSet.next()) {
                movieViewerAmount = resultSet.getInt("viewerAmount");
            }

            return movieViewerAmount;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
