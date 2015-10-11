package pl.spring.demo.desktop.model.client.player.strategy;
import java.util.HashMap;
import java.util.List;

import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketBuyTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketSellTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
public interface Strategy {


List<MarketBuyTransaction> whatShouldClientBuy(double PLN);
List<MarketSellTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo,Integer> stocks);

}
