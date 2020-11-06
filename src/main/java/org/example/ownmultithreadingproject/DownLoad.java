package org.example.ownmultithreadingproject;

public class DownLoad {
    private String filename;
    private long actualSize;
    private long fileSize;
    private double actualPercentage;
    private float rate;
    private int remainingTime;
    private boolean isDownloaded;

    public DownLoad(String filename, long actualSize, long fileSize, double actualPercentage, float rate, int remainingTime) {
        this.filename = filename;
        this.actualSize = actualSize;
        this.fileSize = fileSize / 1024 / 1024;
        this.actualPercentage = actualPercentage;
        this.rate = rate;
        this.remainingTime = remainingTime;
        this.isDownloaded = false;
    }

    public String getFilename() {
        return filename;
    }

    public long getActualSize() {
        return actualSize;
    }

    public long getFileSize() {
        return fileSize;
    }

    public double getActualPercentage() {
        return actualPercentage;
    }

    public float getRate() {
        return rate;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setActualSize(long actualSize) {
        this.actualSize = actualSize;
    }

    public void setActualPercentage(double actualPercentage) {
        this.actualPercentage = actualPercentage;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }
}
