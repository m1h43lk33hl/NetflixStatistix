package com.company.applicationlogic.listeners;

import com.company.datastorage.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LogsCrudPanelDeleteActionListener handles delete logic for LogsCrudPanel
 */
public class LogsCrudPanelDeleteActionListener implements ActionListener
{
    private JTable table;

    /**
     * Class constructor for LogsCrudPanelDeleteActionListener
     *
     * @param table
     */
    public LogsCrudPanelDeleteActionListener(JTable table)
    {
        this.table = table;
    }

    /**
     * {@inheritDoc}
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.deleteLog();
    }

    /**
     * Deletes log
     */
    private void deleteLog()
    {

        TableModel model = this.table.getModel();

        String SQL = "";

        if(model.getValueAt(this.table.getSelectedRow(), 1) == null) // ROW IS MOVIE
        {
            SQL = "DELETE FROM Filmlog WHERE FilmlogID = "+Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        }
        else
        {
            SQL = "DELETE FROM Afleveringlog WHERE AfleveringlogID = "+Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        }


        try
        {
            Database database = Database.getInstance();
            database.queryDDL(SQL);

            // Update table
            ((DefaultTableModel)this.table.getModel()).removeRow(this.table.getSelectedRow());

        }catch (Exception e)
        {

        }
    }
}
