package pl.spring.demo.desktop.model.brokerageOffice.TransactionsRandomizer.impl;



import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.brokerageOffice.TransactionsRandomizer.TransactionRandomizer;
import pl.spring.demo.desktop.model.transaction.Transaction;

@Service
@DependsOn("transactionFactory")
public class TransactionRandomizerImpl implements TransactionRandomizer {

	@Override
	public Transaction randomizePriceAndNumber(Transaction t) {

		int newNumberOfStock=getRandomNumberOfStockInTransaction(
				t.getNumberOfStockRequested(),
				t.getLowerBoundWarrantedNumberOfStock(),
				t.getUpperBoundWarrantedNumberOfStock());

		t.setBrokerageOfficeAcceptedNumber(newNumberOfStock);

		double newPrice=getRandomPriceInTransaction(
				t.getStock().getValue(),
				t.getLowerBoundPriceRandomizer(),
				t.getUpperBoundPriceRandomizer());

		t.setBrokerageOfficeAcceptedRate(newPrice);

		return t;

	}


	private int getRandomNumberOfStockInTransaction(int input, Double lowerBound, Double upperBound){

		int min=(int)(lowerBound*input)/100;
		int max=(int)(upperBound*input)/100;

		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private double getRandomPriceInTransaction(double input, Double lowerBound, Double upperBound){
		double min=(lowerBound*input)/100;
		double max=(upperBound*input)/100;
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}
