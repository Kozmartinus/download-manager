package org.example.ownmultithreadingproject;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<DownLoad> downloads = new ArrayList<>();

    public synchronized void addDownload(DownLoad downLoad) {
        downloads.add(downLoad);
        printCurrentState();
    }

    public synchronized void update(String myFilename, long actualSize, double actualPercentage, float rate, int remainingTime) {
        for (DownLoad download : downloads) {
            if (download.getFilename().equals(myFilename)) {
                download.setActualSize(actualSize);
                download.setActualPercentage(actualPercentage);
                download.setRate(rate);
                download.setRemainingTime(remainingTime);
            }
        }
        printCurrentState();
    }

    private synchronized void printCurrentState() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (DownLoad download : downloads) {
            if (!download.isDownloaded()) {
                System.out.println(download.getFilename() + ":  " + download.getActualSize() + "/" + download.getFileSize()
                        + " MB   " + download.getActualPercentage() + " %   " + download.getRate() + " KB/s   "
                        + download.getRemainingTime() + " remaining sec");
            } else {
                System.out.println(download.getFilename() + " has been downloaded.");
            }
        }
    }

    public synchronized void setDownloaded(String myFilename) {
        for (DownLoad download : downloads) {
            if (download.getFilename().equals(myFilename)) {
                download.setDownloaded(true);
            }
        }
        printCurrentState();
    }
}
