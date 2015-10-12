package pl.spring.demo.desktop.model.transaction.marketTransaction;

import org.springframework.stereotype.Component;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
public class MarketSellTransaction extends MarketTransaction {

	public MarketSellTransaction(StockDailyRecordTo stock, int numberOfStockRequested, Double upperBoundPriceRandomizer,
			Double lowerBoundPriceRandomizer, Double lowerBoundWarrantedNumberOfStock,
			Double upperBoundWarrantedNumberOfStock) {
		super(stock, numberOfStockRequested, upperBoundPriceRandomizer, lowerBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock);
	}

	public MarketSellTransaction() {
		super();
	}

}
