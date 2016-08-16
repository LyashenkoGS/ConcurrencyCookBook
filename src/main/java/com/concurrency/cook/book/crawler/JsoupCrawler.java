package com.concurrency.cook.book.crawler;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author lyashenkogs.
 */
public class JsoupCrawler {


    public static void parseConcurrently() throws ExecutionException, InterruptedException {
        List<String> topSites=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            topSites.add("youtube.com");
        }
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<CompletableFuture<Integer>> featuresList = topSites.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> parseSiteAndCalculateLength(e), executor))
                .collect(Collectors.toList());//created tasks list
        CompletableFuture<List<Integer>> result = CompletableFuture
                .allOf(featuresList.toArray(new CompletableFuture[featuresList.size()]))
                .thenApply(v -> featuresList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));//compose all in one task
        result.get();//
    }
    /**
     * Parse given sitePage page and calculate number of lines of the html code.
     * @param sitePage sitePage address to parse
     * @return parsed sitePage length
     */
    public static Integer parseSiteAndCalculateLength(String sitePage) {
        org.jsoup.nodes.Document document = null;
        try {
            document = Jsoup.connect("http://" + sitePage).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("parsed sitePage" + sitePage + " length:" + document.html().length());
        return document.html().length();
    }
}
