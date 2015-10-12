package pl.spring.demo.desktop.model.client.player.stockWallet.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.client.player.stockWallet.StockWallet;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Service
public class StockWalletImpl implements StockWallet {

	private HashMap<StockDailyRecordTo, Integer> wallet;
	final static Logger logger = Logger.getLogger("StockWallet");

	public StockWalletImpl() {
		wallet = new HashMap<StockDailyRecordTo, Integer>();
	}

	@Override
	public void addToWallet(StockDailyRecordTo c, Integer n) {
		logger.info("Client put to Stockwallet currency: " + c.getCompany().getName() + " quantity: " + n);
		if (!wallet.containsKey(c)) {
			wallet.put(c, 0);
		}
		Integer beforeNumber = wallet.get(c);
		wallet.put(c, beforeNumber + n);
	}

	@Override
	public HashMap<StockDailyRecordTo, Integer> showWallet() {
		return wallet;
	}

	@Override
	public void removeFromWallet(StockDailyRecordTo c, Integer number) {
		Integer currentNumber = wallet.get(c);
		logger.info(
				"Client want remove from Stockwallet currency: " + c.getCompany().getName() + " quantity: " + number);
		if (currentNumber < number) {// try remove more than have
			logger.info("client try remove more than have");
		}
		if (number == currentNumber) {// sell all stock from wallet
			wallet.remove(c);
			logger.info("Client sell all that stocks");
		}

		if (currentNumber > number) {
			wallet.replace(c, currentNumber - number);
			logger.info("Client sell that stocks and left in wallet quantity: " + (currentNumber - number));
		}

	}

	@Override
	public double getValueOfWallet() {
		double result = 0;
		for (StockDailyRecordTo s : wallet.keySet()) {
			result = result + s.getValue() * wallet.get(s);
		}
		return result;
	}

	@Override
	public void clear() {
		wallet.clear();

	}
}