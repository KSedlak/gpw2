package pl.spring.demo.desktop.model.Transaction;

import org.springframework.beans.factory.annotation.Value;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public class BuyTransaction  extends Transaction{

	@Value("${transaction.buy.lowerBoundPriceRandomizer}")
	private Double lowerBoundPriceRandomizer;

	@Value("${transaction.buy.lowerBoundPriceRandomizer}")
	private Double upperBoundPriceRandomizer;



	public BuyTransaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super(stock, numberOfStockRequested);
	}

}
