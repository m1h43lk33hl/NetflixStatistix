package com.company.applicationlogic.listeners;

import com.company.applicationlogic.errorutils.ErrorDialog;
import com.company.applicationlogic.errorutils.ErrorMessages;
import com.company.datastorage.Database;

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
    private JLabel selectedAccountName;

    /**
     * Class constructor
     *
     * @param filmsTable
     * @param movieSelectedLabel
     * @param watchByAmountLabel
     */
    public FilmsPanelSelectActionListener(JTable filmsTable, JLabel movieSelectedLabel, JLabel watchByAmountLabel, JLabel selectedAccountName)
    {
        this.filmsTable = filmsTable;
        this.movieSelectedLabel = movieSelectedLabel;
        this.watchByAmountLabel = watchByAmountLabel;
        this.selectedAccountName = selectedAccountName;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.selectedAccountName.getText().equals(""))
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ACCOUNT_NOT_SELECTED);
            return;
        }
        if(this.filmsTable.getSelectionModel().isSelectionEmpty())
        {
            ErrorDialog.showErrorDialog(ErrorMessages.ROW_NOT_SELECTED);
            return;
        }

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
            ResultSet resultSet = database.query("SELECT COUNT(FilmID) as 'viewerAmount' FROM Filmlog WHERE PercentageBekeken = 100 AND FilmID=" + FilmID + " GROUP BY FilmID;");

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
