package pl.spring.demo.desktop.model.transaction.marketTransaction.marketTransactionFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.transaction.typeOTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
@Qualifier("marketTransactionFactory")
public class MarketTransactionFactory {

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

	final static Logger logger = Logger.getLogger("MarketTransaction factory");

	public MarketTransaction createSellTransaction(StockDailyRecordTo stock, int n) {

		logger.info("Sell transaction created. Stock: " + stock.getCompany().getName() + " quantity: " + n);

		return new MarketTransaction(stock, n, upperSellBoundPriceRandomizer, lowerSellBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock, typeOTransaction.Sell);

	}

	public MarketTransaction createBuyTransaction(StockDailyRecordTo stock, int n) {

		logger.info("Buy transaction created. Stock: " + stock.getCompany().getName() + " quantity: " + n);

		return new MarketTransaction(stock, n, upperBuyBoundPriceRandomizer, lowerBuyBoundPriceRandomizer,
				lowerBoundWarrantedNumberOfStock, upperBoundWarrantedNumberOfStock,typeOTransaction.Buy);
	}
}
