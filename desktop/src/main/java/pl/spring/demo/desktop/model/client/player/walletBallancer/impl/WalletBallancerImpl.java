package pl.spring.demo.desktop.model.client.player.walletBallancer.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.client.player.walletBallancer.WalletBalancer;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.transaction.exchangeTransaction.ExchangeTransaction;
import pl.spring.demo.desktop.model.transaction.exchangeTransaction.typeOfExchangeTransaction;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;

@Service
public class WalletBallancerImpl implements WalletBalancer {

	final static Logger logger = Logger.getLogger("WalletBallancerAdvisor");

	@Value("${player.moneyWalletBalance}")
	private double BALLANCE_WALLET;

	@Value("${player.moneyWalletBalanceTolerance}")
	private double BALLANCE_TOLERANCE;

	@Autowired
	private Cantor cantor;

	@Override
	public ExchangeTransaction makeBallance(Currency primary, double quantA, Currency b, double quantB) {

		double ballance = getBallance(primary, quantA, b, quantB);

		if (ballance > (BALLANCE_WALLET + BALLANCE_TOLERANCE)) {
			logger.info("decision: sell " + primary.getName());
			return createBuyForeginCurrencyToBackBallanced(quantA, b, quantB);
		}
		if (ballance < (BALLANCE_WALLET - BALLANCE_TOLERANCE)) {
			logger.info("decision: sell " + b.getName());
			return createSellForeginCurrencyToBackBallanced(quantA, b, quantB);
		}

		if (ballance < (BALLANCE_WALLET + BALLANCE_TOLERANCE) && ballance > (BALLANCE_WALLET - BALLANCE_TOLERANCE)) {
			logger.info("decision: do nothing");

		}
		return new ExchangeTransaction(typeOfExchangeTransaction.Sell, primary, 1);
	}

	public double getBallance(Currency primary, double quantA, Currency foregin, double quantB) {
		if (quantA == 0 || quantB == 0) {
			return 0;
		}

		double valueForeginInPrimary = cantor.getBuyRate(foregin) * quantB;
		double valuePrimary = quantA;
		double ballance = (valuePrimary / (valueForeginInPrimary + valuePrimary)) * 100;
		ballance = DoubleRounder.roundToMoney(ballance);
		logger.info("Ballance in wallet: " + ballance);
		logger.info("primary: " + DoubleRounder.roundToMoney(valuePrimary) + " foreign: "
				+ DoubleRounder.roundToMoney(quantB));
		return ballance;
	}

	private ExchangeTransaction createBuyForeginCurrencyToBackBallanced(double quantA, Currency foreign,
			double quantB) {
		double valueOfWallet = getaValueOfWalletInPrimaryCurrency(quantA, foreign, quantB);
		double ballanced = getDesiredValueOfPrimary(valueOfWallet);
		double toBuy = (quantA - ballanced) / cantor.getSellRate(foreign);
		return new ExchangeTransaction(typeOfExchangeTransaction.Buy, foreign, DoubleRounder.roundToMoney(toBuy));
	}

	private ExchangeTransaction createSellForeginCurrencyToBackBallanced(double quantA, Currency foreign,
			double quantB) {
		double valueOfWallet = getaValueOfWalletInPrimaryCurrency(quantA, foreign, quantB);
		double ballanced = getDesiredValueOfPrimary(valueOfWallet);
		double toSell = (ballanced - quantA) / cantor.getBuyRate(foreign);
		return new ExchangeTransaction(typeOfExchangeTransaction.Sell, foreign, DoubleRounder.roundToMoney(toSell));
	}

	private double getaValueOfWalletInPrimaryCurrency(double quantA, Currency foregin, double quantB) {
		double valueOfPrimar = quantA;
		double valueOfForein = quantB * cantor.getBuyRate(foregin);
		return valueOfForein + valueOfPrimar;
	}

	private double getDesiredValueOfPrimary(double valueOfWalletInPrimary) {
		return valueOfWalletInPrimary * BALLANCE_WALLET / 100;
	}

}
