package pl.spring.demo.desktop.model.brokerageOffice.TransactionsRandomizer;

import pl.spring.demo.desktop.model.transaction.Transaction;

public interface TransactionRandomizer {

Transaction randomizePriceAndNumber(Transaction t);

}
