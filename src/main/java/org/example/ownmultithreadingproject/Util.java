package org.example.ownmultithreadingproject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Util {
    public static HttpResponse<String> getApiData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest myRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
        try {
            return client.send(myRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}