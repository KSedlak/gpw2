package pl.spring.demo.desktop.model.brokerageOffice.transactionRandomizer;

import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;

public interface TransactionRandomizer {

	MarketTransaction randomizePriceAndNumber(MarketTransaction t);

}
