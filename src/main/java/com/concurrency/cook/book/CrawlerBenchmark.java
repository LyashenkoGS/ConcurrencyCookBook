package com.concurrency.cook.book;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.ExecutionException;

import static com.concurrency.cook.book.crawler.JsoupCrawler.parseConcurrently;
import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteAndCalculateLength;

/**
 * @author lyashenkogs.
 */
public class CrawlerBenchmark {

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @Fork(value = 1, warmups = 1)
    @Benchmark
    public void crawleSequentialBenchmark() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 8; i++) {
            parseSiteAndCalculateLength("youtube.com");
        }
    }

   //TODO there is something wrong in benchmark !! Need to fix.
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @Fork(value = 1, warmups = 1)
    @Benchmark
    public void crawlerConncurentBenchmark() throws ExecutionException, InterruptedException {
        parseConcurrently();
    }
}
