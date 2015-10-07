package pl.spring.demo.desktop.model.NBP.Observer;

import pl.spring.demo.desktop.model.cantor.observer.ObserverNBP;

public interface ObservableNBP  {



	public void addObserver(ObserverNBP obs);

	public void notifyObservers();

	void deleteObservers(ObserverNBP obs);

}
