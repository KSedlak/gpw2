package pl.spring.demo.desktop.model.Transaction;

import org.springframework.beans.factory.annotation.Value;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public class SellTransaction extends Transaction {

	@Value("${transaction.sell.lowerBoundPriceRandomizer}")
	private Double lowerBoundPriceRandomizer;

	@Value("${transaction.sell.lowerBoundPriceRandomizer}")
	private Double upperBoundPriceRandomizer;

	public SellTransaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super(stock, numberOfStockRequested);
	}

}
