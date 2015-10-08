package pl.spring.demo.desktop.model.cantor;

import pl.spring.demo.desktop.model.currency.Currency;

public interface Cantor {
	 double getNbpRate(Currency c);
	 double sellCurrency(Currency c, double PLN);
	 double buyCurrency(Currency c, double currencyNumber);
	 double getSellRate(Currency c);
	 double getBuyRate(Currency c);
}
