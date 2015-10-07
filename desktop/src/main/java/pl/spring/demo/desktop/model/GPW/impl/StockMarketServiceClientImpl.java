package pl.spring.demo.desktop.model.GPW.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.spring.demo.desktop.model.Calendar.event.DayChanged;
import pl.spring.demo.desktop.model.GPW.StockMarketServiceClient;
import pl.spring.demo.desktop.model.GPW.event.StockRatesChanged;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.service.StockMarketService;
import java.time.LocalDate;
import java.util.List;

@Component
public class StockMarketServiceClientImpl implements StockMarketServiceClient {

	@Autowired
	StockMarketService service;

	private ApplicationContext applicationContext;

	private LocalDate today;

	private StockMarketServiceClientImpl() {

	}

	@Override
	public void onApplicationEvent(DayChanged event) {
		applicationContext.publishEvent(new StockRatesChanged(true));
		today=event.getCurrentDate();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<StockDailyRecordTo> getTodayValues() {
		return service.getAllRecordsBySpecificdate(today);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyName(String name) {
		return service.findAllByCompanyName(name);
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameAndDate(String name, LocalDate d) {
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






}
