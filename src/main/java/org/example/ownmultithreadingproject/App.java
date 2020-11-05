package org.example.ownmultithreadingproject;

import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Util.getApiData("https://www.anapioficeandfire.com/api/characters");
        Util.getApiData("https://www.breakingbadapi.com/api/characters");
        Util.getApiData("https://www.swapi.tech/api/people");
        long end = System.currentTimeMillis();
        System.out.println("Time without multithreading: " + (end - start) + " ms");


        long start1 = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            Util.getApiData("https://www.anapioficeandfire.com/api/characters");
        });
        executorService.submit(() -> {
            Util.getApiData("https://www.breakingbadapi.com/api/characters");
        });
        executorService.submit(() -> {
            Util.getApiData("https://www.swapi.tech/api/people");
        });

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        long end1 = System.currentTimeMillis();
        System.out.println("Time with multithreading: " + (end1 - start1) + " ms");
    }
}
