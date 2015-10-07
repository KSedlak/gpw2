package pl.spring.demo.service;





import java.util.List;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockDailyRecordService {



	    StockDailyRecordTo saveStockDailyRecord (StockDailyRecordTo  c);
	    List<StockDailyRecordTo> findAll();
}
