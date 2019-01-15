package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        NetflixStatistixUI UI = new NetflixStatistixUI();
        UI.run();
    }
}