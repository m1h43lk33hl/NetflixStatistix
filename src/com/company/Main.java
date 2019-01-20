package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Main is the entrypoint class for the NetflixStatistix application
 */
public class Main {

    /**
     * Entry point for the NetflixStatistix application
     *
     * @param args
     */
    public static void main(String[] args) {
        NetflixStatistixUI UI = new NetflixStatistixUI();
        UI.run();
    }
}