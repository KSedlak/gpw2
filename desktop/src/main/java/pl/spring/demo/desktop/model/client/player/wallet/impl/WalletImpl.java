package pl.spring.demo.desktop.model.client.player.wallet.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.client.player.wallet.Wallet;
import pl.spring.demo.desktop.model.currency.Currency;


@Service
public class WalletImpl implements Wallet {

private HashMap<Currency, Double> wallet;



@Autowired
public WalletImpl(@Value("${player.startPLN}") Double startPLN,@Value("${player.startForeginCurrency}")Double startEURO) {
wallet=new HashMap<Currency,Double>();
this.addToWallet(Currency.PLN, startPLN);
this.addToWallet(Currency.EURO, startEURO);
}



@Override
public void addToWallet(Currency c, Double n){
	if(!wallet.containsKey(c)){
		wallet.put(c, 0.0);
	}
	 Double beforeNumber =wallet.get(c);
	 wallet.put(c, beforeNumber+n);
}



@Override
public void removeFromWallet(Currency c, Double number) {
		Double currentNumber=wallet.get(c);
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



@Override
public double getMoney(Currency c) {

	return wallet.get(c);
}



@Override
public double getBallance(Currency c, Currency d) {
if(wallet.get(c)==null || wallet.get(d)==null){
	return 0;
}
return wallet.get(c)/wallet.get(d);
}







}

