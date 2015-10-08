package pl.spring.demo.desktop.model.client.Player.strategy.strategies.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.spring.demo.desktop.model.Transaction.BuyTransaction;
import pl.spring.demo.desktop.model.Transaction.SellTransaction;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.client.Player.strategy.Strategy;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Service
@Qualifier("randomStrategy")
public class RandomStrategy implements Strategy {

	@Value("${randomStrategy.maxTransactionsPerKind}")
	int maximumNumberOfGeneratedTransactions;



	@Autowired
	BrokerageOffice brokerageOffice;


	@Override
	public List<BuyTransaction> whatShouldClientBuy(double PLN) {
	List<StockDailyRecordTo> today = brokerageOffice.getTodayStockValues();
	List<BuyTransaction> result=new ArrayList<BuyTransaction>();
	int numberTransactions=ThreadLocalRandom.current().nextInt(0, maximumNumberOfGeneratedTransactions + 1);
	Set<Integer> indexes=generateNoRepetitionIndexes(numberTransactions, today.size());

	StockDailyRecordTo choosenStock = null;

			for(Integer i:indexes){
				choosenStock=today.get(i);
				int maxWhichClientCanBuy=(int)(PLN/choosenStock.getValue());
					int numberOfStockToBuy=getRandomInt(1,maxWhichClientCanBuy);
					result.add(new BuyTransaction(choosenStock, numberOfStockToBuy));
			}

		return result;
	}

	@Override
	public List<SellTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo, Integer> stocks) {
		List<SellTransaction> result=new ArrayList<SellTransaction>();
		int numberTransactions=ThreadLocalRandom.current().nextInt(0, maximumNumberOfGeneratedTransactions + 1);

		Set<Integer> indexes=generateNoRepetitionIndexes(numberTransactions, stocks.size());
		List<StockDailyRecordTo> stocksWhichClientHave= new ArrayList<>(stocks.keySet());
		StockDailyRecordTo choosenStock = null;

		for(Integer i:indexes){
			choosenStock=stocksWhichClientHave.get(i);
				int numberOfStockToBuy=getRandomInt(1,stocks.get(choosenStock));
				result.add(new SellTransaction(choosenStock, numberOfStockToBuy));
		}

		return result;

	}

	private Set<Integer> generateNoRepetitionIndexes(int number, int maxIndex){
		Set<Integer> noRepetitionIdx=new LinkedHashSet<Integer>();
		for(int i=0;i<number;i++){
			noRepetitionIdx.add(getRandomInt(number, maxIndex));
		}
		return noRepetitionIdx;
	}

	private Integer getRandomInt(int min, int max ){
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
