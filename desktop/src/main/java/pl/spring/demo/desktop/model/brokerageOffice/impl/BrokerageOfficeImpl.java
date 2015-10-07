package pl.spring.demo.desktop.model.brokerageOffice.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.desktop.model.GPW.StockMarketServiceClient;
import pl.spring.demo.desktop.model.GPW.event.StockRatesChanged;
import pl.spring.demo.desktop.model.Status.Status;
import pl.spring.demo.desktop.model.brokerageOffice.BrokerageOffice;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;


public class BrokerageOfficeImpl implements BrokerageOffice{
	private Status status;

	@Autowired
	StockMarketServiceClient gpw;

	@Override
	public void onApplicationEvent(StockRatesChanged event) {
		status=Status.Open;//otworz biuro


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
	public List<StockDailyRecordTo> getStocksByCompanyNameAndDate(String name, LocalDate d) {
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



}



