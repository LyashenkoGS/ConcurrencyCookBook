package com.concurrency.cook.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(JUnit4.class)
public class MyBenchmarkTest {
private static Logger LOG = LoggerFactory.getLogger(MyBenchmarkTest.class);

    @Test
    public void testMethod() {
       new MyBenchmark().testMethod();
    }

}
