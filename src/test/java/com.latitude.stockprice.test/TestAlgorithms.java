package com.latitude.stockprice.test;

import com.latitude.stockprice.StockPriceService;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestAlgorithms {

    private final Logger logger = Logger.getLogger(this.getClass());
    private static final String TEST_DATA_FILE = "simple_test_data.csv";
    private static final TimeUnit TIME_UNIT = TimeUnit.MICROSECONDS;
    private static final BigDecimal EXPECTED_GAIN = new BigDecimal(6);
    private List<BigDecimal> stockPrices;

    @Before
    public void setupTest() throws Exception {
        // Load test data from file.
        Path testFilePath = Paths.get(ClassLoader.getSystemResource(TEST_DATA_FILE).toURI());
        stockPrices = StockPriceService.getStockPrices(testFilePath.toAbsolutePath().toString());
    }

    /**
     * Simple algorithm that takes each stock price and subtract from all other stock prices until the largest gain is found.
     * Complexity = O(n^2)
     */
    @Test
    public void algorithmATest() {
        BigDecimal largestGain = new BigDecimal(0);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < stockPrices.size(); i++) {
            for (int j = i + 1; j < stockPrices.size(); j++) {
                BigDecimal intervalGain = stockPrices.get(j).subtract(stockPrices.get(i));

                if (intervalGain.compareTo(largestGain) > 0) {
                    largestGain = intervalGain;
                }
            }
        }

        logger.info(String.format("AlgorithmA took %s(%s) to complete.",
                stopWatch.getTime(TIME_UNIT), TIME_UNIT.toString(), largestGain.toString()));
        stopWatch.stop();
        assertEquals(EXPECTED_GAIN, largestGain);
    }

    /**
     * Algorithm that loops through stock prices comparing the gain between the lowest price thus far and prices thereafter.
     * Complexity = O(n)
     */
    @Test
    public void algorithmBTest() {
        BigDecimal lowestPrice = stockPrices.get(0);
        BigDecimal largestGain = new BigDecimal(0);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int index = 1; index < stockPrices.size(); index++) {
            BigDecimal intervalGain = stockPrices.get(index).subtract(lowestPrice);
            if (intervalGain.compareTo(largestGain) > 0) {
                largestGain = intervalGain;
            }
            if (stockPrices.get(index).compareTo(lowestPrice) < 0) {
                lowestPrice = stockPrices.get(index);
            }
        }

        logger.info(String.format("AlgorithmB took %s(%s) to complete.",
                stopWatch.getTime(TIME_UNIT), TIME_UNIT.toString(), largestGain.toString()));
        stopWatch.stop();
        assertEquals(EXPECTED_GAIN, largestGain);
    }

    /**
     * Algorithm that uses derivatives whereby the sum of positive rate of change between two points in time represents
     * greatest gain.
     * Complexity: O(n)
     */
    @Test
    public void algorithmCTest() {
        BigDecimal largestGain = new BigDecimal(0);
        BigDecimal sum = new BigDecimal(0);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 1; i < stockPrices.size(); i++) {
            BigDecimal priceDiff = stockPrices.get(i).subtract(stockPrices.get(i - 1));

            if (sum.compareTo(BigDecimal.ZERO) > 0) {
                sum = sum.add(priceDiff);
            } else {
                sum = priceDiff;
            }

            if (sum.compareTo(largestGain) > 0) {
                largestGain = sum;
            }
        }

        logger.info(String.format("AlgorithmC took %s(%s) to complete.",
                stopWatch.getTime(TIME_UNIT), TIME_UNIT.toString(), largestGain.toString()));
        stopWatch.stop();
        assertEquals(EXPECTED_GAIN, largestGain);
    }
}
