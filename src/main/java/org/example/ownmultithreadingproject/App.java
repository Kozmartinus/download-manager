package org.example.ownmultithreadingproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static Logger logger = new Logger();

    private static List<FileOnWeb> filesToDownload = new ArrayList<>(){{
        add(new FileOnWeb("https://www.learningcontainer.com/wp-content/uploads/2020/02/Sample-FLAC-File.flac", "large-music.flac"));
        add(new FileOnWeb("https://dlcdnets.asus.com/pub/ASUS/nb/DriversForWin10/VGA/Graphic_IGCC_DCH_Intel_F_V27.20.100.8190_18337.exe", "driver.exe"));
        add(new FileOnWeb("http://www.fsn.hu/testfiles/1GiB", "1GiB"));
        add(new FileOnWeb("https://www.learningcontainer.com/wp-content/uploads/2020/07/Sample-Image-file-Download.jpg", "image.jpg"));
        add(new FileOnWeb("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "newMP4.mp4"));
        add(new FileOnWeb("https://www.learningcontainer.com/wp-content/uploads/2020/09/Sample-gif-Image-File-Download-for-testing.gif", "littleGif.gif"));
    }};

    public static void main(String[] args) throws IOException {
        DownloaderManager.downloadFiles(filesToDownload, logger);
    }
}
