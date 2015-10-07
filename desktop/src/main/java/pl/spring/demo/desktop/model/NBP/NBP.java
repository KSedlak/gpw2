package pl.spring.demo.desktop.model.NBP;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import pl.spring.demo.desktop.model.NBP.Observer.ObservableNBP;
import pl.spring.demo.desktop.model.cantor.observer.ObserverNBP;
import pl.spring.demo.desktop.model.currency.Currency;





public class NBP implements ObservableNBP{



	private List<ObserverNBP> listOfInstitution;
	private HashMap<Currency, Double> currentExchangeRateMap;

	private static NBP nbp=new NBP();



	private NBP(){
		initMap();
		changeExchangeRate();
		listOfInstitution=new ArrayList<ObserverNBP>();
	}


	public void changeExchangeRate(){
		Random rand = new Random();
		for(Currency c: Currency.values()){

		int x = rand.nextInt((int) ((c.getUpper()*100)-(c.getLower()*100)+1));
		Double exchangeRate=((c.getLower()*100)+x)/100;

		currentExchangeRateMap.replace(c, exchangeRate);

	}
	}
	public static NBP getInstance(){
		return nbp;
	}


	public void initMap(){
		currentExchangeRateMap=new HashMap<Currency,Double>();
		for(Currency c: Currency.values()){
			currentExchangeRateMap.put(c, new Double(0));
		}
	}


	@Override
	public void addObserver(ObserverNBP obs) {
		listOfInstitution.add(obs);

	}



	@Override
	public void notifyObservers() {
	listOfInstitution.forEach(ins-> ins.update(currentExchangeRateMap));

	}


	@Override
	public void deleteObservers(ObserverNBP obs) {
		listOfInstitution.remove(obs);

	}


	public Double getExchangeRate(Currency c) {
		return currentExchangeRateMap.get(c);
	}



}
