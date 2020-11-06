package org.example.ownmultithreadingproject.model;

public class FileOnWeb {
    private String url;
    private String filename;

    public FileOnWeb(String url, String filename) {
        this.url = url;
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public String getFilename() {
        return filename;
    }
}
