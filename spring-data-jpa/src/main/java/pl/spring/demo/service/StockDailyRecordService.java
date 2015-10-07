package pl.spring.demo.service;




import java.util.List;
import java.util.Set;

import pl.spring.demo.entity.StockDailyRecordEntity;
import pl.spring.demo.to.StockDailyRecordTo;

public interface StockDailyRecordService {


	    
	    StockDailyRecordTo saveStockDailyRecord (StockDailyRecordTo  c);
	    List<StockDailyRecordTo> findAll();

}
