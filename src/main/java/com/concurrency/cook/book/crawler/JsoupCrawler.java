package com.concurrency.cook.book.crawler;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * HTML crawler realisaton based on jsoup
 *
 * @author lyashenkogs.
 */
public class JsoupCrawler {
private static Logger LOG = LoggerFactory.getLogger(JsoupCrawler.class);
    public static void parseSiteNtimesConcurrently(String siteToParse, int timesToParse) throws ExecutionException, InterruptedException {
        List<String> topSites = new ArrayList<>();
        for (int i = 0; i < timesToParse; i++) {
            topSites.add(siteToParse);
        }
        ExecutorService executor = Executors.newFixedThreadPool(timesToParse);//TODO Should threads number be equal to timesToParse ?
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
     *
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
        LOG.info("parsed sitePage: " + sitePage + " length:" + document.html().length());
        return document.html().length();
    }

    public static void parseSiteNtimesSequentially(String siteToParse, int timesToParse){
        for (int j = 0; j < timesToParse; j++) {
            parseSiteAndCalculateLength(siteToParse);
        }
    }
}
