package pl.spring.demo.desktop.model.NBP;



import java.util.HashMap;
import java.util.Random;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.spring.demo.desktop.model.Calendar.Events.DayChanged;
import pl.spring.demo.desktop.model.NBP.Event.ExchangeRatesChanged;
import pl.spring.demo.desktop.model.currency.Currency;

@Component
public class NBP implements ApplicationListener<DayChanged>,ApplicationContextAware {

	private HashMap<Currency, Double> currentExchangeRateMap;
	private ApplicationContext applicationContext;

	private NBP(){
		initMap();
		changeExchangeRate();//first set rates
	}


	public void changeExchangeRate(){
		Random rand = new Random();
		for(Currency c: Currency.values()){

		int x = rand.nextInt((int) ((c.getUpper()*100)-(c.getLower()*100)+1));
		Double exchangeRate=((c.getLower()*100)+x)/100;
		currentExchangeRateMap.replace(c, exchangeRate);

	}
	}

	public void initMap(){
		currentExchangeRateMap=new HashMap<Currency,Double>();
		for(Currency c: Currency.values()){
			currentExchangeRateMap.put(c, new Double(0));
		}
	}



	public Double getExchangeRate(Currency c) {
		return currentExchangeRateMap.get(c);
	}


	@Override
	public void onApplicationEvent(DayChanged event) {
		changeExchangeRate();
		applicationContext.publishEvent(new ExchangeRatesChanged(currentExchangeRateMap));
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}






}
