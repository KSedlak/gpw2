package pl.spring.demo.desktop.model.brokerageOffice.transactionHandler.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.brokerageOffice.transactionHandler.TransactionHandler;
import pl.spring.demo.desktop.model.brokerageOffice.transactionRandomizer.TransactionRandomizer;
import pl.spring.demo.desktop.model.transaction.marketTransaction.StatusOfTransaction;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;

@Service
@DependsOn("marketTransactionFactory")
public class TransactionHandlerImpl implements TransactionHandler {

	@Autowired
	TransactionRandomizer randomizer;

	final static Logger logger = Logger.getLogger("TransactionHandeler");

	@Override
	public MarketTransaction handleTransaction(MarketTransaction t) {
		logger.info("handle transaction");
		if (t.getStatus().equals(StatusOfTransaction.CREATED)) {
			logger.info("Transaction requested parameter[ value: " + t.getStock().getValue() + " quantity: "
					+ t.getNumberOfStockRequested() + " company: " + t.getStock().getCompany().getName() + "]");

			MarketTransaction randomized = randomizer.randomizePriceAndNumber(t);
			randomized.setStatus(StatusOfTransaction.NEGOTIATED);

			logger.info("Transaction output parameter[ value: " + t.getBrokerageOfficeAcceptedRate() + " quantity: "
					+ t.getBrokerageOfficeAcceptedNumber() + " company: " + t.getStock().getCompany().getName() + "]");

			return randomized;
		}

		if (t.getStatus().equals(StatusOfTransaction.ACCEPTED)) {

		}

		return t;
	}

}
