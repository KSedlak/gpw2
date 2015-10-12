package pl.spring.demo.desktop.model.client.player.strategy;

import java.util.HashMap;
import java.util.List;

import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface Strategy {

	List<MarketTransaction> whatShouldClientBuy(double PLN);

	List<MarketTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo, Integer> stocks,
			List<StockDailyRecordTo> buyedToday);

}
