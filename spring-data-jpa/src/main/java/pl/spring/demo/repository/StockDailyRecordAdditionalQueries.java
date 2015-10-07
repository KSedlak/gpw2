package pl.spring.demo.repository;

import java.util.Date;
import java.util.List;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;

public interface StockDailyRecordAdditionalQueries {
	public List<StockDailyRecordEntity> findStockDailyRecordsByDate(Date d);
	public List<StockDailyRecordEntity> findStockDailyRecordsByDateAndCompanyName(Date d, String name);
	public List<StockDailyRecordEntity> findStockDailyRecordsByCompanyName(String name);
	public List<StockDailyRecordEntity> findStockDailyRecordsByCompanyNameFromDateAToB(String name, Date A, Date B);
	public List<StockDailyRecordEntity> findStockDailyRecordsFromDateAToB(Date A, Date B);
}
