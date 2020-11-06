package org.example.ownmultithreadingproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloaderManager {
    public static void downloadViaUrl(String myUrl, String myFilename, Logger logger) throws IOException {
        URL url = new URL(myUrl);
        FileOutputStream fos = new FileOutputStream(myFilename);
        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        long fileSize = httpConnection.getContentLength();
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        Thread progressBarThread = new Thread(() -> {
            try {
                logger.addDownload(new DownLoad(myFilename, 0, fileSize, 0, 0, 0));
                long oldSize = 0;
                long newSize = 0;
                while (fos.getChannel().size() < fileSize) {
                    sendCurrentState(myFilename, fos, fileSize, oldSize, newSize, logger);
                    oldSize = fos.getChannel().size();
                    Thread.sleep(500);
                    newSize = fos.getChannel().size();
                }
                logger.setDownloaded(myFilename);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        progressBarThread.start();
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    private static void sendCurrentState(String myFilename, FileOutputStream fos, long fileSize, long oldSize, long newSize, Logger logger) throws IOException {
        double currentPercentage = Math.round(((float) fos.getChannel().size() / fileSize) * 100 * 100d) / 100d;
        float rate = (float) ((newSize - oldSize) / 1024 / 0.5);
        float remainingSize = (float) (fileSize - fos.getChannel().size());
        int remainingTime = (int) (remainingSize / (rate * 1024));
        long actualSize = fos.getChannel().size() / 1024 / 1024;
        logger.update(myFilename, actualSize, currentPercentage, rate, remainingTime);
    }

    public static void downloadFiles(List<FileOnWeb> filesToDownload, Logger logger) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (FileOnWeb download : filesToDownload) {
            executor.submit(() -> {
                try {
                    downloadViaUrl(download.getUrl(), download.getFilename(), logger);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
