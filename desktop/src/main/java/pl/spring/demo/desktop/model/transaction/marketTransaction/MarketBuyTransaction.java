package pl.spring.demo.desktop.model.transaction.marketTransaction;


import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;


public class MarketBuyTransaction  extends MarketTransaction{



	public MarketBuyTransaction() {
		super();
	}



	public MarketBuyTransaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super(stock, numberOfStockRequested);
	}


	public MarketBuyTransaction(StockDailyRecordTo stock, int numberOfStockRequested, Double upperBoundPriceRandomizer,
			Double lowerBoundPriceRandomizer, Double lowerBoundWarrantedNumberOfStock,
			Double upperBoundWarrantedNumberOfStock) {
		super(stock, numberOfStockRequested, upperBoundPriceRandomizer, lowerBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock);
		// TODO Auto-generated constructor stub
	}


}
