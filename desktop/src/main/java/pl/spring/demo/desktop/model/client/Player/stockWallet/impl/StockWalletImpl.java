package pl.spring.demo.desktop.model.client.Player.stockWallet.impl;

import java.util.HashMap;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.client.Player.stockWallet.StockWallet;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;


@Service
public class StockWalletImpl implements StockWallet {

private HashMap<StockDailyRecordTo, Integer> wallet;


public StockWalletImpl() {
wallet=new HashMap<StockDailyRecordTo,Integer>();
}



@Override
public void addToWallet(StockDailyRecordTo c, Integer n){
	if(!wallet.containsKey(c)){
		wallet.put(c, 0);
	}
	 Integer beforeNumber =wallet.get(c);
	 wallet.put(c, beforeNumber+n);
}


@Override
public HashMap<StockDailyRecordTo, Integer> showWallet() {
	return wallet;
}





@Override
public void removeFromWallet(StockDailyRecordTo c, Integer number) {
		Integer currentNumber=wallet.get(c);
		if(currentNumber<number){//try remove more than have
			try {
				throw new Exception();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		if(number==currentNumber){//sell all stock from wallet
			wallet.remove(c);
		}

		if(currentNumber>number){
			wallet.replace(c, currentNumber-number);
		}

}
}