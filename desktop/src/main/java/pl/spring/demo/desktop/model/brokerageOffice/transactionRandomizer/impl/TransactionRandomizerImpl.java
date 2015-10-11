package pl.spring.demo.desktop.model.brokerageOffice.transactionRandomizer.impl;



import java.util.concurrent.ThreadLocalRandom;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.brokerageOffice.transactionRandomizer.TransactionRandomizer;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;

@Service
@DependsOn("marketTransactionFactory")
public class TransactionRandomizerImpl implements TransactionRandomizer {
	final static Logger logger=Logger.getLogger("TransactionRandomizer");
	@Override
	public MarketTransaction randomizePriceAndNumber(MarketTransaction t) {

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
		return DoubleRounder.roundToMoney(ThreadLocalRandom.current().nextDouble(min, max));
	}


}
