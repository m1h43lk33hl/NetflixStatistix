package com.company;

import com.company.presentation.NetflixStatistixUI;

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