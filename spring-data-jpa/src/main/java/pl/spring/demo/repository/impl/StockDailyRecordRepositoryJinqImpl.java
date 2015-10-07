package pl.spring.demo.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.jinq.JinqSource;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;
import pl.spring.demo.repository.StockDailyRecordAdditionalQueries;

public class StockDailyRecordRepositoryJinqImpl  implements StockDailyRecordAdditionalQueries{
		
	    @Autowired
	    private JinqSource jinqSource;
	    

	    @Override
		public List<StockDailyRecordEntity> findStockDailyRecordsByDate(Date d) {

			return jinqSource.dailyRecords().where(a->a.getDate().equals(d)).toList();
	}
}