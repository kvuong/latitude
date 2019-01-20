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
