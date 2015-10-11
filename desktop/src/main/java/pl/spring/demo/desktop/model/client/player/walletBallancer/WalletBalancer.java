package pl.spring.demo.desktop.model.client.player.walletBallancer;


import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.transaction.exchangeTransaction.ExchangeTransaction;

public interface WalletBalancer {
ExchangeTransaction makeBallance(Currency a, double quantA, Currency b, double quantB );
double getBallance(Currency primary, double quantA, Currency foregin, double quantB);
}
