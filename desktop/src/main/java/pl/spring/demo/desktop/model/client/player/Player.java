package pl.spring.demo.desktop.model.client.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeStatusChanged;
import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.client.person.Person;
import pl.spring.demo.desktop.model.client.player.event.NoMoreActionToday;
import pl.spring.demo.desktop.model.client.player.stockWallet.StockWallet;
import pl.spring.demo.desktop.model.client.player.strategy.Strategy;
import pl.spring.demo.desktop.model.client.player.wallet.Wallet;
import pl.spring.demo.desktop.model.client.player.walletBallancer.WalletBalancer;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.status.Status;
import pl.spring.demo.desktop.model.transaction.exchangeTransaction.ExchangeTransaction;
import pl.spring.demo.desktop.model.transaction.exchangeTransaction.typeOfExchangeTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketBuyTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketSellTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.StatusOfTransaction;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
public class Player extends Person
		implements ApplicationListener<BrokerageOfficeStatusChanged>, ApplicationContextAware {

	@Autowired
	private Wallet wallet;

	@Autowired
	private StockWallet stockWallet;

	@Autowired
	Cantor cantor;

	@Value("${player.lastName}")
	private String lastName;

	private Strategy currentStrategy;

	@Value("${transaction.changePercentToAccept}")
	private Double TRANSACTION_CHANGE_PERCENT_TOLLERANCE;

	private ApplicationContext applicationContext;

	final static Logger logger = Logger.getLogger("Player");

	@Autowired
	BrokerageOffice brokerageOffice;

	@Autowired
	WalletBalancer walletBallancer;

	@Autowired
	public Player(@Value("${player.firstName}") String f, @Value("${player.lastName}") String l) {
		super(f, l);
	}

	@Override
	public void onApplicationEvent(BrokerageOfficeStatusChanged event) {

		if (event.getBrokerageOfficeStatus().equals(Status.Open)) {
			logger.info("Client get notification about open BrokerageOffic and start job");
			makeTaskForToday();
		}

	}

	public void setCurrentStrategy(Strategy currentStrategy) {
		this.currentStrategy = currentStrategy;
	}

	private void makeTodayOperationsOnMarket() {
		List<StockDailyRecordTo> buyedToday = doTodayBuyTransactions();
		doTodaySellTransactions(buyedToday);
		logger.info("Client end all operations in brokerageOffice");
	}

	private List<StockDailyRecordTo> doTodayBuyTransactions() {
		logger.info("Client ask strategy for BUY transactions");
		double availableMoney = wallet.getMoney(Currency.PLN);
		List<StockDailyRecordTo> buyedToday = new ArrayList<StockDailyRecordTo>();
		List<MarketBuyTransaction> todayBuy = currentStrategy.whatShouldClientBuy(availableMoney);
		logger.info("Client start ask brokerageOffice to make transactions");
		for (MarketBuyTransaction buy : todayBuy) {
			MarketTransaction response = brokerageOffice.makeTransaction(buy);

			if (response.getChangeValueOFTransaction() > TRANSACTION_CHANGE_PERCENT_TOLLERANCE
					|| !canAffordThatTransaction(availableMoney, response)) {
				response.setStatus(StatusOfTransaction.Rejected);
				logger.info("Client reject brokerageOfficeOffer");
			}

			if (response.getChangeValueOFTransaction() <= TRANSACTION_CHANGE_PERCENT_TOLLERANCE
					&& canAffordThatTransaction(availableMoney, response)) {
				logger.info("Client accept conditions and make that deal");
				availableMoney = acceptBuyTransaction(availableMoney, response);
				buyedToday.add(response.getStock());

			}
		}
		return buyedToday;
	}

	public void doTodaySellTransactions(List<StockDailyRecordTo> buyedToday) {
		logger.info("Client ask strategy for SELL transactions");
		HashMap<StockDailyRecordTo, Integer> showToStrategy = stockWallet.showWallet();
		List<MarketSellTransaction> todaySell = currentStrategy.whatShouldClientSell(showToStrategy, buyedToday);
		logger.info("Client start ask brokerageOffice to make transactions");
		for (MarketSellTransaction sell : todaySell) {

			MarketTransaction response = brokerageOffice.makeTransaction(sell);

			if (response.getChangeValueOFTransaction() > TRANSACTION_CHANGE_PERCENT_TOLLERANCE) {
				response.setStatus(StatusOfTransaction.Rejected);
				logger.info("Client reject brokerageOfficeOffer");
			}

			if (response.getChangeValueOFTransaction() <= TRANSACTION_CHANGE_PERCENT_TOLLERANCE) {
				logger.info("Client accept conditions and make that deal");
				acceptSellTransaction(response);

			}
		}
	}

	private void acceptSellTransaction(MarketTransaction response) {
		response.setStatus(StatusOfTransaction.Accepted);
		stockWallet.removeFromWallet(response.getStock(), response.getBrokerageOfficeAcceptedNumber());
		wallet.addToWallet(Currency.PLN,
				response.getValueOfBrokerageOfficeOffer() - response.getBrokerageOfficeCommission());

	}

	private double acceptBuyTransaction(double availableMoney, MarketTransaction response) {
		response.setStatus(StatusOfTransaction.Accepted);
		double transactionCost = DoubleRounder
				.roundToMoney(response.getValueOfBrokerageOfficeOffer() + response.getBrokerageOfficeCommission());
		availableMoney = availableMoney - transactionCost;
		wallet.removeFromWallet(Currency.PLN, transactionCost);
		stockWallet.addToWallet(response.getStock(), response.getBrokerageOfficeAcceptedNumber());
		return availableMoney;
	}

	private boolean canAffordThatTransaction(double currentMoney, MarketTransaction t) {
		double cost = DoubleRounder
				.roundToMoney((t.getValueOfBrokerageOfficeOffer() + t.getBrokerageOfficeCommission()));
		boolean canI = currentMoney > cost;
		logger.info("Client check wallet-> have: " + currentMoney + " cost: " + cost + " canMakeIt: " + canI);
		return canI;
	}

	private void keepBalanceInWallet(Currency a, Currency b) {
		logger.info("Client wants to keep  ballance in wallet");
		ExchangeTransaction ex = walletBallancer.makeBallance(a, wallet.getMoney(a), b, wallet.getMoney(b));
		if (!ex.getCurrency().equals(a)) {

			if (ex.getType().equals(typeOfExchangeTransaction.Buy)) {
				logger.info("Client have to buy currency " + b.getName());
				double cost = cantor.sellCurrencyToClient(ex.getCurrency(), ex.getInput());
				wallet.removeFromWallet(a, cost);
				;
				wallet.addToWallet(b, ex.getInput());
			}
			if (ex.getType().equals(typeOfExchangeTransaction.Sell)) {
				logger.info("Client have to sell currency " + b.getName());
				double result = cantor.buyCurrencyFromClient(ex.getCurrency(), ex.getInput());
				wallet.removeFromWallet(b, ex.getInput());
				wallet.addToWallet(a, result);
			}
		}
		logger.info("Client wallet ballanced");
		walletBallancer.getBallance(a, wallet.getMoney(a), b, wallet.getMoney(b));

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public double howMuchMoneyHave(Currency c) {

		return wallet.getMoney(c);
	}

	public double howMuchStockValueHave() {
		return DoubleRounder.roundToMoney(stockWallet.getValueOfWallet());
	}

	public HashMap<StockDailyRecordTo, Integer> showStockWallet() {
		return stockWallet.showWallet();
	}

	public double getValueOfAllResources() {
		double result = 0;
		result = result + howMuchMoneyHave(Currency.PLN);
		result = result + howMuchMoneyHave(Currency.EURO) * cantor.getBuyRate(Currency.EURO);
		result = result + howMuchStockValueHave();
		result = DoubleRounder.roundToMoney(result);
		return result;
	}

	public void makeTaskForToday() {
		Task<Status> backgroundTask = new Task<Status>() {

			@Override
			protected Status call() throws Exception {

				makeTodayOperationsOnMarket();
				keepBalanceInWallet(Currency.PLN, Currency.EURO);
				return Status.Closed;
			}
		};

		backgroundTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if (newValue == State.SUCCEEDED) {
					succes();
				}
			}

			public void succes() {
				logger.info("Task for Today ends");
				applicationContext.publishEvent(new NoMoreActionToday(Status.Closed));
			}

		});

		new Thread(backgroundTask).start();
	}
}