package com.latitude.stockprice;

import java.math.BigDecimal;
import java.util.List;

public class StockPriceApplication {
    private static final int FILE_PARAM = 0;

    public static void main(final String[] args) {
        try {
            if (args.length == 0) {
                throw new Exception("Please specify stock prices file.");
            }

            // Obtain list of stock prices from file passed in as parameter.
            List<BigDecimal> stockPrices = StockPriceService.getStockPrices(args[FILE_PARAM]);

            System.out.println(String.format("Maximum profit possible = $%s", StockPriceService.getMaxProfit(stockPrices)));
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
