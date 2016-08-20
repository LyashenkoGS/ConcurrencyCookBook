package com.concurrency.cook.book.crawler;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * An example of a simple benchmark on JETM framework
 *
 * @author lyashenkogs.
 */
public class SimpleJetmBenchmark {
    private static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

    public static void twoMultiplyTwo() {
        EtmPoint point = etmMonitor.createPoint("SimpleJetmBenchmark:twoMultiplyTwo");
        int result = 2 * 2;
        point.collect();
    }
}
