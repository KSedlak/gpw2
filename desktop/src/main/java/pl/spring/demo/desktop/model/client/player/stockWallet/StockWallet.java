package pl.spring.demo.desktop.model.client.player.stockWallet;


import java.util.HashMap;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockWallet {
	void addToWallet(StockDailyRecordTo c, Integer n);
	void removeFromWallet(StockDailyRecordTo c, Integer number);
	HashMap<StockDailyRecordTo, Integer> showWallet();
	double getValueOfWallet();
}