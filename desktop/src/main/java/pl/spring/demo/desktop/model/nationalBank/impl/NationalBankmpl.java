package pl.spring.demo.desktop.model.nationalBank.impl;



import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.calendar.event.DayChanged;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.nationalBank.NationalBank;
import pl.spring.demo.desktop.model.nationalBank.event.ExchangeRatesChanged;

@Service("NationalBank")
public class NationalBankmpl implements  NationalBank {
	final static Logger logger=Logger.getLogger("NationalBank");
	private HashMap<Currency, Double> currentExchangeRateMap;
	private ApplicationContext applicationContext;

	private NationalBankmpl(){
		initMap();
		changeExchangeRate();//first set rates
	}


	private void changeExchangeRate(){
		Random rand = new Random();
		for(Currency c: Currency.values()){

		int x = rand.nextInt((int) ((c.getUpper()*100)-(c.getLower()*100)+1));
		Double exchangeRate=((c.getLower()*100)+x)/100;
		currentExchangeRateMap.replace(c, exchangeRate);

	}
	}

	private void initMap(){
		currentExchangeRateMap=new HashMap<Currency,Double>();
		for(Currency c: Currency.values()){
			currentExchangeRateMap.put(c, new Double(0));
		}
	}


	@Override
	public Double getExchangeRate(Currency c) {
		return currentExchangeRateMap.get(c);
	}


	@Override
	public void onApplicationEvent(DayChanged event) {
		changeExchangeRate();
		logger.info("National bank generate new currency exchanges rate");
		applicationContext.publishEvent(new ExchangeRatesChanged(currentExchangeRateMap));
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}






}
