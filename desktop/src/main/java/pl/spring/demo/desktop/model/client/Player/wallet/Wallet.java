package pl.spring.demo.desktop.model.client.Player.wallet;

import pl.spring.demo.desktop.model.currency.Currency;

public interface Wallet {

	void addToWallet(Currency c, Double n);
	double getMoney(Currency c);
	void removeFromWallet(Currency c, Double number);
	double getBallance(Currency c, Currency d);
}
