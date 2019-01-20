package com.latitude.stockprice;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StockPriceService {

    /**
     * Function that takes an array of stock prices and returns the best profit that could be made from 1 purchase and
     * 1 sale of 1 Latitude Financial stock.
     * @param stockPrices List of stock prices from yesterday.
     * @return Maximum profit that could be made.
     */
    public static BigDecimal getMaxProfit(final List<BigDecimal> stockPrices) throws Exception {
        BigDecimal largestGain = new BigDecimal(0);

        try {
            BigDecimal lowestPrice = stockPrices.get(0);

            for (int index = 1; index < stockPrices.size(); index++) {
                BigDecimal intervalGain = stockPrices.get(index).subtract(lowestPrice);
                if (intervalGain.compareTo(largestGain) > 0) {
                    largestGain = intervalGain;
                }
                if (stockPrices.get(index).compareTo(lowestPrice) < 0) {
                    lowestPrice = stockPrices.get(index);
                }
            }
        } catch (Exception error) {
            throw new Exception("Unable to calculate max profit.", error);
        }

        return largestGain;
    }

    /**
     * Get stock prices from file.
     * @return List of stock prices.
     * @throws Exception
     */
    public static List<BigDecimal> getStockPrices(final String fileName) throws Exception {
        List<BigDecimal> prices = new ArrayList<>();
        Path inputFilePath = Paths.get(fileName);
        Stream<String> stream = null;

        try {
            stream = Files.lines(inputFilePath);
            stream.forEach(stockPrice -> prices.add(new BigDecimal(stockPrice)));
        } catch (Exception exception) {
            throw new Exception(String.format("Unable to load stock prices from file \"%s\". Please ensure the file and "
                    + "this application are in the same directory.", inputFilePath.toAbsolutePath().normalize()));
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return prices;
    }
}
