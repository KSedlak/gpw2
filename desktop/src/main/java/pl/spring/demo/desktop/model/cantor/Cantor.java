package pl.spring.demo.desktop.model.cantor;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import pl.spring.demo.desktop.model.currency.Currency;

public interface Cantor extends ApplicationListener<ApplicationEvent>, ApplicationContextAware{
	 double getNbpRate(Currency c);
	 double sellCurrency(Currency c, double PLN);
	 double buyCurrency(Currency c, double currencyNumber);
	 double getSellRate(Currency c);
	 double getBuyRate(Currency c);
}
