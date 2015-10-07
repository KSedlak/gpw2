package pl.spring.demo.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.jinq.JinqSource;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;
import pl.spring.demo.repository.StockDailyRecordAdditionalQueries;

public class StockDailyRecordRepositoryJinqImpl implements StockDailyRecordAdditionalQueries {

	@Autowired
	private JinqSource jinqSource;

	@Override
	public List<StockDailyRecordEntity> findStockDailyRecordsByDate(Date d) {

		return jinqSource.dailyRecords().where(a -> a.getDate().equals(d)).toList();
	}

	@Override
	public List<StockDailyRecordEntity> findStockDailyRecordsByDateAndCompanyName(Date d, String name) {
		return jinqSource.dailyRecords().where(a -> {
			return a.getDate().equals(d) && a.getCompany().getName().equals(name);
		}).toList();
	}

	@Override
	public List<StockDailyRecordEntity> findStockDailyRecordsByCompanyNameFromDateAToB(String name, Date A, Date B) {
		return jinqSource.dailyRecords().where(a -> {
			return a.getDate().after(A) && a.getCompany().getName().equals(name) && a.getDate().before(B);
		}).toList();
	}

	@Override
	public List<StockDailyRecordEntity> findStockDailyRecordsByCompanyName(String name) {
		return jinqSource.dailyRecords().where(a -> {
			return a.getCompany().getName().equals(name);
		}).toList();
	}

	@Override
	public List<StockDailyRecordEntity> findStockDailyRecordsFromDateAToB(Date A, Date B) {
		return jinqSource.dailyRecords().where(a -> {
			return a.getDate().after(A) && a.getDate().before(B);
		}).toList();
	}

}