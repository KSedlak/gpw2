package pl.spring.demo.desktop.model.nationalBank;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import pl.spring.demo.desktop.model.calendar.event.DayChanged;
import pl.spring.demo.desktop.model.currency.Currency;

public interface NationalBank extends ApplicationListener<DayChanged>,ApplicationContextAware {

	Double getExchangeRate(Currency c);

}
