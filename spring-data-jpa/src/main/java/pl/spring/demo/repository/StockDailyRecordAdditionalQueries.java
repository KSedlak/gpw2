package pl.spring.demo.repository;

import java.util.Date;
import java.util.List;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;

public interface StockDailyRecordAdditionalQueries {
	public List<StockDailyRecordEntity> findStockDailyRecordsByDate(Date d);
}
