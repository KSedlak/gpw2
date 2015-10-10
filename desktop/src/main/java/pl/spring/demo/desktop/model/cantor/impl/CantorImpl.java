package pl.spring.demo.desktop.model.cantor.impl;


import java.util.HashMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import pl.spring.demo.desktop.model.NBP.event.ExchangeRatesChanged;
import pl.spring.demo.desktop.model.Status.Status;
import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.cantor.event.CantorStatusChanged;
import pl.spring.demo.desktop.model.client.Player.event.IamDoneForToday;
import pl.spring.demo.desktop.model.currency.Currency;

@Service
@DependsOn("NationalBank")
public class CantorImpl implements Cantor{

@Value("${cantor.exchangeRate.sellPercentage}")
private double sellPercentage;
@Value("${cantor.exchangeRate.buyPercentage}")
private  double buyPercentage;
private Status cantorStatus;
private ApplicationContext applicationContext;
private HashMap<Currency, Double> currentExchangeRateMap;


public CantorImpl() {
	super();
	currentExchangeRateMap=new HashMap<Currency, Double>();
}

@Override
public double getSellRate(Currency c) {
return currentExchangeRateMap.get(c)*(100+sellPercentage)/100;
}

@Override
public double getBuyRate(Currency c) {

	return currentExchangeRateMap.get(c)*(100-buyPercentage)/100;
}
@Override
public double getNbpRate(Currency c) {
	return currentExchangeRateMap.get(c);
}


public double getSellPercentage() {
	return sellPercentage;
}

public double getBuyPercentage() {
	return buyPercentage;
}
@Override
public double sellCurrency(Currency c, double PLN){
double rate=getSellRate(c);
return rate*PLN;
}
@Override
public double buyCurrency(Currency c, double currencyNumber){
double rate=getBuyRate(c);
return rate*currencyNumber;
}


@Override
public void onApplicationEvent(ApplicationEvent event){
if(event instanceof ExchangeRatesChanged){
	cantorStatus=Status.Open;
this.currentExchangeRateMap=((ExchangeRatesChanged)event).getCurrentExchangeRateMap();
}
if(event instanceof IamDoneForToday){
	cantorStatus=Status.Closed;
	applicationContext.publishEvent(new CantorStatusChanged(cantorStatus));
}

}

@Override
public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	this.applicationContext = applicationContext;
}



}
