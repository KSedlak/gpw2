package pl.spring.demo.desktop.model.transaction.exchangeTransaction;

import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.transaction.typeOTransaction;

public class ExchangeTransaction {

	private typeOTransaction type;

	private Currency currency;

	private double input;

	private double output;

	public typeOTransaction getType() {
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

	public ExchangeTransaction(typeOTransaction type, Currency currency, double input) {
		super();
		this.type = type;
		this.currency = currency;
		this.input = input;
	}

}
