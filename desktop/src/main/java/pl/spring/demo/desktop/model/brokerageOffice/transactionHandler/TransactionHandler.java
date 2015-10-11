package pl.spring.demo.desktop.model.brokerageOffice.transactionHandler;

import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;

public interface TransactionHandler {

	MarketTransaction handleTransaction(MarketTransaction t);
}
