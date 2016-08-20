package com.concurrency.cook.book.crawler;

import java.io.File;
import java.util.concurrent.ExecutionException;

import etm.contrib.console.HttpConsoleServer;
import etm.core.configuration.EtmManager;
import etm.core.configuration.XmlEtmConfigurator;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import etm.core.renderer.SimpleTextRenderer;

/**
 * Execute benchmarks, deploy internal JETM server to display results over http(html console),
 *
 * @author lyashenkogs.
 */
public class BenchmarksRunner {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        XmlEtmConfigurator.configure(new File("jetm-config.xml"));
        // startup measurement subsystem
        EtmMonitor etmMonitor = EtmManager.getEtmMonitor();
        HttpConsoleServer server = new HttpConsoleServer(etmMonitor);
        // override default listen port
        server.setListenPort(4500);
        server.start();
        etmMonitor.start();
        // execute business logic
        SimpleJetmBenchmark.twoMultiplyTwo();
        parseYoutube8TimesConcurrentlyBenchmark(etmMonitor);
        parseYoutube8TimesSequentiallyBenchmark(etmMonitor);
        // visualize results
        etmMonitor.render(new SimpleTextRenderer());
        // shutdown measurement framework
        etmMonitor.stop();
    }

    private static void parseYoutube8TimesConcurrentlyBenchmark(EtmMonitor etmMonitor) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            EtmPoint point = etmMonitor.createPoint("CrawlerBenchmark:parseYotube8TimesConcurrently");
            JsoupCrawler.parseSiteNtimesConcurrently("youtube.com", 8);
            point.collect();
        }
    }

    private static void parseYoutube8TimesSequentiallyBenchmark(EtmMonitor etmMonitor) {
        for (int i = 0; i < 5; i++) {
            EtmPoint point1 = etmMonitor.createPoint("CrawlerBenchmark:parseYotube8TimesSequentially");
          JsoupCrawler.parseSiteNtimesSequentially("youtube.com", 8);
            point1.collect();
        }
    }
}
