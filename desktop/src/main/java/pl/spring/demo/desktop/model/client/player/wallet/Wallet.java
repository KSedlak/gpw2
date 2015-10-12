package pl.spring.demo.desktop.model.client.player.wallet;

import pl.spring.demo.desktop.model.currency.Currency;

public interface Wallet {

	void addToWallet(Currency c, Double n);
	double getMoney(Currency c);
	void removeFromWallet(Currency c, Double number);
	void clear();

}
