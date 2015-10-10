package pl.spring.demo.desktop.model.brokerageOffice.TransactionHandler.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.Transaction.StatusOfTransaction;
import pl.spring.demo.desktop.model.Transaction.Transaction;
import pl.spring.demo.desktop.model.brokerageOffice.TransactionHandler.TransactionHandler;
import pl.spring.demo.desktop.model.brokerageOffice.TransactionsRandomizer.TransactionRandomizer;
@Service
@DependsOn("transactionFactory")
public class TransactionHandlerImpl implements TransactionHandler {

	@Autowired
	TransactionRandomizer randomizer;

	@Override
	public Transaction handleTransaction(Transaction t) {

		if(t.getStatus().equals(StatusOfTransaction.Created)){

			Transaction randomized=randomizer.randomizePriceAndNumber(t);
			randomized.setStatus(StatusOfTransaction.Negotiated);
			return randomized;
		}

		if(t.getStatus().equals(StatusOfTransaction.Accepted)){

		}


		return t;
	}




}
