package pl.spring.demo.desktop.model.NBP;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import pl.spring.demo.desktop.model.Calendar.event.DayChanged;
import pl.spring.demo.desktop.model.currency.Currency;

public interface NationalBank extends ApplicationListener<DayChanged>,ApplicationContextAware {

	Double getExchangeRate(Currency c);

}
