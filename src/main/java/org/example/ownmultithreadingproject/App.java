package org.example.ownmultithreadingproject;

import java.net.http.HttpResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static int counter = 0;
    private static CountDownLatch latch = new CountDownLatch(3);;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                Util.getApiData("https://www.anapioficeandfire.com/api/characters");
                Util.getApiData("https://www.breakingbadapi.com/api/characters");
                Util.getApiData("https://www.swapi.tech/api/people");
                long end = System.currentTimeMillis();
                System.out.println("\rTime without multithreading: " + (end - start) + " ms");
            }
        });

        thread.start();
        while (!thread.getState().equals(Thread.State.TERMINATED)) {
            switch (counter%3) {
                case 0: System.out.print("\rLoading."); break;
                case 1: System.out.print("\rLoading.."); break;
                case 2: System.out.print("\rLoading..."); break;
            }
            counter++;
            Thread.sleep(500);
        }
        counter = 0;




        long start1 = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            Util.getApiData("https://www.anapioficeandfire.com/api/characters");
            latch.countDown();
        });
        executorService.submit(() -> {
            Util.getApiData("https://www.breakingbadapi.com/api/characters");
            latch.countDown();
        });
        executorService.submit(() -> {
            Util.getApiData("https://www.swapi.tech/api/people");
            latch.countDown();
        });

        executorService.shutdown();

        while (latch.getCount() != 0) {
            switch (counter%3) {
                case 0: System.out.print("\rLoading."); break;
                case 1: System.out.print("\rLoading.."); break;
                case 2: System.out.print("\rLoading..."); break;
            }
            counter++;
            Thread.sleep(50);
        }

//        executorService.awaitTermination(5, TimeUnit.SECONDS);
        long end1 = System.currentTimeMillis();
        System.out.println("\rTime with multithreading: " + (end1 - start1) + " ms");
    }
}
