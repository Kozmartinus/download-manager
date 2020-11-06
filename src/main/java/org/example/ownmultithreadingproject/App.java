package org.example.ownmultithreadingproject;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

//        DownloaderManager.downloadViaUrl("http://www.fsn.hu/testfiles/1GiB", "1GiB");
        DownloaderManager.downloadViaUrl("https://www.learningcontainer.com/wp-content/uploads/2020/02/Sample-FLAC-File.flac", "large-music.flac");
        DownloaderManager.downloadViaUrl("https://dlcdnets.asus.com/pub/ASUS/nb/DriversForWin10/VGA/Graphic_IGCC_DCH_Intel_F_V27.20.100.8190_18337.exe", "driver.exe");

    }
}
