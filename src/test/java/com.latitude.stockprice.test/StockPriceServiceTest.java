package com.latitude.stockprice.test;

import com.latitude.stockprice.StockPriceService;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockPriceServiceTest {

    private static final String TEST_DATA_FILE = "full_test_data.csv";
    private static final String INVALID_DATA_FILE = "invalid_test_data.csv";
    private static final BigDecimal EXPECTED_MAX_PROFIT = new BigDecimal("8.97");

    @Test
    public void successfullyGetMaxProfit() throws Exception {
        Path testFilePath = Paths.get(ClassLoader.getSystemResource(TEST_DATA_FILE).toURI());
        List<BigDecimal> stockPrices = StockPriceService.getStockPrices(testFilePath.toAbsolutePath().toString());
        assertEquals(EXPECTED_MAX_PROFIT, StockPriceService.getMaxProfit(stockPrices));
    }

    @Test(expected = NumberFormatException.class)
    public void invalidInputInTestFile() throws Exception {
        Path testFilePath = Paths.get(ClassLoader.getSystemResource(INVALID_DATA_FILE).toURI());
        List<BigDecimal> stockPrices = StockPriceService.getStockPrices(testFilePath.toAbsolutePath().toString());
    }
}
