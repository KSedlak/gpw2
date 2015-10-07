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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameAndDate(String name, LocalDate d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockDailyRecordTo> getStocksByCompanyNameFromDateXDaysBefore(String name, LocalDate A) {
		// TODO Auto-generated method stub
		return null;
	}



}



