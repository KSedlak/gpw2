package pl.spring.demo.desktop.model.transaction;


import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;


public class BuyTransaction  extends Transaction{



	public BuyTransaction() {
		super();
	}



	public BuyTransaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super(stock, numberOfStockRequested);
	}


	public BuyTransaction(StockDailyRecordTo stock, int numberOfStockRequested, Double upperBoundPriceRandomizer,
			Double lowerBoundPriceRandomizer, Double lowerBoundWarrantedNumberOfStock,
			Double upperBoundWarrantedNumberOfStock) {
		super(stock, numberOfStockRequested, upperBoundPriceRandomizer, lowerBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock);
		// TODO Auto-generated constructor stub
	}


}
