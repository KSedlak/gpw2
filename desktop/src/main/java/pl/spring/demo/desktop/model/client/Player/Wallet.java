package pl.spring.demo.desktop.model.client.Player;

import java.util.HashMap;

import pl.spring.demo.desktop.model.currency.Currency;



public class Wallet {

private HashMap<Currency, Integer> wallet;


public Wallet() {
wallet=new HashMap<Currency,Integer>();
}



public HashMap<Currency, Integer> getWallet() {
	return wallet;
}



public void addToWallet(Currency c, Integer n){
	if(!wallet.containsKey(c)){
		wallet.put(c, 0);
	}
	 int beforeNumber =wallet.get(c);
	 wallet.put(c, beforeNumber+n);
}


@SuppressWarnings("unused")
private void removeFromMap(Currency c, Integer number) {
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

