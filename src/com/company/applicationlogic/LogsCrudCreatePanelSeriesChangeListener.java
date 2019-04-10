package com.company.applicationlogic;

import com.company.datastorage.Database;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class LogsCrudCreatePanelSeriesChangeListener handles the listener for LogsCrudCreatePanel's series box
 */
public class LogsCrudCreatePanelSeriesChangeListener implements ItemListener {

    private JComboBox<String> selectEpisodeBox;

    public LogsCrudCreatePanelSeriesChangeListener(JComboBox<String> selectEpisodeBox)
    {
        this.selectEpisodeBox = selectEpisodeBox;
    }

    /**
     * {@inheritDoc}
     *
     * @param event
     */
    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object selectedSerie = event.getItem();

            if(selectedSerie.toString().equals(""))
            {
                String emptyArray[] = {};
                this.selectEpisodeBox.setModel(new DefaultComboBoxModel(emptyArray));
                return;
            }
            else
            {
                this.selectEpisodeBox.setModel(new DefaultComboBoxModel(this.returnEpisodeTitles(selectedSerie.toString()).toArray()));
            }
        }
    }

    /**
     * Returns a list of series titles of accounts by selected profile
     *
     * @return
     */
    private java.util.List<String> returnEpisodeTitles(String selectedSerie) {
        List<String> seriesList = new ArrayList<String>();

        try {
            Database db = Database.getInstance();
            ResultSet rs = db.query("SELECT Aflevering.Titel FROM Aflevering INNER JOIN Seizoen ON Aflevering.SezoenID = Seizoen.SeizoenID INNER JOIN Serie ON Serie.SerieID = Seizoen.SerieID WHERE Serie.Titel = '"+selectedSerie+"';");

            while (rs.next()) {
                seriesList.add(rs.getString("Titel"));
            }

            return seriesList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

}