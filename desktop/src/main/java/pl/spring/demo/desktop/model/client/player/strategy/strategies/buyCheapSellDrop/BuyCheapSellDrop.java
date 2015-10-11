package pl.spring.demo.desktop.model.client.player.strategy.strategies.buyCheapSellDrop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.client.player.strategy.Strategy;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketBuyTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketSellTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.marketTransactionFactory.MarketTransactionFactory;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Service
public class BuyCheapSellDrop implements Strategy {

	@Value("${buyCheapStrategy.maxTransactionsPerKind}")
	int maximumNumberOfGeneratedTransactions;
	final static Logger logger=Logger.getLogger("BuyCheapStrategy");
	@Value("${buyCheapStrategy.daysToDropCheck}")
	int daysToCheckDrop;
	@Value("${buyCheapStrategy.name}")
	String name;
	@Autowired
	BrokerageOffice brokerageOffice;

	@Autowired
	MarketTransactionFactory factory;

	@Override
	public List<MarketBuyTransaction> whatShouldClientBuy(double PLN) {

	List<StockDailyRecordTo> today = brokerageOffice.getCheapesStocksFromToday(maximumNumberOfGeneratedTransactions);
	List<MarketBuyTransaction> result=new ArrayList<MarketBuyTransaction>();

	int numberTransactions=ThreadLocalRandom.current().nextInt(0, maximumNumberOfGeneratedTransactions + 1);
	StockDailyRecordTo choosenStock = null;

			for(int i=0;i<numberTransactions;i++){
				choosenStock=today.get(i);
				int maxWhichClientCanBuy=(int)(PLN/choosenStock.getValue());
					int numberOfStockToBuy=getRandomInt(1,maxWhichClientCanBuy);
					result.add(factory.createBuyTransaction(choosenStock, numberOfStockToBuy));
			}
		return result;

	}

	@Override
	public List<MarketSellTransaction> whatShouldClientSell(HashMap<StockDailyRecordTo, Integer> stocks) {

		List<MarketSellTransaction> result=new ArrayList<MarketSellTransaction>();

		List<StockDailyRecordTo> stocksWhichClientHave= new ArrayList<>(stocks.keySet());
		List<StockDailyRecordTo> toSell=getWhatDropsAllTheTime(stocksWhichClientHave);
		if(toSell.size()>0){
		for(StockDailyRecordTo s:toSell){
				int numberOfStockToSell=getRandomInt(1,stocks.get(s));
					result.add(factory.createSellTransaction(s, numberOfStockToSell));
		}}

		return result;

	}


	private List<StockDailyRecordTo> getWhatDropsAllTheTime(List<StockDailyRecordTo> stocksWhichClientHave){
		List<StockDailyRecordTo> candidateToSell=new ArrayList<StockDailyRecordTo>();
		List<StockDailyRecordTo> beforeValues;
		Boolean alltimeDrop=true;
		for(StockDailyRecordTo st:stocksWhichClientHave){
			double todayValue=brokerageOffice.getStocksByCompanyNameFromToday(st.getCompany().getName()).getValue();
			beforeValues=brokerageOffice.getStocksByCompanyNameFromTodayXDaysBefore(st.getCompany().getName(), daysToCheckDrop);
			alltimeDrop=true;
		for(StockDailyRecordTo before:beforeValues){
			if(todayValue>=before.getValue()){
				alltimeDrop=false;
			}
		}
		if(alltimeDrop){
			candidateToSell.add(st);
		}


		}


		return candidateToSell;
	}

	private Integer getRandomInt(int min, int max ){
		if(max==0){
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
