package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;


public class SeriesPanel extends JPanel {

    public SeriesPanel()
    {
        this.setBackground(Color.gray);
        this.createComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * Create FilmsPanels components
     */
    private void createComponents()
    {

        // Create panels
        JPanel innerFlowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel innerBoxPanel = new JPanel();
        JPanel firstComponentPanel = new JPanel(new GridLayout(0, 1));
        JPanel thirdComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel forthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fifthComponentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel fillerPanel = new JPanel();


        // Set colors and titles
        innerFlowPanel.setBorder(BorderFactory.createTitledBorder("Series"));
        innerFlowPanel.setBackground(Color.gray);
        innerBoxPanel.setBackground(Color.gray);
        firstComponentPanel.setBackground(Color.gray);
        secondComponentPanel.setBackground(Color.gray);
        thirdComponentPanel.setBackground(Color.gray);
        forthComponentPanel.setBackground(Color.gray);


        // Set options
        innerBoxPanel.setLayout(new BoxLayout(innerBoxPanel, BoxLayout.Y_AXIS));
        innerFlowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //create table and fill with data
        Object[] filmData = this.returnFilmData();
        String[] columns = (String[])filmData[1];
        Object[][] data = (Object[][])filmData[0];
        JTable filmsTable = new JTable(data, columns);

        JScrollPane tablePane = new JScrollPane(filmsTable);
        tablePane.setPreferredSize(new Dimension(500, 130));
        tablePane.setMaximumSize(new Dimension(500, 13-0));



        // Add components to first component panel
        firstComponentPanel.add(tablePane);

        // Add components to third component panel
        JLabel filterOptionsLabel = new JLabel("Filter options:");
        secondComponentPanel.add(filterOptionsLabel);

        // Add components to second component panel
        JLabel filterAccountsLabel = new JLabel("Bekeken door:");
        JButton filterAccountButton = new JButton("Filter");

        String[] accountFilterBoxOptions = {"Account", "Iedereen"};
        JComboBox<String> accountFilterBox = new JComboBox<>(accountFilterBoxOptions);

        thirdComponentPanel.add(filterAccountsLabel);
        thirdComponentPanel.add(accountFilterBox);
        thirdComponentPanel.add(filterAccountButton);

        // add components to forth component panel
        JLabel timeSpanLabel = new JLabel("Tijdsduur:");
        String[] timeSpanFilterBoxOptions = {"Geen selectie", "Kortst", "Langst"};
        JComboBox<String> timeSpanFilterBox = new JComboBox<>(timeSpanFilterBoxOptions);

        JLabel ageIndicationLabel = new JLabel("Leeftijdsindicatie: ");
        String[] ageIndicationFilterBoxOptions = {"Allemaal", "6", "9", "12", "16"};
        JComboBox<String> ageIndicationFilterBox = new JComboBox<>(ageIndicationFilterBoxOptions);

        JButton selectSerieButton = new JButton("Selecteer");

        forthComponentPanel.add(selectSerieButton);


        // JCHART
        JFreeChart barChart = ChartFactory.createBarChart(
                "Gemiddeld % bekeken van tijdsduur per aflevering",
                "Category",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new Dimension(900, 190));
        chartPanel.setMinimumSize(new Dimension(900, 190));

        fifthComponentPanel.add(chartPanel);

        innerBoxPanel.add(firstComponentPanel);
        innerBoxPanel.add(forthComponentPanel);

        // Create and add filler panel
        fillerPanel.add(Box.createRigidArea(new Dimension(0,15))); // Create space between buttons
        fillerPanel.setBackground(Color.gray);


        innerBoxPanel.add(fillerPanel);
        innerBoxPanel.add(fifthComponentPanel);
        innerBoxPanel.add(secondComponentPanel);
        innerBoxPanel.add(thirdComponentPanel);

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
     * Returns a string array of film data by selected film options
     *
     * @return
     */
    private Object[] returnFilmData(){

        //headers for the table
        String[] columns = new String[] {
                "Titel", "Genre", "Taal", "Leeftijdsincdicatie", "Aantal afleveringen"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },
                {1, "John", 40.0, false, "3" },

        };

        Object[] tableData = {data, columns};

        return tableData;

    }

    private CategoryDataset createDataset( ) {

        // Gebruiker of profiel
        final String gebruiker = "Gebruiker";

        // Episode name
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String aa = "asd";
        final String bb = "c";
        final String cc = "iasdo";
        final String dd = "asdasdas";
        final String ee = "asdas";
        final String ff = "asdasdi";
        final String gg = "asu0das";


        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        dataset.addValue( 1.0 , gebruiker , speed );
        dataset.addValue( 3.0 , gebruiker , userrating );
        dataset.addValue( 5.0 , gebruiker , ff );
        dataset.addValue( 5.0 , gebruiker , aa );

        dataset.addValue( 5.0 , gebruiker , speed );
        dataset.addValue( 6.0 , gebruiker , ee );
        dataset.addValue( 10.0 , gebruiker , gg );
        dataset.addValue( 4.0 , gebruiker , bb );

        dataset.addValue( 2.0 , gebruiker , dd );
        dataset.addValue( 3.0 , gebruiker , millage );
        dataset.addValue( 6.0 , gebruiker , cc );

        return dataset;
    }


}
