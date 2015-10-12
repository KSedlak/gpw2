package pl.spring.demo.desktop.model.brokerageOffice.impl;

import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeStatusChanged;
import pl.spring.demo.desktop.model.brokerageOffice.transactionHandler.TransactionHandler;
import pl.spring.demo.desktop.model.client.player.event.NoMoreActionToday;
import pl.spring.demo.desktop.model.status.Status;
import pl.spring.demo.desktop.model.stockMarket.StockMarketServiceClient;
import pl.spring.demo.desktop.model.stockMarket.event.StockRatesChanged;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

@Service
public class BrokerageOfficeImpl implements BrokerageOffice {
	private Status status;
	private ApplicationContext applicationContext;
	final static Logger logger = Logger.getLogger("BrokerageOffice");

	@Autowired
	StockMarketServiceClient gpw;

	@Autowired
	TransactionHandler transactionHandler;
	@Value("${brokerageOffice.commissionPercent}")
	Double commissionPercent;
	@Value("${brokerageOffice.commissionFixed}")
	Double fixedCommission;

	public BrokerageOfficeImpl() {
		super();
	}

	@Override
	public List<StockDailyRecordTo> getTodayStockValues() {
		return gpw.getTodayValues();
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyName(String name) {
		return gpw.getStocksByCompanyName(name);
	}

	@Override
	public StockDailyRecordTo getStocksByCompanyNameAndDate(String name, LocalDate d) {
		return gpw.getStocksByCompanyNameAndDate(name, d);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B) {
		return gpw.getStocksByCompanyNameFromDateToDate(name, A, B);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromDateXDaysBefore(String name, LocalDate A, Integer X) {
		return gpw.getStocksByCompanyNameFromDateToDate(name, A.minusDays(X), A);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromTodayXDaysBefore(String name, Integer X) {
		return gpw.getStocksByCompanyNameFromTodayXDaysBefore(name, X);
	}

	@Override
	public List<StockDailyRecordTo> getStocksFromTodayXDaysBefore(Integer X) {
		return getStocksFromTodayXDaysBefore(X);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public MarketTransaction makeTransaction(MarketTransaction t) {

		MarketTransaction result = transactionHandler.handleTransaction(t);
		setCommission(result);
		return result;
	}

	private double calculateCommisionFromTransaction(MarketTransaction t) {
		double valueOfTransaction = t.getValueOfBrokerageOfficeOffer();
		double percentCommission = (commissionPercent / 100) * valueOfTransaction;
		if (percentCommission < fixedCommission) {
			return fixedCommission;
		}
		return DoubleRounder.roundToMoney(percentCommission);

	}

	private void setCommission(MarketTransaction t) {
		double commission = calculateCommisionFromTransaction(t);
		logger.info("brokerageOffice add commission: " + commission + " to transaction");
		t.setBrokerageOfficeCommission(commission);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof StockRatesChanged) {
			status = Status.Open;
			applicationContext.publishEvent(new BrokerageOfficeStatusChanged(status));
		}
		if (event instanceof NoMoreActionToday) {
			status = Status.Closed;
			applicationContext.publishEvent(new BrokerageOfficeStatusChanged(status));
		}

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public List<StockDailyRecordTo> getCheapesStocksFromToday(Integer X) {
		return gpw.getChepestStockFromToday(X);
	}

	@Override
	public StockDailyRecordTo getStocksByCompanyNameFromToday(String name) {
		return gpw.getStocksByCompanyNameFromToday(name);
	}

}
