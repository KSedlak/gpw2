package pl.spring.demo.desktop.model.NBP.event;

import java.util.HashMap;
import org.springframework.context.ApplicationEvent;
import pl.spring.demo.desktop.model.currency.Currency;

public class ExchangeRatesChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HashMap<Currency, Double> currentExchangeRateMap;

	public ExchangeRatesChanged(HashMap<Currency, Double> source) {
		super(source);
		this.currentExchangeRateMap = source;
	}

	public HashMap<Currency, Double> getCurrentExchangeRateMap() {
		return currentExchangeRateMap;
	}





}