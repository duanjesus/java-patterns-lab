package com.javapatternslab.singleton;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonTest {

    @Test
    void getInstanceAlwaysReturnsTheSameObject() {
        CheckoutConfig first = CheckoutConfig.getInstance();
        CheckoutConfig second = CheckoutConfig.getInstance();

        assertSame(first, second);
    }

    @Test
    void concurrentFirstAccessStillYieldsExactlyOneInstance() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(16);
        try {
            Callable<CheckoutConfig> task = CheckoutConfig::getInstance;
            var futures = IntStream.range(0, 100)
                    .mapToObj(i -> pool.submit(task))
                    .toList();

            Set<CheckoutConfig> distinctInstances = futures.stream()
                    .map(SingletonTest::getUnchecked)
                    .collect(Collectors.toSet());

            assertEquals(1, distinctInstances.size());
        } finally {
            pool.shutdown();
        }
    }

    private static CheckoutConfig getUnchecked(Future<CheckoutConfig> future) {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
