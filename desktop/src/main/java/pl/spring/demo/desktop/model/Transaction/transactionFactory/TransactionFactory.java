package pl.spring.demo.desktop.model.Transaction.transactionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.Transaction.BuyTransaction;
import pl.spring.demo.desktop.model.Transaction.SellTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
@Qualifier("transactionFactory")
public class TransactionFactory {

	@Value("${transaction.sell.lowerBoundPriceRandomizer}")
	private Double lowerSellBoundPriceRandomizer;

	@Value("${transaction.sell.upperBoundPriceRandomizer}")
	private Double upperSellBoundPriceRandomizer;

	@Value("${transaction.lowerBoundNumberOfStockRandomizer}")
	private Double lowerBoundWarrantedNumberOfStock;

	@Value("${transaction.upperBoundNumberOfStockRandomizer}")
	private Double upperBoundWarrantedNumberOfStock;

	@Value("${transaction.buy.lowerBoundPriceRandomizer}")
	private Double lowerBuyBoundPriceRandomizer;

	@Value("${transaction.buy.upperBoundPriceRandomizer}")
	private Double upperBuyBoundPriceRandomizer;


	public SellTransaction createSellTransaction(StockDailyRecordTo stock, int n){
		return new SellTransaction(stock,n,upperSellBoundPriceRandomizer,lowerSellBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock,upperBoundWarrantedNumberOfStock);
	}

	public BuyTransaction createBuyTransaction(StockDailyRecordTo stock, int n){
		return new  BuyTransaction(stock,n,upperBuyBoundPriceRandomizer,lowerBuyBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock,upperBoundWarrantedNumberOfStock);
	}
}
