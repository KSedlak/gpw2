package pl.spring.demo.desktop.model.transaction.exchangeTransaction;

import pl.spring.demo.desktop.model.currency.Currency;

public class ExchangeTransaction {

	private typeOfExchangeTransaction type;

	private Currency currency;

	private double input;

	private double output;

	public typeOfExchangeTransaction getType() {
		return type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getInput() {
		return input;
	}

	public double getOutput() {
		return output;
	}

	public ExchangeTransaction(typeOfExchangeTransaction type, Currency currency, double input) {
		super();
		this.type = type;
		this.currency = currency;
		this.input = input;
	}

}
