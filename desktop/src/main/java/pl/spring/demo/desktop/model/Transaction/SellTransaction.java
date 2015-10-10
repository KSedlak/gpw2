package pl.spring.demo.desktop.model.Transaction;

import org.springframework.stereotype.Component;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
public class SellTransaction extends Transaction {


	public SellTransaction(StockDailyRecordTo stock,
			int numberOfStockRequested,
			Double upperBoundPriceRandomizer,
			Double lowerBoundPriceRandomizer,
			Double lowerBoundWarrantedNumberOfStock,
			Double upperBoundWarrantedNumberOfStock) {
		super(stock, numberOfStockRequested, upperBoundPriceRandomizer, lowerBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock);
	}

	public SellTransaction() {
		super();
	}


}
