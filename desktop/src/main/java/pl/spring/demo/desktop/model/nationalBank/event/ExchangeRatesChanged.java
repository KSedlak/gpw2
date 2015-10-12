package pl.spring.demo.desktop.model.nationalBank.event;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import pl.spring.demo.desktop.model.currency.Currency;

public class ExchangeRatesChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HashMap<Currency, Double> currentExchangeRateMap;

	final static Logger logger = Logger.getLogger("ExchangesRateChangedEvent");

	public ExchangeRatesChanged(HashMap<Currency, Double> source) {
		super(source);
		this.currentExchangeRateMap = source;
		logger.info("event fired");
	}

	public HashMap<Currency, Double> getCurrentExchangeRateMap() {
		return currentExchangeRateMap;
	}

}