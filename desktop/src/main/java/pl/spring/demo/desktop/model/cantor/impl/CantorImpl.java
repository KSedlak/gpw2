package pl.spring.demo.desktop.model.cantor.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.cantor.event.CantorStatusChanged;
import pl.spring.demo.desktop.model.client.player.event.IamDoneForToday;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.nationalBank.event.ExchangeRatesChanged;
import pl.spring.demo.desktop.model.status.Status;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;


@Component
@DependsOn("NationalBank")
@Qualifier("cantor")
public class CantorImpl implements Cantor {

	@Value("${cantor.exchangeRate.sellPercentage}")
	private double sellPercentage;
	@Value("${cantor.exchangeRate.buyPercentage}")
	private double buyPercentage;
	private Status cantorStatus;
	private ApplicationContext applicationContext;
	private HashMap<Currency, Double> currentExchangeRateMap;
	final static Logger logger=Logger.getLogger("Cantor");
	public CantorImpl() {
		super();
		currentExchangeRateMap = new HashMap<Currency, Double>();
	}

	@Override
	public double getSellRate(Currency c) {
		if(currentExchangeRateMap.isEmpty()){
			return 0;
		}
		return currentExchangeRateMap.get(c) * (100 + sellPercentage) / 100;
	}

	@Override
	public double getBuyRate(Currency c) {
		if(currentExchangeRateMap.isEmpty()){
			return 0;
		}
		return currentExchangeRateMap.get(c) * (100 - buyPercentage) / 100;
	}

	@Override
	public double getNbpRate(Currency c) {
		if(currentExchangeRateMap.isEmpty()){
			return 0;
		}
		return currentExchangeRateMap.get(c);
	}

	public double getSellPercentage() {
		return sellPercentage;
	}

	public double getBuyPercentage() {
		return buyPercentage;
	}

	@Override
	public double sellCurrencyToClient(Currency output, double requested) {

		double rate = getSellRate(output);
		double cost=DoubleRounder.roundToMoney(rate*requested);
		logger.info("Client get from cantor: "+rate+" "+output.getName()+" exRate: "+getSellRate(output));
		return cost;
	}

	@Override
	public double buyCurrencyFromClient(Currency input, double currencyNumber) {
		double rate = getBuyRate(input);
		double returned=rate=DoubleRounder.roundToMoney(rate*currencyNumber);
		logger.info("Client get from cantor: "+rate+" PLN and pay for it: "+currencyNumber+" "+input.getName()+" exRate: "+getBuyRate(input));
		return returned;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ExchangeRatesChanged) {
			cantorStatus = Status.Open;
			this.currentExchangeRateMap = ((ExchangeRatesChanged) event).getCurrentExchangeRateMap();
			applicationContext.publishEvent(new CantorStatusChanged(cantorStatus));
		}
		if (event instanceof IamDoneForToday) {
			cantorStatus = Status.Closed;
			applicationContext.publishEvent(new CantorStatusChanged(cantorStatus));
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}



}
