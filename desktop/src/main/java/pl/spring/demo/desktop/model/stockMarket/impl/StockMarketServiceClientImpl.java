package pl.spring.demo.desktop.model.stockMarket.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.calendar.event.DayChanged;
import pl.spring.demo.desktop.model.client.player.event.NoMoreActionToday;
import pl.spring.demo.desktop.model.status.Status;
import pl.spring.demo.desktop.model.stockMarket.StockMarketServiceClient;
import pl.spring.demo.desktop.model.stockMarket.event.StockRatesChanged;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.service.StockMarketService;
import java.time.LocalDate;
import java.util.List;

@Component
public class StockMarketServiceClientImpl implements StockMarketServiceClient {

	@Autowired
	StockMarketService service;

	private ApplicationContext applicationContext;

	final static Logger logger = Logger.getLogger("Stock Market");

	private LocalDate today;

	private StockMarketServiceClientImpl() {

	}

	@Override
	public void onApplicationEvent(DayChanged event) {
		logger.info("Stock market check for new data");
		today = event.getCurrentDate();
		if (getTodayValues().size() == 0) {
			logger.info("No data for today because market is closed");
			applicationContext.publishEvent(new NoMoreActionToday(Status.CLOSED));
		}
		if (getTodayValues().size() > 0) {
			logger.info("Data found and notify brokerageOffice");
			applicationContext.publishEvent(new StockRatesChanged(true));
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<StockDailyRecordTo> getTodayValues() {
		return service.findAllRecordsBySpecificdate(today);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyName(String name) {
		return service.findAllByCompanyName(name);
	}

	@Override
	public StockDailyRecordTo getStocksByCompanyNameAndDate(String name, LocalDate d) {
		return service.findStockDailyRecordsByDateAndCompanyName(d, name);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B) {
		return service.findStockDailyRecordsByCompanyNameFromDateAToB(name, A, B);
	}

	@Override
	public List<StockDailyRecordTo> getStockDailyRecordsFromDateAToB(LocalDate A, LocalDate B) {
		return service.findStockDailyRecordsFromDateAToB(A, B);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromTodayXDaysBefore(String name, Integer X) {
		return getStocksByCompanyNameFromDateToDate(name, today.minusDays(X), today);
	}

	@Override
	public List<StockDailyRecordTo> getStocksFromTodayXDaysBefore(Integer X) {
		return getStockDailyRecordsFromDateAToB(today.minusDays(X), today);
	}

	@Override
	public List<StockDailyRecordTo> getChepestStockFromToday(int limit) {

		return service.findCheapestFromDay(today, limit);
	}

	@Override
	public StockDailyRecordTo getStocksByCompanyNameFromToday(String name) {
		return getStocksByCompanyNameAndDate(name, today);
	}

}
