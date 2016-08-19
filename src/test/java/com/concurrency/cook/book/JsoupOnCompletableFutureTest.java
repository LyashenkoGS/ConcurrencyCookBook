package com.concurrency.cook.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutionException;

import static com.concurrency.cook.book.crawler.JsoupCrawler.parseConcurrently;
import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteAndCalculateLength;

/**
 * @author lyashenkogs.
 */
@RunWith(JUnit4.class)
public class JsoupOnCompletableFutureTest {

    @Test
    public void testParseSequentially() {
        for (int i = 0; i < 8; i++) {
            parseSiteAndCalculateLength("youtube.com");
        }
    }

    @Test
    public void testParseViaCompletableFeature() throws ExecutionException, InterruptedException {
        parseConcurrently();
    }


}
