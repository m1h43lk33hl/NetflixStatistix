package com.company.applicationlogic;

import com.company.ErrorDialog;
import com.company.ErrorMessages;
import com.company.datastorage.Database;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LogsCrudPanelSaveActionListener handles save logic for LogsCrudPanelS
 */
public class LogsCrudPanelSaveActionListener implements ActionListener {

    public JTable table;
    private JComboBox<String> selectProfileBox;
    private JLabel selectedAccountName;

    /**
     * Class contstructor for LogsCrudPanel
     *
     * @param table
     * @param selectProfileBox
     * @param selectedAccountName
     */
    public LogsCrudPanelSaveActionListener(JTable table, JComboBox<String> selectProfileBox, JLabel selectedAccountName) {
        this.table = table;
        this.selectProfileBox = selectProfileBox;
        this.selectedAccountName = selectedAccountName;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (this.hasDuplicate()) {
            ErrorDialog.showErrorDialog(ErrorMessages.TABLE_HAS_DUPLICATES);
            return;
        }


        TableModel model = this.table.getModel();
        int columnCount = model.getColumnCount() - 1;

        for (int x = 0; x < table.getRowCount(); x++) {
            if (model.getValueAt(x, 1) == null) // ROW IS MOVIE
            {
                this.updateMovieLog(model.getValueAt(x, 0).toString(), model.getValueAt(x, 3).toString(), model.getValueAt(x, 4).toString());
            } else {
                this.updateEpisodeLog(model.getValueAt(x, 0).toString(), model.getValueAt(x, 1).toString(), model.getValueAt(x, 2).toString(), model.getValueAt(x, 4).toString());
            }
        }
    }

    /**
     * Updates episode log
     *
     * @param episodeLogID
     * @param Serie
     * @param episode
     * @param timeSpan
     */
    private void updateEpisodeLog(String episodeLogID, String Serie, String episode, String timeSpan) {

        String SQL = "UPDATE Afleveringlog SET AfleveringID = \n" +
                "(SELECT Aflevering.AfleveringID FROM Aflevering\n" +
                "INNER JOIN Seizoen ON Seizoen.SeizoenID = Aflevering.SezoenID\n" +
                "INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID\n" +
                "WHERE Serie.Titel='" + Serie + "' AND Aflevering.Titel = '" + episode + "')\n" +
                ", PercentageBekeken=" + timeSpan + " WHERE AfleveringlogID=" + episodeLogID + ";";

        try {
            Database database = Database.getInstance();
            database.queryDDL(SQL);
        } catch (Exception e) {

        }

    }

    /**
     * Updates movie log
     *
     * @param filmLogID
     * @param movie
     * @param timeSpan
     */
    private void updateMovieLog(String filmLogID, String movie, String timeSpan) {
        System.out.println(movie + "" + timeSpan);

        String SQL = "UPDATE Filmlog SET FilmID = (SELECT Film.FilmID FROM Film WHERE Film.Titel = '" + movie + "'), PercentageBekeken=" + timeSpan + " WHERE FilmlogID = " + filmLogID + ";";

        try {
            Database database = Database.getInstance();
            database.queryDDL(SQL);
        } catch (Exception e) {

        }
    }

    /**
     * Check if there exists duplicates in table
     * @return
     */
    public boolean hasDuplicate() {

        List<String> filmsList = new ArrayList<>();
        List<String> episodesList = new ArrayList<>();


        for (int x = 0; x < table.getRowCount(); x++) {
            if (table.getModel().getValueAt(x, 3) == null)
                continue;

            filmsList.add(table.getModel().getValueAt(x, 3).toString());
        }

        for (int x = 0; x < table.getRowCount(); x++) {
            if (table.getModel().getValueAt(x, 2) == null)
                continue;

            episodesList.add(table.getModel().getValueAt(x, 2).toString());
        }

        Set<String> appeared = new HashSet<>();
        Set<String> appeared2 = new HashSet<>();

        for (String item : filmsList) {
            if (!appeared.add(item)) {
                return true;
            }
        }

        for (String item : episodesList) {
            if (!appeared2.add(item)) {
                return true;
            }
        }

        return false;
    }

}