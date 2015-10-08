package pl.spring.demo.desktop.model.client.Player.strategy;
import java.util.HashMap;
import java.util.List;

import pl.spring.demo.desktop.model.Transaction.BuyTransaction;
import pl.spring.demo.desktop.model.Transaction.SellTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
public interface Strategy {


List<BuyTransaction> whatShouldClientBuy(double PLN);
List<SellTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo,Integer> stocks);

}
