package pl.spring.demo.desktop.model.cantor.observer;



import java.util.HashMap;

import pl.spring.demo.desktop.model.currency.Currency;



public interface ObserverNBP {

void update(HashMap<Currency, Double> currentExchangeRateMap);

}
