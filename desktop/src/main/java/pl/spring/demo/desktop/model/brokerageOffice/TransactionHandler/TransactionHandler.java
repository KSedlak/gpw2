package pl.spring.demo.desktop.model.brokerageOffice.TransactionHandler;

import pl.spring.demo.desktop.model.Transaction.Transaction;

public interface TransactionHandler {

	Transaction handleTransaction(Transaction t);
}
