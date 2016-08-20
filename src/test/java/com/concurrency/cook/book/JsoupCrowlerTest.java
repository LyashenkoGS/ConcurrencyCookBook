package com.concurrency.cook.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutionException;

import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteNtimesConcurrently;
import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteAndCalculateLength;
import static com.concurrency.cook.book.crawler.JsoupCrawler.parseSiteNtimesSequentially;

/**
 * @author lyashenkogs.
 */
@RunWith(JUnit4.class)
public class JsoupCrowlerTest {


    @Test
    public void testParseNtimesSequentially(){
        parseSiteNtimesSequentially("youtube.com",2);
    }

    @Test
    public void testParseNtimesConcurrently() throws ExecutionException, InterruptedException {
        parseSiteNtimesConcurrently("youtube.com", 2);
    }

    @Test
    public void testParseAndCalculateLength(){
        parseSiteAndCalculateLength("youtube.com");
    }


}
