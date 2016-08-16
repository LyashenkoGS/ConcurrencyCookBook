package com.concurrency.cook.book;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.concurrent.ExecutionException;

import static com.concurrency.cook.book.crawler.JsoupCrawler.parseConcurrently;
import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteAndCalculateLength;

/**
 * @author lyashenkogs.
 */
public class CrawlerBenchmark {

    @Benchmark
    public void crawleSequentialBenchmark() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 8; i++) {
            parseSiteAndCalculateLength("youtube.com");
        }
    }

    @Benchmark
    public void crawlerConncurentBenchmark() throws ExecutionException, InterruptedException {
        parseConcurrently();
    }
}
