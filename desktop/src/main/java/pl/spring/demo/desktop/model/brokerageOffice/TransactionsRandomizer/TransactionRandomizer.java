package pl.spring.demo.desktop.model.brokerageOffice.TransactionsRandomizer;

import pl.spring.demo.desktop.model.Transaction.Transaction;

public interface TransactionRandomizer {

Transaction randomizePriceAndNumber(Transaction t);

}
