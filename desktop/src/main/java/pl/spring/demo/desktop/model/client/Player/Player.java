package pl.spring.demo.desktop.model.client.Player;

import java.util.HashMap;
import java.util.List;

import org.hibernate.metamodel.relational.Size;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.spring.demo.desktop.model.Transaction.BuyTransaction;
import pl.spring.demo.desktop.model.Transaction.SellTransaction;
import pl.spring.demo.desktop.model.Transaction.StatusOfTransaction;
import pl.spring.demo.desktop.model.Transaction.Transaction;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeStatusChanged;
import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.client.Person.Person;
import pl.spring.demo.desktop.model.client.Player.strategy.Strategy;
import pl.spring.demo.desktop.model.client.Player.wallet.Wallet;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.client.Player.stockWallet.StockWallet;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Component
public class Player extends Person implements ApplicationListener<BrokerageOfficeStatusChanged>,  ApplicationContextAware {

	@Autowired
	private Wallet wallet;

	@Autowired
	private StockWallet stockWallet;

	@Value("${player.moneyWalletBalance}")
	private double BALLANCE_WALLET;
	@Value("${player.moneyWalletBalanceTolerance}")
	private double BALLANCE_TOLERANCE;
	@Value("${player.lastName}")
	private String lastName;
	@Autowired
	private Cantor cantor;
	@Autowired
	private Strategy currentStrategy;
	@Value("${transaction.changePercentToAccept}")
	private Double TRANSACTION_CHANGE_PERCENT_TOLLERANCE;
	private ApplicationContext applicationContext;

	@Autowired
	BrokerageOffice brokerageOffice;


	@Autowired
	public Player(@Value("${player.firstName}") String f, @Value("${player.lastName}") String l) {
		super(f, l);
	}

	@Override
	public void onApplicationEvent(BrokerageOfficeStatusChanged event) {
		makeTodayOperationsOnMarket();
		keepBalanceInWallet();



	}

	private void makeTodayOperationsOnMarket() {
		doTodayBuyTransactions();
		doTodaySellTransactions();
	}

	private void doTodayBuyTransactions() {

		double availableMoney = wallet.getMoney(Currency.PLN);
		List<BuyTransaction> todayBuy = currentStrategy.whatShouldClientBuy(availableMoney);
		for (BuyTransaction buy : todayBuy) {
			Transaction response = brokerageOffice.makeTransaction(buy);

			if (response.getChangeValueOFTransaction() > TRANSACTION_CHANGE_PERCENT_TOLLERANCE) {
				response.setStatus(StatusOfTransaction.Rejected);
				// Future can handle that
			}

			if (response.getChangeValueOFTransaction() <= TRANSACTION_CHANGE_PERCENT_TOLLERANCE
					&& canAffordThatTransaction(availableMoney, response)) {

				availableMoney = acceptBuyTransaction(availableMoney, response);


			}
		}
	}


	public void doTodaySellTransactions() {
		HashMap<StockDailyRecordTo, Integer> showToStrategy = stockWallet.showWallet();
		List<SellTransaction> todaySell = currentStrategy.whatShouldClientSell(showToStrategy);
		for (SellTransaction sell : todaySell) {

			Transaction response = brokerageOffice.makeTransaction(sell);

			if (response.getChangeValueOFTransaction()> TRANSACTION_CHANGE_PERCENT_TOLLERANCE) {
				response.setStatus(StatusOfTransaction.Rejected);
				// Future can handle that
			}

			if (response.getChangeValueOFTransaction() <= TRANSACTION_CHANGE_PERCENT_TOLLERANCE) {

				acceptSellTransaction(response);

			}
		}
	}

	private void acceptSellTransaction(Transaction response) {
		response.setStatus(StatusOfTransaction.Accepted);
		stockWallet.removeFromWallet(response.getStock(), response.getBrokerageOfficeAcceptedNumber());
		wallet.addToWallet(Currency.PLN, response.getValueOfBrokerageOfficeOffer()-response.getBrokerageOfficeCommission());

	}

	private double acceptBuyTransaction(double availableMoney, Transaction response) {
		response.setStatus(StatusOfTransaction.Accepted);
		response.setStatus(StatusOfTransaction.Accepted);
		double transactionCost = response.getValueOfBrokerageOfficeOffer()+response.getBrokerageOfficeCommission();
		availableMoney = availableMoney - transactionCost;
		wallet.removeFromWallet(Currency.PLN, transactionCost);
		stockWallet.addToWallet(response.getStock(), response.getBrokerageOfficeAcceptedNumber());
		return availableMoney;
	}



	private boolean canAffordThatTransaction(double currentMoney, Transaction t) {
		return currentMoney >( t.getValueOfBrokerageOfficeOffer()+t.getBrokerageOfficeCommission());
	}


	private void keepBalanceInWallet(){
		double valueOfPLN=wallet.getMoney(Currency.PLN);
		double valueOfEuro=wallet.getMoney(Currency.EURO)*cantor.getNbpRate(Currency.EURO);

		double valueOfWallet= valueOfEuro+valueOfPLN;

		double ballance=calculateBalance(valueOfPLN,valueOfWallet);

		double toleratedBalanceLower=BALLANCE_WALLET-BALLANCE_TOLERANCE;
		double toleratedBalanceUpper=BALLANCE_WALLET+BALLANCE_TOLERANCE;
		if(ballance<toleratedBalanceLower){
			//sell euro
			double euroToSell=((valueOfWallet*(BALLANCE_WALLET/100))-valueOfEuro)/cantor.getNbpRate(Currency.EURO);
			wallet.addToWallet(Currency.PLN, cantor.buyCurrency(Currency.EURO, euroToSell));
			wallet.removeFromWallet(Currency.EURO, euroToSell);
		}

		if(ballance>toleratedBalanceUpper){
			//sell euro
			double plnToSell=((valueOfWallet*(BALLANCE_WALLET/100))-valueOfPLN);
			wallet.addToWallet(Currency.EURO, cantor.sellCurrency(Currency.EURO, plnToSell));
			wallet.removeFromWallet(Currency.PLN, plnToSell);
		}

	}

	private double calculateBalance(double a, double b){
		if(a!=0 && b!=0){
			return (a*100)/b;
		}
		return 0;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public double howMuchMoneyHave(Currency c){
		return wallet.getMoney(c);
	}
	public HashMap<StockDailyRecordTo, Integer> showStockWallet(){
		return stockWallet.showWallet();
	}
}
