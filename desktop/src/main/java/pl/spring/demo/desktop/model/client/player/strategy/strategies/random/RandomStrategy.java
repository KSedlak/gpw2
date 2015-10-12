package pl.spring.demo.desktop.model.client.player.strategy.strategies.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.client.player.strategy.Strategy;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.marketTransactionFactory.MarketTransactionFactory;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Service
public class RandomStrategy implements Strategy {

	@Value("${randomStrategy.maxTransactionsPerKind}")
	private int maximumNumberOfGeneratedTransactions;

	final static Logger logger = Logger.getLogger("RandomStrategy");

	@Value("${randomStrategy.name}")
	private String name;

	@Value("${randomStrategy.minimumSellPercent}")
	private double minimumSellPercent;

	@Autowired
	private BrokerageOffice brokerageOffice;

	@Autowired
	private MarketTransactionFactory factory;

	@Override
	public List<MarketTransaction> whatShouldClientBuy(double PLN) {

		List<StockDailyRecordTo> today = brokerageOffice.getTodayStockValues();
		List<MarketTransaction> result = new ArrayList<MarketTransaction>();
		int numberTransactions = ThreadLocalRandom.current().nextInt(0, maximumNumberOfGeneratedTransactions + 1);
		Set<Integer> indexes = generateNoRepetitionIndexes(numberTransactions, today.size() - 1);

		StockDailyRecordTo choosenStock = null;

		for (Integer i : indexes) {
			choosenStock = today.get(i);
			int maxWhichClientCanBuy = (int) (PLN / choosenStock.getValue());
			int numberOfStockToBuy = getRandomInt(1, maxWhichClientCanBuy);
			result.add(factory.createBuyTransaction(choosenStock, numberOfStockToBuy));
		}

		return result;

	}

	@Override
	public List<MarketTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo, Integer> stocks,
			List<StockDailyRecordTo> buyedToday) {

		List<MarketTransaction> result = new ArrayList<MarketTransaction>();

		int numberTransactions = ThreadLocalRandom.current().nextInt(0, maximumNumberOfGeneratedTransactions + 1);
		if (stocks.size() - 1 <= numberTransactions) {
			numberTransactions = stocks.size() - 1;
		}
		logger.info("Genearte " + numberTransactions + " random sell transactions");
		Set<Integer> indexes = generateNoRepetitionIndexes(numberTransactions, stocks.size() - 1);
		List<StockDailyRecordTo> stocksWhichClientHave = new ArrayList<>(stocks.keySet());
		StockDailyRecordTo choosenStock = null;

		for (Integer i : indexes) {
			choosenStock = stocksWhichClientHave.get(i);
			if (buyedToday.contains(choosenStock)) {
				logger.info("that stock was buyed today");
			}

			if (!buyedToday.contains(choosenStock)) {
				int minimum = (int) (stocks.get(choosenStock) * minimumSellPercent / 100);
				int numberOfStockToSell = getRandomInt(minimum, stocks.get(choosenStock));
				result.add(factory.createSellTransaction(choosenStock, numberOfStockToSell));
			}
		}

		return result;

	}

	private Set<Integer> generateNoRepetitionIndexes(int number, int maxIndex) {
		Set<Integer> noRepetitionIdx = new LinkedHashSet<Integer>();
		while (noRepetitionIdx.size() < number) {
			noRepetitionIdx.add(getRandomInt(0, maxIndex));
		}
		return noRepetitionIdx;
	}

	private Integer getRandomInt(int min, int max) {
		if (max == 0) {
			return 0;
		}

		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
