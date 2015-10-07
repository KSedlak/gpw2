package pl.spring.demo.desktop.model.cantor;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.NBP.Event.ExchangeRatesChanged;
import pl.spring.demo.desktop.model.currency.Currency;

@Service
public class Cantor implements ApplicationListener<ExchangeRatesChanged>{

@Value("${cantor.exchangeRate.sellPercentage}")
private double sellPercentage;
@Value("${cantor.exchangeRate.buyPercentage}")
private  double buyPercentage;

private HashMap<Currency, Double> currentExchangeRateMap;



public double getSellRate(Currency c) {
return currentExchangeRateMap.get(c)*(100+sellPercentage)/100;
}


public double getBuyRate(Currency c) {

	return currentExchangeRateMap.get(c)*(100-buyPercentage)/100;
}

public double getNbpRate(Currency c) {
	return currentExchangeRateMap.get(c);
}


public double getSellPercentage() {
	return sellPercentage;
}

public double getBuyPercentage() {
	return buyPercentage;
}

public double sellCurrency(Currency c, double PLN){
double rate=getSellRate(c);
return rate*PLN;
}

public double buyCurrency(Currency c, double currencyNumber){
double rate=getBuyRate(c);
return rate*currencyNumber;
}


@Override
public void onApplicationEvent(ExchangeRatesChanged event) {
	this.currentExchangeRateMap=event.getCurrentExchangeRateMap();

}



}
