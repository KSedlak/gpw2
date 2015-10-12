package pl.spring.demo.desktop.model.client.player.wallet.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.client.player.wallet.Wallet;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;


@Service
public class WalletImpl implements Wallet {

private HashMap<Currency, Double> wallet;
final static Logger logger=Logger.getLogger("Wallet");


@Autowired
public WalletImpl(@Value("${player.startPLN}") Double startPLN,@Value("${player.startForeginCurrency}")Double startEURO) {
wallet=new HashMap<Currency,Double>();
this.addToWallet(Currency.PLN, startPLN);
this.addToWallet(Currency.EURO, startEURO);
}



@Override
public void addToWallet(Currency c, Double n){
	logger.info("Client put to wallet currency: "+c.getName()+" quantity: "+n);
	if(!wallet.containsKey(c)){
		wallet.put(c, 0.0);
	}
	 Double beforeNumber =wallet.get(c);
	 Double toWallet=DoubleRounder.roundToMoney(beforeNumber+n);
	 wallet.put(c, toWallet);
}



@Override
public void removeFromWallet(Currency c, Double number) {
	logger.info("Client remove to wallet currency: "+c.getName()+" quantity: "+number);
		Double currentNumber=wallet.get(c);
		if(currentNumber<number){//try remove more than have
			logger.info("Client remove more than is allowed");

		}
		if(number==currentNumber){//sell all stock from wallet
			wallet.remove(c);
			logger.info("Client remove all money in that currency");
		}

		if(currentNumber>number){
			double fromWallet=currentNumber-number;
			wallet.replace(c, DoubleRounder.roundToMoney(fromWallet));
			logger.info("Client remove money and left in wallet: "+(currentNumber-number));
		}

}



@Override
public double getMoney(Currency c) {

	return wallet.get(c);
}



@Override
public void clear() {
	wallet.clear();

}











}

